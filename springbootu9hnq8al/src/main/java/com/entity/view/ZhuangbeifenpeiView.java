package com.entity.view;

import com.entity.ZhuangbeifenpeiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 装备分配
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
@TableName("zhuangbeifenpei")
public class ZhuangbeifenpeiView  extends ZhuangbeifenpeiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ZhuangbeifenpeiView(){
	}
 
 	public ZhuangbeifenpeiView(ZhuangbeifenpeiEntity zhuangbeifenpeiEntity){
 	try {
			BeanUtils.copyProperties(this, zhuangbeifenpeiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
