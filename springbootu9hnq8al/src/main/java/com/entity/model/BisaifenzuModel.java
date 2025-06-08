package com.entity.model;

import com.entity.BisaifenzuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 比赛分组
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public class BisaifenzuModel  implements Serializable {
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
	 * 分组时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date fenzushijian;
		
	/**
	 * 玩家账号
	 */
	
	private String wanjiazhanghao;
		
	/**
	 * 玩家姓名
	 */
	
	private String wanjiaxingming;
		
	/**
	 * 对战选手
	 */
	
	private String duizhanxuanshou;
		
	/**
	 * 分配备注
	 */
	
	private String fenpeibeizhu;
				
	
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
	 * 设置：分组时间
	 */
	 
	public void setFenzushijian(Date fenzushijian) {
		this.fenzushijian = fenzushijian;
	}
	
	/**
	 * 获取：分组时间
	 */
	public Date getFenzushijian() {
		return fenzushijian;
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
	 * 设置：对战选手
	 */
	 
	public void setDuizhanxuanshou(String duizhanxuanshou) {
		this.duizhanxuanshou = duizhanxuanshou;
	}
	
	/**
	 * 获取：对战选手
	 */
	public String getDuizhanxuanshou() {
		return duizhanxuanshou;
	}
				
	
	/**
	 * 设置：分配备注
	 */
	 
	public void setFenpeibeizhu(String fenpeibeizhu) {
		this.fenpeibeizhu = fenpeibeizhu;
	}
	
	/**
	 * 获取：分配备注
	 */
	public String getFenpeibeizhu() {
		return fenpeibeizhu;
	}
			
}
