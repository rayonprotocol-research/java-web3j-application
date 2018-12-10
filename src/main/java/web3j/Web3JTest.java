package web3j;

import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.exceptions.ContractCallException;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

public class Web3JTest {
	public final static String LOCAL_URL = "http://localhost:8545";
	public final static String LOCAL_GAS_PRICE = "1";
	public final static String LOCAL_GAS_LIMIT = "90000000000";
	// public final static String KLAYTN_URL = "http://192.168.3.102:8551";
	// public final static String KLAYTN_GAS_PRICE = "25000000000";
	// public final static String KLAYTN_GAS_LIMIT = "20000000";

	public final static String NODE_URL = LOCAL_URL;
	public final static String NODE_GAS_PRICE = LOCAL_GAS_PRICE;
	public final static String NODE_GAS_LIMIT = LOCAL_GAS_LIMIT;
	public final static ContractGasProvider GasProvider = new StaticGasProvider(new BigInteger(NODE_GAS_PRICE), new BigInteger(NODE_GAS_LIMIT));

	public final static String PRIVATE_KEY = "0xf7cd72178207e94255c394f9fc1fc6387f6b6eb3776701fc6ec76c5429030fe9";

	public static void main(String[] args) {
		try {
			Web3j web3j = Web3j.build(new HttpService(NODE_URL));
			System.out.println(String.format("Node URL'%s', Version:%s", NODE_URL, web3j.web3ClientVersion().send().getWeb3ClientVersion()));

			Credentials credentials = Credentials.create(PRIVATE_KEY);
			// Credentials credentials = WalletUtils.loadCredentials("password", "keystore");
			System.out.println("Wallet address: " + credentials.getAddress());

			// Deploy
			KeyValue contract = KeyValue.deploy(web3j, credentials, GasProvider).send();
			System.out.println(String.format("%s is deployed. contract address: %s", "KeyValue", contract.getContractAddress()));

			// Put
			System.out.println("Put 1 -> 100");
			TransactionReceipt receipt = contract.put(BigInteger.valueOf(1), BigInteger.valueOf(100)).send();
			System.out.println(receipt);
			System.out.println("Put 2 -> 300");
			receipt = contract.put(BigInteger.valueOf(2), BigInteger.valueOf(300)).send();
			System.out.println(receipt);
			try {
				System.out.println("Put 2 -> 500");
				receipt = contract.put(BigInteger.valueOf(2), BigInteger.valueOf(500)).send();
			} catch (RuntimeException e) {
				System.out.println("Put 2 -> 500 is reverted");
			}

			// Get
			System.out.println("Get 1 -> " + contract.get(BigInteger.valueOf(1)).send());
			System.out.println("Get 2 -> " + contract.get(BigInteger.valueOf(2)).send());
			try {
				System.out.println("Get 3 -> " + contract.get(BigInteger.valueOf(3)).send());
			} catch (ContractCallException e) {
				System.out.println("Get 3 -> is reverted");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
