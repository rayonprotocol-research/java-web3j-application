package web3j;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.0.1.
 */
public class KeyValue extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506103c1806100206000396000f3fe608060405260043610610050577c0100000000000000000000000000000000000000000000000000000000600035046313576dc8811461005557806342de95981461009e578063d8a26e3a146100d8575b600080fd5b34801561006157600080fd5b506100856004803603602081101561007857600080fd5b503563ffffffff16610108565b6040805163ffffffff9092168252519081900360200190f35b3480156100aa57600080fd5b506100d6600480360360408110156100c157600080fd5b5063ffffffff81358116916020013516610120565b005b3480156100e457600080fd5b50610085600480360360208110156100fb57600080fd5b503563ffffffff166102ac565b60006020819052908152604090205463ffffffff1681565b63ffffffff8216151561017d576040805160e560020a62461bcd02815260206004820152600f60248201527f6b65792063616e6e6f7420626520300000000000000000000000000000000000604482015290519081900360640190fd5b63ffffffff811615156101da576040805160e560020a62461bcd02815260206004820152601160248201527f76616c75652063616e6e6f742062652030000000000000000000000000000000604482015290519081900360640190fd5b63ffffffff8083166000908152602081905260409020541615610247576040805160e560020a62461bcd02815260206004820152601b60248201527f6b6579206d757374206e6f7420636f6e7461696e20696e206d61700000000000604482015290519081900360640190fd5b63ffffffff82811660008181526020818152604091829020805463ffffffff19169486169485179055815192835282019290925281517f7e5d56cd58fecda0b3ebe87c1179236af1afac32c752fd6375db0957450651a2929181900390910190a15050565b600063ffffffff8216151561030b576040805160e560020a62461bcd02815260206004820152600f60248201527f6b65792063616e6e6f7420626520300000000000000000000000000000000000604482015290519081900360640190fd5b63ffffffff808316600090815260208190526040902054161515610379576040805160e560020a62461bcd02815260206004820152601760248201527f6b6579206d75737420636f6e7461696e20696e206d6170000000000000000000604482015290519081900360640190fd5b5063ffffffff908116600090815260208190526040902054169056fea165627a7a72305820a10dcfaf413428362626e6e2a1542b5175f89ab14ec26dbd21144b797663f8c20029";

    public static final String FUNC_MAP = "map";

    public static final String FUNC_PUT = "put";

    public static final String FUNC_GET = "get";

    public static final Event VALUEPUT_EVENT = new Event("ValuePut", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}));
    ;

    @Deprecated
    protected KeyValue(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected KeyValue(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected KeyValue(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected KeyValue(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> map(BigInteger param0) {
        final Function function = new Function(FUNC_MAP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> put(BigInteger _key, BigInteger _value) {
        final Function function = new Function(
                FUNC_PUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_key), 
                new org.web3j.abi.datatypes.generated.Uint32(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> get(BigInteger _key) {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public List<ValuePutEventResponse> getValuePutEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VALUEPUT_EVENT, transactionReceipt);
        ArrayList<ValuePutEventResponse> responses = new ArrayList<ValuePutEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ValuePutEventResponse typedResponse = new ValuePutEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.key = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ValuePutEventResponse> valuePutEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ValuePutEventResponse>() {
            @Override
            public ValuePutEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VALUEPUT_EVENT, log);
                ValuePutEventResponse typedResponse = new ValuePutEventResponse();
                typedResponse.log = log;
                typedResponse.key = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ValuePutEventResponse> valuePutEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALUEPUT_EVENT));
        return valuePutEventFlowable(filter);
    }

    @Deprecated
    public static KeyValue load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new KeyValue(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static KeyValue load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new KeyValue(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static KeyValue load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new KeyValue(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static KeyValue load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new KeyValue(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<KeyValue> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(KeyValue.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<KeyValue> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(KeyValue.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<KeyValue> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(KeyValue.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<KeyValue> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(KeyValue.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ValuePutEventResponse {
        public Log log;

        public BigInteger key;

        public BigInteger value;
    }
}
