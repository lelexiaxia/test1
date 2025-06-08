package com.jlwl.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayConfig {
    
    @Value("${alipay.appId}")
    private String appId;
    
    @Value("${alipay.privateKey}")
    private String privateKey;
    
    @Value("${alipay.publicKey}")
    private String publicKey;
    
    @Value("${alipay.notifyUrl}")
    private String notifyUrl;
    
    @Value("${alipay.returnUrl}")
    private String returnUrl;
    
    @Value("${alipay.gateway}")
    private String gateway;
    
    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(gateway, appId, privateKey, "json", "UTF-8", publicKey, "RSA2");
    }
    
    // getter方法
    public String getAppId() {
        return appId;
    }
    
    public String getPrivateKey() {
        return privateKey;
    }
    
    public String getPublicKey() {
        return publicKey;
    }
    
    public String getNotifyUrl() {
        return notifyUrl;
    }
    
    public String getReturnUrl() {
        return returnUrl;
    }
    
    public String getGateway() {
        return gateway;
    }
} 