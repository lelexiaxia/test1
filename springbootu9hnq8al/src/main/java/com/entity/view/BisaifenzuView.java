package com.entity.view;

import com.entity.BisaifenzuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 比赛分组
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
@TableName("bisaifenzu")
public class BisaifenzuView  extends BisaifenzuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public BisaifenzuView(){
	}
 
 	public BisaifenzuView(BisaifenzuEntity bisaifenzuEntity){
 	try {
			BeanUtils.copyProperties(this, bisaifenzuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
