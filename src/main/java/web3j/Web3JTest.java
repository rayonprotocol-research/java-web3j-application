package web3j;

import java.io.IOException;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

public class Web3JTest {

	public static void main(String[] args) {
		try {
			// Web3j web3 = Web3j.build(new HttpService("ws://192.168.3.102:8552"));
			// Web3j web3 = Web3j.build(new HttpService("http://192.168.3.102:8551"));
			Web3j web3 = Web3j.build(new HttpService());
			Web3ClientVersion web3ClientVersion;
			web3ClientVersion = web3.web3ClientVersion().send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println("version: " + clientVersion);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

