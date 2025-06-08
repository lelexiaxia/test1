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

import com.entity.DuizhananpaiEntity;
import com.entity.view.DuizhananpaiView;

import com.service.DuizhananpaiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 对战安排
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
@RestController
@RequestMapping("/duizhananpai")
public class DuizhananpaiController {
    @Autowired
    private DuizhananpaiService duizhananpaiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DuizhananpaiEntity duizhananpai,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("wanjia")) {
			duizhananpai.setWanjiazhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<DuizhananpaiEntity> ew = new EntityWrapper<DuizhananpaiEntity>();


        //查询结果
		PageUtils page = duizhananpaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, duizhananpai), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DuizhananpaiEntity duizhananpai, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DuizhananpaiEntity> ew = new EntityWrapper<DuizhananpaiEntity>();

        //查询结果
		PageUtils page = duizhananpaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, duizhananpai), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DuizhananpaiEntity duizhananpai){
       	EntityWrapper<DuizhananpaiEntity> ew = new EntityWrapper<DuizhananpaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( duizhananpai, "duizhananpai")); 
        return R.ok().put("data", duizhananpaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DuizhananpaiEntity duizhananpai){
        EntityWrapper< DuizhananpaiEntity> ew = new EntityWrapper< DuizhananpaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( duizhananpai, "duizhananpai")); 
		DuizhananpaiView duizhananpaiView =  duizhananpaiService.selectView(ew);
		return R.ok("查询对战安排成功").put("data", duizhananpaiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DuizhananpaiEntity duizhananpai = duizhananpaiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(duizhananpai,deSens);
        return R.ok().put("data", duizhananpai);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DuizhananpaiEntity duizhananpai = duizhananpaiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(duizhananpai,deSens);
        return R.ok().put("data", duizhananpai);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DuizhananpaiEntity duizhananpai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(duizhananpai);
        duizhananpaiService.insert(duizhananpai);
        return R.ok().put("data",duizhananpai.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DuizhananpaiEntity duizhananpai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(duizhananpai);
        duizhananpaiService.insert(duizhananpai);
        return R.ok().put("data",duizhananpai.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DuizhananpaiEntity duizhananpai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(duizhananpai);
        //全部更新
        duizhananpaiService.updateById(duizhananpai);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        duizhananpaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
