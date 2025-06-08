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

import com.entity.ZhuangbeifenpeiEntity;
import com.entity.view.ZhuangbeifenpeiView;

import com.service.ZhuangbeifenpeiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 装备分配
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
@RestController
@RequestMapping("/zhuangbeifenpei")
public class ZhuangbeifenpeiController {
    @Autowired
    private ZhuangbeifenpeiService zhuangbeifenpeiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhuangbeifenpeiEntity zhuangbeifenpei,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("wanjia")) {
			zhuangbeifenpei.setWanjiazhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<ZhuangbeifenpeiEntity> ew = new EntityWrapper<ZhuangbeifenpeiEntity>();


        //查询结果
		PageUtils page = zhuangbeifenpeiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuangbeifenpei), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZhuangbeifenpeiEntity zhuangbeifenpei, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZhuangbeifenpeiEntity> ew = new EntityWrapper<ZhuangbeifenpeiEntity>();

        //查询结果
		PageUtils page = zhuangbeifenpeiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuangbeifenpei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhuangbeifenpeiEntity zhuangbeifenpei){
       	EntityWrapper<ZhuangbeifenpeiEntity> ew = new EntityWrapper<ZhuangbeifenpeiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhuangbeifenpei, "zhuangbeifenpei")); 
        return R.ok().put("data", zhuangbeifenpeiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhuangbeifenpeiEntity zhuangbeifenpei){
        EntityWrapper< ZhuangbeifenpeiEntity> ew = new EntityWrapper< ZhuangbeifenpeiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhuangbeifenpei, "zhuangbeifenpei")); 
		ZhuangbeifenpeiView zhuangbeifenpeiView =  zhuangbeifenpeiService.selectView(ew);
		return R.ok("查询装备分配成功").put("data", zhuangbeifenpeiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhuangbeifenpeiEntity zhuangbeifenpei = zhuangbeifenpeiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhuangbeifenpei,deSens);
        return R.ok().put("data", zhuangbeifenpei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhuangbeifenpeiEntity zhuangbeifenpei = zhuangbeifenpeiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhuangbeifenpei,deSens);
        return R.ok().put("data", zhuangbeifenpei);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhuangbeifenpeiEntity zhuangbeifenpei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuangbeifenpei);
        zhuangbeifenpeiService.insert(zhuangbeifenpei);
        return R.ok().put("data",zhuangbeifenpei.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhuangbeifenpeiEntity zhuangbeifenpei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuangbeifenpei);
        zhuangbeifenpeiService.insert(zhuangbeifenpei);
        return R.ok().put("data",zhuangbeifenpei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhuangbeifenpeiEntity zhuangbeifenpei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuangbeifenpei);
        //全部更新
        zhuangbeifenpeiService.updateById(zhuangbeifenpei);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhuangbeifenpeiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
