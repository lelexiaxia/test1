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

import com.entity.BaomingbisaiEntity;
import com.entity.view.BaomingbisaiView;

import com.service.BaomingbisaiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 报名比赛
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
@RestController
@RequestMapping("/baomingbisai")
public class BaomingbisaiController {
    @Autowired
    private BaomingbisaiService baomingbisaiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BaomingbisaiEntity baomingbisai,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("wanjia")) {
			baomingbisai.setWanjiazhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<BaomingbisaiEntity> ew = new EntityWrapper<BaomingbisaiEntity>();


        //查询结果
		PageUtils page = baomingbisaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baomingbisai), params), params));
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
    public R list(@RequestParam Map<String, Object> params,BaomingbisaiEntity baomingbisai, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<BaomingbisaiEntity> ew = new EntityWrapper<BaomingbisaiEntity>();

        //查询结果
		PageUtils page = baomingbisaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baomingbisai), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BaomingbisaiEntity baomingbisai){
       	EntityWrapper<BaomingbisaiEntity> ew = new EntityWrapper<BaomingbisaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( baomingbisai, "baomingbisai")); 
        return R.ok().put("data", baomingbisaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BaomingbisaiEntity baomingbisai){
        EntityWrapper< BaomingbisaiEntity> ew = new EntityWrapper< BaomingbisaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( baomingbisai, "baomingbisai")); 
		BaomingbisaiView baomingbisaiView =  baomingbisaiService.selectView(ew);
		return R.ok("查询报名比赛成功").put("data", baomingbisaiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BaomingbisaiEntity baomingbisai = baomingbisaiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(baomingbisai,deSens);
        return R.ok().put("data", baomingbisai);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BaomingbisaiEntity baomingbisai = baomingbisaiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(baomingbisai,deSens);
        return R.ok().put("data", baomingbisai);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BaomingbisaiEntity baomingbisai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baomingbisai);
        baomingbisaiService.insert(baomingbisai);
        return R.ok().put("data",baomingbisai.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BaomingbisaiEntity baomingbisai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baomingbisai);
        baomingbisaiService.insert(baomingbisai);
        return R.ok().put("data",baomingbisai.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BaomingbisaiEntity baomingbisai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baomingbisai);
        //全部更新
        baomingbisaiService.updateById(baomingbisai);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        baomingbisaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
