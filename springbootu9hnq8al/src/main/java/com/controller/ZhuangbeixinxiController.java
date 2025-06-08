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

import com.entity.ZhuangbeixinxiEntity;
import com.entity.view.ZhuangbeixinxiView;

import com.service.ZhuangbeixinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 装备信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-26 22:38:36
 */
@RestController
@RequestMapping("/zhuangbeixinxi")
public class ZhuangbeixinxiController {
    @Autowired
    private ZhuangbeixinxiService zhuangbeixinxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhuangbeixinxiEntity zhuangbeixinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZhuangbeixinxiEntity> ew = new EntityWrapper<ZhuangbeixinxiEntity>();


        //查询结果
		PageUtils page = zhuangbeixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuangbeixinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZhuangbeixinxiEntity zhuangbeixinxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZhuangbeixinxiEntity> ew = new EntityWrapper<ZhuangbeixinxiEntity>();

        //查询结果
		PageUtils page = zhuangbeixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuangbeixinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhuangbeixinxiEntity zhuangbeixinxi){
       	EntityWrapper<ZhuangbeixinxiEntity> ew = new EntityWrapper<ZhuangbeixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhuangbeixinxi, "zhuangbeixinxi")); 
        return R.ok().put("data", zhuangbeixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhuangbeixinxiEntity zhuangbeixinxi){
        EntityWrapper< ZhuangbeixinxiEntity> ew = new EntityWrapper< ZhuangbeixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhuangbeixinxi, "zhuangbeixinxi")); 
		ZhuangbeixinxiView zhuangbeixinxiView =  zhuangbeixinxiService.selectView(ew);
		return R.ok("查询装备信息成功").put("data", zhuangbeixinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhuangbeixinxiEntity zhuangbeixinxi = zhuangbeixinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhuangbeixinxi,deSens);
        return R.ok().put("data", zhuangbeixinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhuangbeixinxiEntity zhuangbeixinxi = zhuangbeixinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhuangbeixinxi,deSens);
        return R.ok().put("data", zhuangbeixinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhuangbeixinxiEntity zhuangbeixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuangbeixinxi);
        zhuangbeixinxiService.insert(zhuangbeixinxi);
        return R.ok().put("data",zhuangbeixinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhuangbeixinxiEntity zhuangbeixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuangbeixinxi);
        zhuangbeixinxiService.insert(zhuangbeixinxi);
        return R.ok().put("data",zhuangbeixinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhuangbeixinxiEntity zhuangbeixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuangbeixinxi);
        //全部更新
        zhuangbeixinxiService.updateById(zhuangbeixinxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhuangbeixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
