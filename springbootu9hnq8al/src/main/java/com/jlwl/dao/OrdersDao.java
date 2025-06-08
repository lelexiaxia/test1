package com.jlwl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jlwl.entity.OrdersEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersDao extends BaseMapper<OrdersEntity> {
} 