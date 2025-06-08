package com.entity.view;

import com.entity.BaomingbisaiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 报名比赛
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
@TableName("baomingbisai")
public class BaomingbisaiView  extends BaomingbisaiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public BaomingbisaiView(){
	}
 
 	public BaomingbisaiView(BaomingbisaiEntity baomingbisaiEntity){
 	try {
			BeanUtils.copyProperties(this, baomingbisaiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
