package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 装备信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-02-26 22:38:36
 */
@TableName("zhuangbeixinxi")
public class ZhuangbeixinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ZhuangbeixinxiEntity() {
		
	}
	
	public ZhuangbeixinxiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 装备编号
	 */
					
	private String zhuangbeibianhao;
	
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
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date fabushijian;
	
	/**
	 * 装备详情
	 */
					
	private String zhuangbeixiangqing;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：装备编号
	 */
	public void setZhuangbeibianhao(String zhuangbeibianhao) {
		this.zhuangbeibianhao = zhuangbeibianhao;
	}
	/**
	 * 获取：装备编号
	 */
	public String getZhuangbeibianhao() {
		return zhuangbeibianhao;
	}
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
