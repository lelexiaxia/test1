package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ZhuangbeifenleiEntity;
import com.entity.view.ZhuangbeifenleiView;

import com.service.ZhuangbeifenleiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 装备分类
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-26 22:38:36
 */
@RestController
@RequestMapping("/zhuangbeifenlei")
public class ZhuangbeifenleiController {
    @Autowired
    private ZhuangbeifenleiService zhuangbeifenleiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhuangbeifenleiEntity zhuangbeifenlei,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZhuangbeifenleiEntity> ew = new EntityWrapper<ZhuangbeifenleiEntity>();


        //查询结果
		PageUtils page = zhuangbeifenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuangbeifenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhuangbeifenleiEntity zhuangbeifenlei, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZhuangbeifenleiEntity> ew = new EntityWrapper<ZhuangbeifenleiEntity>();

        //查询结果
		PageUtils page = zhuangbeifenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuangbeifenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhuangbeifenleiEntity zhuangbeifenlei){
       	EntityWrapper<ZhuangbeifenleiEntity> ew = new EntityWrapper<ZhuangbeifenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhuangbeifenlei, "zhuangbeifenlei")); 
        return R.ok().put("data", zhuangbeifenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhuangbeifenleiEntity zhuangbeifenlei){
        EntityWrapper< ZhuangbeifenleiEntity> ew = new EntityWrapper< ZhuangbeifenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhuangbeifenlei, "zhuangbeifenlei")); 
		ZhuangbeifenleiView zhuangbeifenleiView =  zhuangbeifenleiService.selectView(ew);
		return R.ok("查询装备分类成功").put("data", zhuangbeifenleiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhuangbeifenleiEntity zhuangbeifenlei = zhuangbeifenleiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhuangbeifenlei,deSens);
        return R.ok().put("data", zhuangbeifenlei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhuangbeifenleiEntity zhuangbeifenlei = zhuangbeifenleiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhuangbeifenlei,deSens);
        return R.ok().put("data", zhuangbeifenlei);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhuangbeifenleiEntity zhuangbeifenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(zhuangbeifenleiService.selectCount(new EntityWrapper<ZhuangbeifenleiEntity>().eq("zhuangbeifenlei", zhuangbeifenlei.getZhuangbeifenlei()))>0) {
            return R.error("装备分类已存在");
        }
        //ValidatorUtils.validateEntity(zhuangbeifenlei);
        zhuangbeifenleiService.insert(zhuangbeifenlei);
        return R.ok().put("data",zhuangbeifenlei.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhuangbeifenleiEntity zhuangbeifenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(zhuangbeifenleiService.selectCount(new EntityWrapper<ZhuangbeifenleiEntity>().eq("zhuangbeifenlei", zhuangbeifenlei.getZhuangbeifenlei()))>0) {
            return R.error("装备分类已存在");
        }
        //ValidatorUtils.validateEntity(zhuangbeifenlei);
        zhuangbeifenleiService.insert(zhuangbeifenlei);
        return R.ok().put("data",zhuangbeifenlei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhuangbeifenleiEntity zhuangbeifenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuangbeifenlei);
        //验证字段唯一性，否则返回错误信息
        if(zhuangbeifenleiService.selectCount(new EntityWrapper<ZhuangbeifenleiEntity>().ne("id", zhuangbeifenlei.getId()).eq("zhuangbeifenlei", zhuangbeifenlei.getZhuangbeifenlei()))>0) {
            return R.error("装备分类已存在");
        }
        //全部更新
        zhuangbeifenleiService.updateById(zhuangbeifenlei);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhuangbeifenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
