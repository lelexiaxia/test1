package com.jlwl.controller;

import com.alipay.api.AlipayApiException;
import com.jlwl.service.AlipayService;
import com.jlwl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/alipay")
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    /**
     * 创建支付订单
     */
    @PostMapping("/create")
    public R createPayment(@RequestParam String amount, @RequestParam String subject) {
        try {
            String orderId = alipayService.generateOrderId();
            String form = alipayService.createPayment(orderId, amount, subject);
            return R.ok().put("form", form).put("orderId", orderId);
        } catch (AlipayApiException e) {
            return R.error("创建支付订单失败：" + e.getMessage());
        }
    }

    /**
     * 支付宝异步通知
     */
    @PostMapping("/notify")
    public String notifyUrl(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        // TODO: 验证签名
        // TODO: 更新订单状态
        return "success";
    }

    /**
     * 支付宝同步通知
     */
    @GetMapping("/return")
    public String returnUrl(HttpServletRequest request) {
        // TODO: 处理支付成功后的页面跳转
        return "支付成功";
    }
} 