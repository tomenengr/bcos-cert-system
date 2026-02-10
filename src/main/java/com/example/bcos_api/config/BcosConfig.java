package com.example.bcos_api.config;

import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BcosConfig {

    // 配置文件路径 (开发环境直接读取 resources 下的文件)
    private static final String CONFIG_FILE = "src/main/resources/conf/config.toml";

    // 1. 初始化 SDK (把它变成一个 Bean，全剧通用)
    @Bean
    public BcosSDK bcosSDK() {
        System.out.println("====== BcosSDK Init Start ======");
        try {
            BcosSDK sdk = BcosSDK.build(CONFIG_FILE);
            System.out.println("====== BcosSDK Init Success ======");
            return sdk;
        } catch (Exception e) {
            System.err.println("BcosSDK Init Failed: " + e.getMessage());
            throw e;
        }
    }

    // 2. 初始化 Client (连接群组 group0)
    @Bean
    public Client client(BcosSDK sdk) {
        return sdk.getClient("group0");
    }

    // 3. 初始化 Admin 账户 (直接加载默认账户)
    @Bean
    public CryptoKeyPair adminKeyPair(Client client) {
        return client.getCryptoSuite().getCryptoKeyPair();
    }
}
