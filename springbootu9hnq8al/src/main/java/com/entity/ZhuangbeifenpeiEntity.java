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
 * 装备分配
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
@TableName("zhuangbeifenpei")
public class ZhuangbeifenpeiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ZhuangbeifenpeiEntity() {
		
	}
	
	public ZhuangbeifenpeiEntity(T t) {
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
	 * 分配数量
	 */
					
	private Integer zhuangbeishuliang;
	
	/**
	 * 分配时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
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
