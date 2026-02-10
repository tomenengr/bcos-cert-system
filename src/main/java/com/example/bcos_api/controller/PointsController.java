package com.example.bcos_api.controller;

import com.example.bcos_api.contract.Points;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class PointsController {

    @Autowired
    private Client client;

    @Autowired
    private CryptoKeyPair adminKeyPair;

    // 用来暂存部署好的合约对象
    private Points pointsContract;

    // 接口 1: 部署合约
    // 访问: http://localhost:8080/deploy
    @GetMapping("/deploy")
    public String deploy() {
        try {
            System.out.println("Start deploying contract...");
            pointsContract = Points.deploy(client, adminKeyPair);
            String address = pointsContract.getContractAddress();
            System.out.println("Deploy Success: " + address);
            return "Deploy Success! Address: " + address;
        } catch (Exception e) {
            e.printStackTrace();
            return "Deploy Failed: " + e.getMessage();
        }
    }

    // 接口 2: 发行积分
    // 访问: http://localhost:8080/issue?amount=100
    @GetMapping("/issue")
    public String issue(@RequestParam BigInteger amount) {
        if (pointsContract == null) {
            return "Error: Please deploy contract first!";
        }
        try {
            // 发给自己 (Admin)
            String toAddress = adminKeyPair.getAddress();
            pointsContract.issue(toAddress, amount);
            
            // 查余额确认
            BigInteger balance = pointsContract.balanceOf(toAddress);
            return "Issue Success! Sent " + amount + " to " + toAddress + ". Current Balance: " + balance;
        } catch (Exception e) {
            e.printStackTrace();
            return "Issue Failed: " + e.getMessage();
        }
    }
}