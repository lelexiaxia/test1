package com.entity.vo;

import com.entity.BisaichengjiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 比赛成绩
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public class BisaichengjiVO  implements Serializable {
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
	 * 玩家账号
	 */
	
	private String wanjiazhanghao;
		
	/**
	 * 玩家姓名
	 */
	
	private String wanjiaxingming;
		
	/**
	 * 成绩
	 */
	
	private Double chengji;
		
	/**
	 * 胜率
	 */
	
	private String shenglv;
		
	/**
	 * MVP
	 */
	
	private Integer paiming;
		
	/**
	 * 录入时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date lurushijian;
				
	
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
	 * 设置：玩家账号
	 */
	 
	public void setWanjiazhanghao(String wanjiazhanghao) {
		this.wanjiazhanghao = wanjiazhanghao;
	}
	
	/**
	 * 获取：玩家账号
	 */
	public String getWanjiazhanghao() {
		return wanjiazhanghao;
	}
				
	
	/**
	 * 设置：玩家姓名
	 */
	 
	public void setWanjiaxingming(String wanjiaxingming) {
		this.wanjiaxingming = wanjiaxingming;
	}
	
	/**
	 * 获取：玩家姓名
	 */
	public String getWanjiaxingming() {
		return wanjiaxingming;
	}
				
	
	/**
	 * 设置：成绩
	 */
	 
	public void setChengji(Double chengji) {
		this.chengji = chengji;
	}
	
	/**
	 * 获取：成绩
	 */
	public Double getChengji() {
		return chengji;
	}
				
	
	/**
	 * 设置：胜率
	 */
	 
	public void setShenglv(String shenglv) {
		this.shenglv = shenglv;
	}
	
	/**
	 * 获取：胜率
	 */
	public String getShenglv() {
		return shenglv;
	}
				
	
	/**
	 * 设置：MVP
	 */
	 
	public void setPaiming(Integer paiming) {
		this.paiming = paiming;
	}
	
	/**
	 * 获取：MVP
	 */
	public Integer getPaiming() {
		return paiming;
	}
				
	
	/**
	 * 设置：录入时间
	 */
	 
	public void setLurushijian(Date lurushijian) {
		this.lurushijian = lurushijian;
	}
	
	/**
	 * 获取：录入时间
	 */
	public Date getLurushijian() {
		return lurushijian;
	}
			
}
