package com.entity.model;

import com.entity.ZhuangbeixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 装备信息
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-02-26 22:38:36
 */
public class ZhuangbeixinxiModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 装备名称
	 */
	
	private String zhuangbeimingcheng;
		
	/**
	 * 装备分类
	 */
	
	private String zhuangbeifenlei;
		
	/**
	 * 装备照片
	 */
	
	private String zhuangbeizhaopian;
		
	/**
	 * 装备数量
	 */
	
	private Integer zhuangbeishuliang;
		
	/**
	 * 装备型号
	 */
	
	private String zhuangbeixinghao;
		
	/**
	 * 发布时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date fabushijian;
		
	/**
	 * 装备详情
	 */
	
	private String zhuangbeixiangqing;
				
	
	/**
	 * 设置：装备名称
	 */
	 
	public void setZhuangbeimingcheng(String zhuangbeimingcheng) {
		this.zhuangbeimingcheng = zhuangbeimingcheng;
	}
	
	/**
	 * 获取：装备名称
	 */
	public String getZhuangbeimingcheng() {
		return zhuangbeimingcheng;
	}
				
	
	/**
	 * 设置：装备分类
	 */
	 
	public void setZhuangbeifenlei(String zhuangbeifenlei) {
		this.zhuangbeifenlei = zhuangbeifenlei;
	}
	
	/**
	 * 获取：装备分类
	 */
	public String getZhuangbeifenlei() {
		return zhuangbeifenlei;
	}
				
	
	/**
	 * 设置：装备照片
	 */
	 
	public void setZhuangbeizhaopian(String zhuangbeizhaopian) {
		this.zhuangbeizhaopian = zhuangbeizhaopian;
	}
	
	/**
	 * 获取：装备照片
	 */
	public String getZhuangbeizhaopian() {
		return zhuangbeizhaopian;
	}
				
	
	/**
	 * 设置：装备数量
	 */
	 
	public void setZhuangbeishuliang(Integer zhuangbeishuliang) {
		this.zhuangbeishuliang = zhuangbeishuliang;
	}
	
	/**
	 * 获取：装备数量
	 */
	public Integer getZhuangbeishuliang() {
		return zhuangbeishuliang;
	}
				
	
	/**
	 * 设置：装备型号
	 */
	 
	public void setZhuangbeixinghao(String zhuangbeixinghao) {
		this.zhuangbeixinghao = zhuangbeixinghao;
	}
	
	/**
	 * 获取：装备型号
	 */
	public String getZhuangbeixinghao() {
		return zhuangbeixinghao;
	}
				
	
	/**
	 * 设置：发布时间
	 */
	 
	public void setFabushijian(Date fabushijian) {
		this.fabushijian = fabushijian;
	}
	
	/**
	 * 获取：发布时间
	 */
	public Date getFabushijian() {
		return fabushijian;
	}
				
	
	/**
	 * 设置：装备详情
	 */
	 
	public void setZhuangbeixiangqing(String zhuangbeixiangqing) {
		this.zhuangbeixiangqing = zhuangbeixiangqing;
	}
	
	/**
	 * 获取：装备详情
	 */
	public String getZhuangbeixiangqing() {
		return zhuangbeixiangqing;
	}
			
}
