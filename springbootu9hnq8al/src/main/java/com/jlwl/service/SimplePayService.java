package com.jlwl.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jlwl.dao.OrdersDao;
import com.jlwl.entity.OrdersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
public class SimplePayService {
    
    @Value("${simple.pay.qrcode}")
    private String payQrCode; // 店主的支付宝收款码URL
    
    @Autowired
    private OrdersDao ordersDao;
    
    /**
     * 创建订单
     */
    @Transactional
    public String createOrder(BigDecimal amount, String remark) {
        String orderId = generateOrderId();
        
        OrdersEntity order = new OrdersEntity();
        order.setId(orderId);
        order.setAmount(amount);
        order.setStatus(0); // 未支付
        order.setCreateTime(new Date());
        order.setRemark(remark);
        
        ordersDao.insert(order);
        return orderId;
    }
    
    /**
     * 更新订单状态
     */
    @Transactional
    public boolean updateOrderStatus(String orderId, Integer status) {
        OrdersEntity order = new OrdersEntity();
        order.setId(orderId);
        order.setStatus(status);
        if (status == 1) { // 已支付
            order.setPayTime(new Date());
        }
        return ordersDao.updateById(order) > 0;
    }
    
    /**
     * 获取订单信息
     */
    public OrdersEntity getOrder(String orderId) {
        return ordersDao.selectById(orderId);
    }
    
    /**
     * 生成订单号
     */
    private String generateOrderId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * 获取支付二维码URL
     */
    public String getPayQrCode() {
        return payQrCode;
    }
} 