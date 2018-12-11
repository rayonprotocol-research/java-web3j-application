package web3j;

import java.math.BigInteger;
import java.net.URI;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketClient;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tx.exceptions.ContractCallException;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import web3j.KeyValue.ValuePutEventResponse;

public class Web3JTest {
	public final static String NODE_URL = "http://localhost:8545";
	public final static String NODE_GAS_PRICE = "1";
	public final static String NODE_GAS_LIMIT = "90000000000";
	// public final static String NODE_URL = "http://192.168.3.102:8551";
	// public final static String NODE_GAS_PRICE = "25000000000";
	// public final static String NODE_GAS_LIMIT = "20000000";

	public final static ContractGasProvider GasProvider = new StaticGasProvider(new BigInteger(NODE_GAS_PRICE), new BigInteger(NODE_GAS_LIMIT));

	public final static String PRIVATE_KEY = "0xf7cd72178207e94255c394f9fc1fc6387f6b6eb3776701fc6ec76c5429030fe9";

	public static void main(String[] args) {
		try {
			test1();
			// test2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test1() throws Exception {
		Web3j web3j = Web3j.build(new HttpService(NODE_URL));
		System.out.println(String.format("Node URL'%s', Version:%s", NODE_URL, web3j.web3ClientVersion().send().getWeb3ClientVersion()));

		Credentials credentials = Credentials.create(PRIVATE_KEY);
		// Credentials credentials = WalletUtils.loadCredentials("password", "keystore");
		System.out.println("Wallet address: " + credentials.getAddress());

		// Deploy
		KeyValue contract1 = KeyValue.deploy(web3j, credentials, GasProvider).send();
		System.out.println(String.format("%s is deployed. contract address: %s", "KeyValue", contract1.getContractAddress()));

		// Put : sync
		System.out.println("Put 1 -> 100");
		TransactionReceipt receipt = contract1.put(BigInteger.valueOf(1), BigInteger.valueOf(100)).send();
		System.out.println(receipt);
		for (ValuePutEventResponse event : contract1.getValuePutEvents(receipt)) {
			System.out.println(String.format("Event '%d' -> '%d'", event.key, event.value));
		}

		// Put : async
		System.out.println("Put 2 -> 300");
		CompletableFuture<TransactionReceipt> future = contract1.put(BigInteger.valueOf(2), BigInteger.valueOf(300)).sendAsync();
		while (!future.isDone()) {
			System.out.println("Waiting until done..");
			Thread.sleep(500);
		}
		receipt = future.get();
		System.out.println(receipt);

		// Put : revert
		try {
			System.out.println("Put 2 -> 500");
			receipt = contract1.put(BigInteger.valueOf(2), BigInteger.valueOf(500)).send();
		} catch (RuntimeException | TransactionException e) {
			System.out.println("Put 2 -> 500 is reverted");
		}

		// Get
		System.out.println("Get 1 -> " + contract1.get(BigInteger.valueOf(1)).send());
		System.out.println("Get 2 -> " + contract1.get(BigInteger.valueOf(2)).send());
		try {
			System.out.println("Get 3 -> " + contract1.get(BigInteger.valueOf(3)).send());
		} catch (ContractCallException e) {
			System.out.println("Get 3 -> is reverted");
		}

		// create instance by address
		KeyValue contract2 = KeyValue.load(contract1.getContractAddress(), web3j, credentials, GasProvider);
		System.out.println("Get 1 -> " + contract2.get(BigInteger.valueOf(1)).send());

		// event
	}

	private static void test2() throws Exception {
		final WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://localhost:8551"));
		// final WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://192.168.3.102:8552"));
		final boolean includeRawResponses = false;
		final WebSocketService webSocketService = new WebSocketService(webSocketClient, includeRawResponses);

		final Request<?, Web3ClientVersion> request = new Request<>("web3_clientVersion", Collections.<String> emptyList(), webSocketService, Web3ClientVersion.class);

		// Send an asynchronous request via WebSocket protocol
		final CompletableFuture<Web3ClientVersion> reply = webSocketService.sendAsync(request, Web3ClientVersion.class);

		// Get result of the reply
		final Web3ClientVersion clientVersion = reply.get();

	}
}
