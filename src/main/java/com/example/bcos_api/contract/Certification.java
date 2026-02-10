package com.example.bcos_api.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.Bool;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple5;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple6;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.eventsub.EventSubCallback;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Certification extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50610a84806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80630dfb1e861461004657806365b2a8631461005b578063bb9c6c3e1461006e575b600080fd5b6100596100543660046107e7565b61009c565b005b6100596100693660046108b9565b610269565b61008161007c3660046108b9565b610312565b60405161009396959493929190610952565b60405180910390f35b6000856040516100ac91906109c9565b90815260405190819003602001902080546100c6906109e5565b15905061011a5760405162461bcd60e51b815260206004820152601e60248201527f436572746966696361746520494420616c72656164792065786973747321000060448201526064015b60405180910390fd5b60006040518060c001604052808781526020018681526020018581526020018481526020018381526020016001151581525090508060008760405161015f91906109c9565b908152602001604051809103902060008201518160000190805190602001906101899291906106ab565b5060208281015180516101a292600185019201906106ab565b50604082015180516101be9160028401916020909101906106ab565b50606082015180516101da9160038401916020909101906106ab565b50608082015180516101f69160048401916020909101906106ab565b5060a091909101516005909101805460ff19169115159190911790556040516102209087906109c9565b60405180910390207fb9ab3c382b93e502b23e9a7d057189047feefeb7bae293a84d714a3f1e1773d08684604051610259929190610a20565b60405180910390a2505050505050565b60008160405161027991906109c9565b9081526040519081900360200190208054610293906109e5565b151590506102db5760405162461bcd60e51b815260206004820152601560248201527410d95c9d1a599a58d85d19481b9bdd08199bdd5b99605a1b6044820152606401610111565b600080826040516102ec91906109c9565b908152604051908190036020019020600501805491151560ff1990921691909117905550565b606080606080606060008060008860405161032d91906109c9565b90815260200160405180910390206040518060c0016040529081600082018054610356906109e5565b80601f0160208091040260200160405190810160405280929190818152602001828054610382906109e5565b80156103cf5780601f106103a4576101008083540402835291602001916103cf565b820191906000526020600020905b8154815290600101906020018083116103b257829003601f168201915b505050505081526020016001820180546103e8906109e5565b80601f0160208091040260200160405190810160405280929190818152602001828054610414906109e5565b80156104615780601f1061043657610100808354040283529160200191610461565b820191906000526020600020905b81548152906001019060200180831161044457829003601f168201915b5050505050815260200160028201805461047a906109e5565b80601f01602080910402602001604051908101604052809291908181526020018280546104a6906109e5565b80156104f35780601f106104c8576101008083540402835291602001916104f3565b820191906000526020600020905b8154815290600101906020018083116104d657829003601f168201915b5050505050815260200160038201805461050c906109e5565b80601f0160208091040260200160405190810160405280929190818152602001828054610538906109e5565b80156105855780601f1061055a57610100808354040283529160200191610585565b820191906000526020600020905b81548152906001019060200180831161056857829003601f168201915b5050505050815260200160048201805461059e906109e5565b80601f01602080910402602001604051908101604052809291908181526020018280546105ca906109e5565b80156106175780601f106105ec57610100808354040283529160200191610617565b820191906000526020600020905b8154815290600101906020018083116105fa57829003601f168201915b50505091835250506005919091015460ff16151560209091015280515190915061067b5760405162461bcd60e51b815260206004820152601560248201527410d95c9d1a599a58d85d19481b9bdd08199bdd5b99605a1b6044820152606401610111565b8051602082015160408301516060840151608085015160a090950151939c929b5090995097509195509350915050565b8280546106b7906109e5565b90600052602060002090601f0160209004810192826106d9576000855561071f565b82601f106106f257805160ff191683800117855561071f565b8280016001018555821561071f579182015b8281111561071f578251825591602001919060010190610704565b5061072b92915061072f565b5090565b5b8082111561072b5760008155600101610730565b634e487b7160e01b600052604160045260246000fd5b600082601f83011261076b57600080fd5b813567ffffffffffffffff8082111561078657610786610744565b604051601f8301601f19908116603f011681019082821181831017156107ae576107ae610744565b816040528381528660208588010111156107c757600080fd5b836020870160208301376000602085830101528094505050505092915050565b600080600080600060a086880312156107ff57600080fd5b853567ffffffffffffffff8082111561081757600080fd5b61082389838a0161075a565b9650602088013591508082111561083957600080fd5b61084589838a0161075a565b9550604088013591508082111561085b57600080fd5b61086789838a0161075a565b9450606088013591508082111561087d57600080fd5b61088989838a0161075a565b9350608088013591508082111561089f57600080fd5b506108ac8882890161075a565b9150509295509295909350565b6000602082840312156108cb57600080fd5b813567ffffffffffffffff8111156108e257600080fd5b6108ee8482850161075a565b949350505050565b60005b838110156109115781810151838201526020016108f9565b83811115610920576000848401525b50505050565b6000815180845261093e8160208601602086016108f6565b601f01601f19169290920160200192915050565b60c08152600061096560c0830189610926565b82810360208401526109778189610926565b9050828103604084015261098b8188610926565b9050828103606084015261099f8187610926565b905082810360808401526109b38186610926565b91505082151560a0830152979650505050505050565b600082516109db8184602087016108f6565b9190910192915050565b600181811c908216806109f957607f821691505b60208210811415610a1a57634e487b7160e01b600052602260045260246000fd5b50919050565b604081526000610a336040830185610926565b8281036020840152610a458185610926565b9594505050505056fea26469706673582212207fbbb17a9cd505bcd7b6a5e784f0ea9b9143d9e0739747ac7580edb9e00c45d164736f6c634300080b0033"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50610a87806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80630490fef914610046578063959d6fee1461005b5780639f2450691461006e575b600080fd5b6100596100543660046107ea565b61009c565b005b6100596100693660046108bc565b61026a565b61008161007c3660046108bc565b610314565b60405161009396959493929190610955565b60405180910390f35b6000856040516100ac91906109cc565b90815260405190819003602001902080546100c6906109e8565b15905061011b57604051636381e58960e11b815260206004820152601e60248201527f436572746966696361746520494420616c72656164792065786973747321000060448201526064015b60405180910390fd5b60006040518060c001604052808781526020018681526020018581526020018481526020018381526020016001151581525090508060008760405161016091906109cc565b9081526020016040518091039020600082015181600001908051906020019061018a9291906106ae565b5060208281015180516101a392600185019201906106ae565b50604082015180516101bf9160028401916020909101906106ae565b50606082015180516101db9160038401916020909101906106ae565b50608082015180516101f79160048401916020909101906106ae565b5060a091909101516005909101805460ff19169115159190911790556040516102219087906109cc565b60405180910390207fcb670b92d0605996a4bc3dab9b6f3340d05de5b213573ba446fb885c94ec4193868460405161025a929190610a23565b60405180910390a2505050505050565b60008160405161027a91906109cc565b9081526040519081900360200190208054610294906109e8565b151590506102dd57604051636381e58960e11b815260206004820152601560248201527410d95c9d1a599a58d85d19481b9bdd08199bdd5b99605a1b6044820152606401610112565b600080826040516102ee91906109cc565b908152604051908190036020019020600501805491151560ff1990921691909117905550565b606080606080606060008060008860405161032f91906109cc565b90815260200160405180910390206040518060c0016040529081600082018054610358906109e8565b80601f0160208091040260200160405190810160405280929190818152602001828054610384906109e8565b80156103d15780601f106103a6576101008083540402835291602001916103d1565b820191906000526020600020905b8154815290600101906020018083116103b457829003601f168201915b505050505081526020016001820180546103ea906109e8565b80601f0160208091040260200160405190810160405280929190818152602001828054610416906109e8565b80156104635780601f1061043857610100808354040283529160200191610463565b820191906000526020600020905b81548152906001019060200180831161044657829003601f168201915b5050505050815260200160028201805461047c906109e8565b80601f01602080910402602001604051908101604052809291908181526020018280546104a8906109e8565b80156104f55780601f106104ca576101008083540402835291602001916104f5565b820191906000526020600020905b8154815290600101906020018083116104d857829003601f168201915b5050505050815260200160038201805461050e906109e8565b80601f016020809104026020016040519081016040528092919081815260200182805461053a906109e8565b80156105875780601f1061055c57610100808354040283529160200191610587565b820191906000526020600020905b81548152906001019060200180831161056a57829003601f168201915b505050505081526020016004820180546105a0906109e8565b80601f01602080910402602001604051908101604052809291908181526020018280546105cc906109e8565b80156106195780601f106105ee57610100808354040283529160200191610619565b820191906000526020600020905b8154815290600101906020018083116105fc57829003601f168201915b50505091835250506005919091015460ff16151560209091015280515190915061067e57604051636381e58960e11b815260206004820152601560248201527410d95c9d1a599a58d85d19481b9bdd08199bdd5b99605a1b6044820152606401610112565b8051602082015160408301516060840151608085015160a090950151939c929b5090995097509195509350915050565b8280546106ba906109e8565b90600052602060002090601f0160209004810192826106dc5760008555610722565b82601f106106f557805160ff1916838001178555610722565b82800160010185558215610722579182015b82811115610722578251825591602001919060010190610707565b5061072e929150610732565b5090565b5b8082111561072e5760008155600101610733565b63b95aa35560e01b600052604160045260246000fd5b600082601f83011261076e57600080fd5b813567ffffffffffffffff8082111561078957610789610747565b604051601f8301601f19908116603f011681019082821181831017156107b1576107b1610747565b816040528381528660208588010111156107ca57600080fd5b836020870160208301376000602085830101528094505050505092915050565b600080600080600060a0868803121561080257600080fd5b853567ffffffffffffffff8082111561081a57600080fd5b61082689838a0161075d565b9650602088013591508082111561083c57600080fd5b61084889838a0161075d565b9550604088013591508082111561085e57600080fd5b61086a89838a0161075d565b9450606088013591508082111561088057600080fd5b61088c89838a0161075d565b935060808801359150808211156108a257600080fd5b506108af8882890161075d565b9150509295509295909350565b6000602082840312156108ce57600080fd5b813567ffffffffffffffff8111156108e557600080fd5b6108f18482850161075d565b949350505050565b60005b838110156109145781810151838201526020016108fc565b83811115610923576000848401525b50505050565b600081518084526109418160208601602086016108f9565b601f01601f19169290920160200192915050565b60c08152600061096860c0830189610929565b828103602084015261097a8189610929565b9050828103604084015261098e8188610929565b905082810360608401526109a28187610929565b905082810360808401526109b68186610929565b91505082151560a0830152979650505050505050565b600082516109de8184602087016108f9565b9190910192915050565b600181811c908216806109fc57607f821691505b60208210811415610a1d5763b95aa35560e01b600052602260045260246000fd5b50919050565b604081526000610a366040830185610929565b8281036020840152610a488185610929565b9594505050505056fea26469706673582212207113006fd5fdf3a1eadeb85816b8c42a7928b79b346e4b078a37403f238fb7e464736f6c634300080b0033"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"string\",\"name\":\"id\",\"type\":\"string\"},{\"indexed\":false,\"internalType\":\"string\",\"name\":\"name\",\"type\":\"string\"},{\"indexed\":false,\"internalType\":\"string\",\"name\":\"issuer\",\"type\":\"string\"}],\"name\":\"IssueCert\",\"type\":\"event\"},{\"conflictFields\":[{\"kind\":3,\"slot\":0,\"value\":[0]}],\"inputs\":[{\"internalType\":\"string\",\"name\":\"_id\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_name\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_course\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_date\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_issuer\",\"type\":\"string\"}],\"name\":\"issue\",\"outputs\":[],\"selector\":[234561158,76611321],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":3,\"slot\":0,\"value\":[0]}],\"inputs\":[{\"internalType\":\"string\",\"name\":\"_id\",\"type\":\"string\"}],\"name\":\"revoke\",\"outputs\":[],\"selector\":[1706207331,2510122990],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":3,\"slot\":0,\"value\":[0]}],\"inputs\":[{\"internalType\":\"string\",\"name\":\"_id\",\"type\":\"string\"}],\"name\":\"verify\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"selector\":[3147590718,2669957225],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ISSUE = "issue";

    public static final String FUNC_REVOKE = "revoke";

    public static final String FUNC_VERIFY = "verify";

    public static final Event ISSUECERT_EVENT = new Event("IssueCert", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected Certification(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<IssueCertEventResponse> getIssueCertEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ISSUECERT_EVENT, transactionReceipt);
        ArrayList<IssueCertEventResponse> responses = new ArrayList<IssueCertEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IssueCertEventResponse typedResponse = new IssueCertEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.issuer = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeIssueCertEvent(BigInteger fromBlock, BigInteger toBlock,
            List<String> otherTopics, EventSubCallback callback) {
        String topic0 = eventEncoder.encode(ISSUECERT_EVENT);
        subscribeEvent(topic0,otherTopics,fromBlock,toBlock,callback);
    }

    public void subscribeIssueCertEvent(EventSubCallback callback) {
        String topic0 = eventEncoder.encode(ISSUECERT_EVENT);
        subscribeEvent(topic0,callback);
    }

    public TransactionReceipt issue(String _id, String _name, String _course, String _date,
            String _issuer) {
        final Function function = new Function(
                FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_id), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_name), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_course), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_date), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_issuer)), 
                Collections.<TypeReference<?>>emptyList(), 4);
        return executeTransaction(function);
    }

    public Function getMethodIssueRawFunction(String _id, String _name, String _course,
            String _date, String _issuer) throws ContractException {
        final Function function = new Function(FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_id), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_name), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_course), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_date), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_issuer)), 
                Arrays.<TypeReference<?>>asList());
        return function;
    }

    public String getSignedTransactionForIssue(String _id, String _name, String _course,
            String _date, String _issuer) {
        final Function function = new Function(
                FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_id), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_name), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_course), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_date), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_issuer)), 
                Collections.<TypeReference<?>>emptyList(), 4);
        return createSignedTransaction(function);
    }

    public String issue(String _id, String _name, String _course, String _date, String _issuer,
            TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_id), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_name), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_course), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_date), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_issuer)), 
                Collections.<TypeReference<?>>emptyList(), 4);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple5<String, String, String, String, String> getIssueInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ISSUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple5<String, String, String, String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue(), 
                (String) results.get(4).getValue()
                );
    }

    public TransactionReceipt revoke(String _id) {
        final Function function = new Function(
                FUNC_REVOKE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_id)), 
                Collections.<TypeReference<?>>emptyList(), 4);
        return executeTransaction(function);
    }

    public Function getMethodRevokeRawFunction(String _id) throws ContractException {
        final Function function = new Function(FUNC_REVOKE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_id)), 
                Arrays.<TypeReference<?>>asList());
        return function;
    }

    public String getSignedTransactionForRevoke(String _id) {
        final Function function = new Function(
                FUNC_REVOKE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_id)), 
                Collections.<TypeReference<?>>emptyList(), 4);
        return createSignedTransaction(function);
    }

    public String revoke(String _id, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_REVOKE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_id)), 
                Collections.<TypeReference<?>>emptyList(), 4);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple1<String> getRevokeInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REVOKE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple6<String, String, String, String, String, Boolean> verify(String _id) throws
            ContractException {
        final Function function = new Function(FUNC_VERIFY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple6<String, String, String, String, String, Boolean>(
                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue(), 
                (String) results.get(4).getValue(), 
                (Boolean) results.get(5).getValue());
    }

    public Function getMethodVerifyRawFunction(String _id) throws ContractException {
        final Function function = new Function(FUNC_VERIFY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        return function;
    }

    public static Certification load(String contractAddress, Client client,
            CryptoKeyPair credential) {
        return new Certification(contractAddress, client, credential);
    }

    public static Certification deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(Certification.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class IssueCertEventResponse {
        public TransactionReceipt.Logs log;

        public byte[] id;

        public String name;

        public String issuer;
    }
}
