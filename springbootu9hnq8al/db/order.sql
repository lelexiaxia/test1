CREATE TABLE `orders` (
  `id` varchar(32) NOT NULL COMMENT '订单ID',
  `amount` decimal(10,2) NOT NULL COMMENT '支付金额',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付状态：0未支付，1已支付',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表'; 