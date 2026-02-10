package com.example.bcos_api.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.eventsub.EventSubCallback;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Points extends Contract {
    public static final String[] BINARY_ARRAY = {
        "608060405234801561001057600080fd5b50600180546001600160a01b031916331790556103a7806100326000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c806327e235e31461005c57806370a082311461008f578063867904b4146100b8578063a9059cbb146100cd578063f851a440146100e0575b600080fd5b61007c61006a3660046102e0565b60006020819052908152604090205481565b6040519081526020015b60405180910390f35b61007c61009d3660046102e0565b6001600160a01b031660009081526020819052604090205490565b6100cb6100c6366004610302565b61010b565b005b6100cb6100db366004610302565b6101d9565b6001546100f3906001600160a01b031681565b6040516001600160a01b039091168152602001610086565b6001546001600160a01b0316331461016a5760405162461bcd60e51b815260206004820152601b60248201527f4f6e6c792061646d696e2063616e20697373756520706f696e7473000000000060448201526064015b60405180910390fd5b6001600160a01b03821660009081526020819052604081208054839290610192908490610342565b90915550506040518181526001600160a01b038316907fc65a3f767206d2fdcede0b094a4840e01c0dd0be1888b5ba800346eaa0123c169060200160405180910390a25050565b3360009081526020819052604090205481111561022f5760405162461bcd60e51b8152602060048201526014602482015273496e73756666696369656e742062616c616e636560601b6044820152606401610161565b336000908152602081905260408120805483929061024e90849061035a565b90915550506001600160a01b0382166000908152602081905260408120805483929061027b908490610342565b90915550506040518181526001600160a01b0383169033907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9060200160405180910390a35050565b80356001600160a01b03811681146102db57600080fd5b919050565b6000602082840312156102f257600080fd5b6102fb826102c4565b9392505050565b6000806040838503121561031557600080fd5b61031e836102c4565b946020939093013593505050565b634e487b7160e01b600052601160045260246000fd5b600082198211156103555761035561032c565b500190565b60008282101561036c5761036c61032c565b50039056fea2646970667358221220308c240bfd67d589c3b196d6bb31bda2a1b247c5fe9a37daf7f5da545290ebb664736f6c634300080b0033"
    };

    public static final String BINARY =
            org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {
        "608060405234801561001057600080fd5b50600180546001600160a01b031916331790556103a7806100326000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c806327e235e31461005c57806370a082311461008f578063867904b4146100b8578063a9059cbb146100cd578063f851a440146100e0575b600080fd5b61007c61006a3660046102e0565b60006020819052908152604090205481565b6040519081526020015b60405180910390f35b61007c61009d3660046102e0565b6001600160a01b031660009081526020819052604090205490565b6100cb6100c6366004610302565b61010b565b005b6100cb6100db366004610302565b6101d9565b6001546100f3906001600160a01b031681565b6040516001600160a01b039091168152602001610086565b6001546001600160a01b0316331461016a5760405162461bcd60e51b815260206004820152601b60248201527f4f6e6c792061646d696e2063616e20697373756520706f696e7473000000000060448201526064015b60405180910390fd5b6001600160a01b03821660009081526020819052604081208054839290610192908490610342565b90915550506040518181526001600160a01b038316907fc65a3f767206d2fdcede0b094a4840e01c0dd0be1888b5ba800346eaa0123c169060200160405180910390a25050565b3360009081526020819052604090205481111561022f5760405162461bcd60e51b8152602060048201526014602482015273496e73756666696369656e742062616c616e636560601b6044820152606401610161565b336000908152602081905260408120805483929061024e90849061035a565b90915550506001600160a01b0382166000908152602081905260408120805483929061027b908490610342565b90915550506040518181526001600160a01b0383169033907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9060200160405180910390a35050565b80356001600160a01b03811681146102db57600080fd5b919050565b6000602082840312156102f257600080fd5b6102fb826102c4565b9392505050565b6000806040838503121561031557600080fd5b61031e836102c4565b946020939093013593505050565b634e487b7160e01b600052601160045260246000fd5b600082198211156103555761035561032c565b500190565b60008282101561036c5761036c61032c565b50039056fea2646970667358221220308c240bfd67d589c3b196d6bb31bda2a1b247c5fe9a37daf7f5da545290ebb664736f6c634300080b0033"
    };

    public static final String SM_BINARY =
            org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {
        "[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Issue\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Transfer\",\"type\":\"event\"},{\"inputs\":[],\"name\":\"admin\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"account\",\"type\":\"address\"}],\"name\":\"balanceOf\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"balances\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"receiver\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"issue\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"receiver\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"transfer\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"
    };

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ADMIN = "admin";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_ISSUE = "issue";

    public static final String FUNC_TRANSFER = "transfer";

    public static final Event ISSUE_EVENT =
            new Event(
                    "Issue",
                    Arrays.<TypeReference<?>>asList(
                            new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));;

    public static final Event TRANSFER_EVENT =
            new Event(
                    "Transfer",
                    Arrays.<TypeReference<?>>asList(
                            new TypeReference<Address>(true) {},
                            new TypeReference<Address>(true) {},
                            new TypeReference<Uint256>() {}));;

    protected Points(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<IssueEventResponse> getIssueEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList =
                extractEventParametersWithLog(ISSUE_EVENT, transactionReceipt);
        ArrayList<IssueEventResponse> responses =
                new ArrayList<IssueEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IssueEventResponse typedResponse = new IssueEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.to = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeIssueEvent(
            BigInteger fromBlock,
            BigInteger toBlock,
            List<String> otherTopics,
            EventSubCallback callback) {
        String topic0 = eventEncoder.encode(ISSUE_EVENT);
        subscribeEvent(topic0, otherTopics, fromBlock, toBlock, callback);
    }

    public void subscribeIssueEvent(EventSubCallback callback) {
        String topic0 = eventEncoder.encode(ISSUE_EVENT);
        subscribeEvent(topic0, callback);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList =
                extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses =
                new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeTransferEvent(
            BigInteger fromBlock,
            BigInteger toBlock,
            List<String> otherTopics,
            EventSubCallback callback) {
        String topic0 = eventEncoder.encode(TRANSFER_EVENT);
        subscribeEvent(topic0, otherTopics, fromBlock, toBlock, callback);
    }

    public void subscribeTransferEvent(EventSubCallback callback) {
        String topic0 = eventEncoder.encode(TRANSFER_EVENT);
        subscribeEvent(topic0, callback);
    }

    public String admin() throws ContractException {
        final Function function =
                new Function(
                        FUNC_ADMIN,
                        Arrays.<Type>asList(),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public Function getMethodAdminRawFunction() throws ContractException {
        final Function function =
                new Function(
                        FUNC_ADMIN,
                        Arrays.<Type>asList(),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return function;
    }

    public BigInteger balanceOf(String account) throws ContractException {
        final Function function =
                new Function(
                        FUNC_BALANCEOF,
                        Arrays.<Type>asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(account)),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Function getMethodBalanceOfRawFunction(String account) throws ContractException {
        final Function function =
                new Function(
                        FUNC_BALANCEOF,
                        Arrays.<Type>asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(account)),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return function;
    }

    public BigInteger balances(String param0) throws ContractException {
        final Function function =
                new Function(
                        FUNC_BALANCES,
                        Arrays.<Type>asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(param0)),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Function getMethodBalancesRawFunction(String param0) throws ContractException {
        final Function function =
                new Function(
                        FUNC_BALANCES,
                        Arrays.<Type>asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(param0)),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return function;
    }

    /**
     * @return TransactionReceipt Get more transaction info (e.g. txhash, block) from
     *     TransactionReceipt
     */
    public TransactionReceipt issue(String receiver, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_ISSUE,
                        Arrays.<Type>asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(receiver),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.<TypeReference<?>>emptyList(),
                        0);
        return executeTransaction(function);
    }

    public Function getMethodIssueRawFunction(String receiver, BigInteger amount)
            throws ContractException {
        final Function function =
                new Function(
                        FUNC_ISSUE,
                        Arrays.<Type>asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(receiver),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Arrays.<TypeReference<?>>asList());
        return function;
    }

    public String getSignedTransactionForIssue(String receiver, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_ISSUE,
                        Arrays.<Type>asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(receiver),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.<TypeReference<?>>emptyList(),
                        0);
        return createSignedTransaction(function);
    }

    /**
     * @param callback Get TransactionReceipt from TransactionCallback onResponse(TransactionReceipt
     *     receipt)
     * @return txHash Transaction hash of current transaction call
     */
    public String issue(String receiver, BigInteger amount, TransactionCallback callback) {
        final Function function =
                new Function(
                        FUNC_ISSUE,
                        Arrays.<Type>asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(receiver),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.<TypeReference<?>>emptyList(),
                        0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, BigInteger> getIssueInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function =
                new Function(
                        FUNC_ISSUE,
                        Arrays.<Type>asList(),
                        Arrays.<TypeReference<?>>asList(
                                new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(
                (String) results.get(0).getValue(), (BigInteger) results.get(1).getValue());
    }

    /**
     * @return TransactionReceipt Get more transaction info (e.g. txhash, block) from
     *     TransactionReceipt
     */
    public TransactionReceipt transfer(String receiver, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_TRANSFER,
                        Arrays.<Type>asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(receiver),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.<TypeReference<?>>emptyList(),
                        0);
        return executeTransaction(function);
    }

    public Function getMethodTransferRawFunction(String receiver, BigInteger amount)
            throws ContractException {
        final Function function =
                new Function(
                        FUNC_TRANSFER,
                        Arrays.<Type>asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(receiver),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Arrays.<TypeReference<?>>asList());
        return function;
    }

    public String getSignedTransactionForTransfer(String receiver, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_TRANSFER,
                        Arrays.<Type>asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(receiver),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.<TypeReference<?>>emptyList(),
                        0);
        return createSignedTransaction(function);
    }

    /**
     * @param callback Get TransactionReceipt from TransactionCallback onResponse(TransactionReceipt
     *     receipt)
     * @return txHash Transaction hash of current transaction call
     */
    public String transfer(String receiver, BigInteger amount, TransactionCallback callback) {
        final Function function =
                new Function(
                        FUNC_TRANSFER,
                        Arrays.<Type>asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(receiver),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.<TypeReference<?>>emptyList(),
                        0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, BigInteger> getTransferInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function =
                new Function(
                        FUNC_TRANSFER,
                        Arrays.<Type>asList(),
                        Arrays.<TypeReference<?>>asList(
                                new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(
                (String) results.get(0).getValue(), (BigInteger) results.get(1).getValue());
    }

    public static Points load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Points(contractAddress, client, credential);
    }

    public static Points deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(
                Points.class,
                client,
                credential,
                getBinary(client.getCryptoSuite()),
                getABI(),
                null,
                null);
    }

    public static class IssueEventResponse {
        public TransactionReceipt.Logs log;

        public String to;

        public BigInteger value;
    }

    public static class TransferEventResponse {
        public TransactionReceipt.Logs log;

        public String from;

        public String to;

        public BigInteger value;
    }
}
