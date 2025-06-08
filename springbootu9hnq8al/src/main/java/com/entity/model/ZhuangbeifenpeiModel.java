package com.entity.model;

import com.entity.ZhuangbeifenpeiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 装备分配
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public class ZhuangbeifenpeiModel  implements Serializable {
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
	 * 分配数量
	 */
	
	private Integer zhuangbeishuliang;
		
	/**
	 * 分配时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date fenpeishijian;
		
	/**
	 * 分配备注
	 */
	
	private String fenpeibeizhu;
		
	/**
	 * 玩家账号
	 */
	
	private String wanjiazhanghao;
		
	/**
	 * 玩家姓名
	 */
	
	private String wanjiaxingming;
		
	/**
	 * 跨表用户id
	 */
	
	private Long crossuserid;
		
	/**
	 * 跨表主键id
	 */
	
	private Long crossrefid;
				
	
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
	 * 设置：分配数量
	 */
	 
	public void setZhuangbeishuliang(Integer zhuangbeishuliang) {
		this.zhuangbeishuliang = zhuangbeishuliang;
	}
	
	/**
	 * 获取：分配数量
	 */
	public Integer getZhuangbeishuliang() {
		return zhuangbeishuliang;
	}
				
	
	/**
	 * 设置：分配时间
	 */
	 
	public void setFenpeishijian(Date fenpeishijian) {
		this.fenpeishijian = fenpeishijian;
	}
	
	/**
	 * 获取：分配时间
	 */
	public Date getFenpeishijian() {
		return fenpeishijian;
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
	 * 设置：跨表用户id
	 */
	 
	public void setCrossuserid(Long crossuserid) {
		this.crossuserid = crossuserid;
	}
	
	/**
	 * 获取：跨表用户id
	 */
	public Long getCrossuserid() {
		return crossuserid;
	}
				
	
	/**
	 * 设置：跨表主键id
	 */
	 
	public void setCrossrefid(Long crossrefid) {
		this.crossrefid = crossrefid;
	}
	
	/**
	 * 获取：跨表主键id
	 */
	public Long getCrossrefid() {
		return crossrefid;
	}
			
}
