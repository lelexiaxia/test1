package com.entity.view;

import com.entity.ZhuangbeixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 装备信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-02-26 22:38:36
 */
@TableName("zhuangbeixinxi")
public class ZhuangbeixinxiView  extends ZhuangbeixinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ZhuangbeixinxiView(){
	}
 
 	public ZhuangbeixinxiView(ZhuangbeixinxiEntity zhuangbeixinxiEntity){
 	try {
			BeanUtils.copyProperties(this, zhuangbeixinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
