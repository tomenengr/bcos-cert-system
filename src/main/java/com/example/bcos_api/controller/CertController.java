package com.example.bcos_api.controller;

import com.example.bcos_api.contract.Certification;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/cert")
public class CertController {

    @Autowired
    private Client client;

    @Autowired
    private CryptoKeyPair adminKeyPair;

    // 核心改进：尝试从配置文件读取地址
    // 如果 application.properties 里没写，它就是空字符串 ""
    @Value("${system.contract.address:}")
    private String contractAddress;

    // 1. 部署合约 (Deploy)
    @GetMapping("/deploy")
    public String deploy() {
        try {
            System.out.println("Start deploying contract...");
            Certification cert = Certification.deploy(client, adminKeyPair);
            
            // 更新内存中的地址
            this.contractAddress = cert.getContractAddress();
            
            System.out.println("Deploy Success: " + this.contractAddress);
            return "Deploy Success! Address: " + this.contractAddress + " (Please save this to application.properties)";
        } catch (Exception e) {
            e.printStackTrace();
            return "Deploy Failed: " + e.getMessage();
        }
    }

    // 2. 颁发证书 (Issue)
    @PostMapping("/issue")
    public String issue(@RequestParam String id, @RequestParam String name, @RequestParam String course) {
        if (contractAddress == null || contractAddress.isEmpty()) {
            return "Error: Contract not deployed! Please call /deploy first or set address in config.";
        }
        
        try {
            // 加载合约
            Certification cert = Certification.load(contractAddress, client, adminKeyPair);
            String date = LocalDate.now().toString();
            String issuer = "FISCO University";
            
            // --- 核心修复点开始 ---
            // SDK V3 写入数据不抛出 ContractException，而是返回回执
            TransactionReceipt receipt = cert.issue(id, name, course, date, issuer);
            
            // 检查链上执行状态 (0代表成功)
            if (receipt.isStatusOK()) {
                return "Issue Success! Cert ID: " + id;
            } else {
                // 如果是重复颁发，Solidity 的 require 报错信息会在 message 里
                return "Issue Failed on Chain: " + receipt.getMessage();
            }
            // --- 核心修复点结束 ---
            
        } catch (Exception e) {
            e.printStackTrace();
            return "System Error: " + e.getMessage();
        }
    }

    // 3. 核验证书 (Verify)
    @GetMapping("/verify")
    public Object verify(@RequestParam String id) {
        if (contractAddress == null || contractAddress.isEmpty()) {
            return "Error: Contract not ready!";
        }

        try {
            Certification cert = Certification.load(contractAddress, client, adminKeyPair);
            
            // 调用查询接口 (Solidity view/pure 方法)
            Tuple6<String, String, String, String, String, Boolean> result = cert.verify(id);
            
            // 封装成 JSON 对象返回
            return new CertData(
                result.getValue1(), // id
                result.getValue2(), // name
                result.getValue3(), // course
                result.getValue4(), // date
                result.getValue5(), // issuer
                result.getValue6()  // isValid
            );
        } catch (Exception e) {
            // 查询不到或者是空证书
            e.printStackTrace();
            return "Verification Failed: Cert Not Found";
        }
    }

    // 内部类：用于 JSON 格式化输出
    static class CertData {
        public String id;
        public String name;
        public String course;
        public String date;
        public String issuer;
        public boolean isValid;

        public CertData(String id, String name, String course, String date, String issuer, boolean isValid) {
            this.id = id;
            this.name = name;
            this.course = course;
            this.date = date;
            this.issuer = issuer;
            this.isValid = isValid;
        }
    }
}