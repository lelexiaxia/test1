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

import com.entity.BisaifenzuEntity;
import com.entity.view.BisaifenzuView;

import com.service.BisaifenzuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 比赛分组
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
@RestController
@RequestMapping("/bisaifenzu")
public class BisaifenzuController {
    @Autowired
    private BisaifenzuService bisaifenzuService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BisaifenzuEntity bisaifenzu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("wanjia")) {
			bisaifenzu.setWanjiazhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<BisaifenzuEntity> ew = new EntityWrapper<BisaifenzuEntity>();


        //查询结果
		PageUtils page = bisaifenzuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bisaifenzu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,BisaifenzuEntity bisaifenzu, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<BisaifenzuEntity> ew = new EntityWrapper<BisaifenzuEntity>();

        //查询结果
		PageUtils page = bisaifenzuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bisaifenzu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BisaifenzuEntity bisaifenzu){
       	EntityWrapper<BisaifenzuEntity> ew = new EntityWrapper<BisaifenzuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( bisaifenzu, "bisaifenzu")); 
        return R.ok().put("data", bisaifenzuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BisaifenzuEntity bisaifenzu){
        EntityWrapper< BisaifenzuEntity> ew = new EntityWrapper< BisaifenzuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( bisaifenzu, "bisaifenzu")); 
		BisaifenzuView bisaifenzuView =  bisaifenzuService.selectView(ew);
		return R.ok("查询比赛分组成功").put("data", bisaifenzuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BisaifenzuEntity bisaifenzu = bisaifenzuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(bisaifenzu,deSens);
        return R.ok().put("data", bisaifenzu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BisaifenzuEntity bisaifenzu = bisaifenzuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(bisaifenzu,deSens);
        return R.ok().put("data", bisaifenzu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BisaifenzuEntity bisaifenzu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(bisaifenzu);
        bisaifenzuService.insert(bisaifenzu);
        return R.ok().put("data",bisaifenzu.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BisaifenzuEntity bisaifenzu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(bisaifenzu);
        bisaifenzuService.insert(bisaifenzu);
        return R.ok().put("data",bisaifenzu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BisaifenzuEntity bisaifenzu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(bisaifenzu);
        //全部更新
        bisaifenzuService.updateById(bisaifenzu);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        bisaifenzuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
