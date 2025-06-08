package com.entity.model;

import com.entity.BisaixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 比赛信息
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-02-26 22:38:36
 */
public class BisaixinxiModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 比赛类型
	 */
	
	private String bisaileixing;
		
	/**
	 * 比赛图片
	 */
	
	private String bisaitupian;
		
	/**
	 * 比赛地点
	 */
	
	private String bisaididian;
		
	/**
	 * 比赛日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date bisairiqi;
		
	/**
	 * 报名日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date baomingriqi;
		
	/**
	 * 结束日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date jieshuriqi;
		
	/**
	 * 比赛详情
	 */
	
	private String bisaixiangqing;
		
	/**
	 * 发布时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date fabushijian;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：比赛类型
	 */
	 
	public void setBisaileixing(String bisaileixing) {
		this.bisaileixing = bisaileixing;
	}
	
	/**
	 * 获取：比赛类型
	 */
	public String getBisaileixing() {
		return bisaileixing;
	}
				
	
	/**
	 * 设置：比赛图片
	 */
	 
	public void setBisaitupian(String bisaitupian) {
		this.bisaitupian = bisaitupian;
	}
	
	/**
	 * 获取：比赛图片
	 */
	public String getBisaitupian() {
		return bisaitupian;
	}
				
	
	/**
	 * 设置：比赛地点
	 */
	 
	public void setBisaididian(String bisaididian) {
		this.bisaididian = bisaididian;
	}
	
	/**
	 * 获取：比赛地点
	 */
	public String getBisaididian() {
		return bisaididian;
	}
				
	
	/**
	 * 设置：比赛日期
	 */
	 
	public void setBisairiqi(Date bisairiqi) {
		this.bisairiqi = bisairiqi;
	}
	
	/**
	 * 获取：比赛日期
	 */
	public Date getBisairiqi() {
		return bisairiqi;
	}
				
	
	/**
	 * 设置：报名日期
	 */
	 
	public void setBaomingriqi(Date baomingriqi) {
		this.baomingriqi = baomingriqi;
	}
	
	/**
	 * 获取：报名日期
	 */
	public Date getBaomingriqi() {
		return baomingriqi;
	}
				
	
	/**
	 * 设置：结束日期
	 */
	 
	public void setJieshuriqi(Date jieshuriqi) {
		this.jieshuriqi = jieshuriqi;
	}
	
	/**
	 * 获取：结束日期
	 */
	public Date getJieshuriqi() {
		return jieshuriqi;
	}
				
	
	/**
	 * 设置：比赛详情
	 */
	 
	public void setBisaixiangqing(String bisaixiangqing) {
		this.bisaixiangqing = bisaixiangqing;
	}
	
	/**
	 * 获取：比赛详情
	 */
	public String getBisaixiangqing() {
		return bisaixiangqing;
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
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
