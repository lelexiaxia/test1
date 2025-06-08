package com.jlwl.controller;

import com.jlwl.entity.OrdersEntity;
import com.jlwl.service.SimplePayService;
import com.jlwl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/pay")
public class SimplePayController {

    @Autowired
    private SimplePayService simplePayService;

    /**
     * 创建支付订单
     */
    @PostMapping("/create")
    public R createOrder(@RequestParam BigDecimal amount, @RequestParam(required = false) String remark) {
        try {
            String orderId = simplePayService.createOrder(amount, remark);
            String qrCode = simplePayService.getPayQrCode();
            return R.ok()
                    .put("orderId", orderId)
                    .put("qrCode", qrCode);
        } catch (Exception e) {
            return R.error("创建订单失败：" + e.getMessage());
        }
    }

    /**
     * 获取订单信息
     */
    @GetMapping("/order/{orderId}")
    public R getOrder(@PathVariable String orderId) {
        OrdersEntity order = simplePayService.getOrder(orderId);
        if (order != null) {
            return R.ok().put("order", order);
        }
        return R.error("订单不存在");
    }

    /**
     * 更新订单状态（店主手动确认支付）
     */
    @PostMapping("/confirm/{orderId}")
    public R confirmPayment(@PathVariable String orderId) {
        try {
            boolean success = simplePayService.updateOrderStatus(orderId, 1);
            if (success) {
                return R.ok("支付确认成功");
            }
            return R.error("支付确认失败");
        } catch (Exception e) {
            return R.error("支付确认失败：" + e.getMessage());
        }
    }
} 