package com.jlwl.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.jlwl.config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AlipayService {

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * 创建支付宝支付订单
     * @param orderId 订单ID
     * @param amount 支付金额
     * @param subject 订单标题
     * @return 支付表单HTML
     */
    public String createPayment(String orderId, String amount, String subject) throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(alipayConfig.getNotifyUrl());
        request.setReturnUrl(alipayConfig.getReturnUrl());

        // 构建请求参数
        String bizContent = "{" +
                "\"out_trade_no\":\"" + orderId + "\"," +
                "\"total_amount\":\"" + amount + "\"," +
                "\"subject\":\"" + subject + "\"," +
                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"" +
                "}";
        request.setBizContent(bizContent);

        // 调用支付宝接口
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        if (response.isSuccess()) {
            return response.getBody();
        } else {
            throw new RuntimeException("创建支付宝支付订单失败");
        }
    }

    /**
     * 生成订单号
     */
    public String generateOrderId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
} 