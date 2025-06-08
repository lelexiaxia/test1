package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.entity.TokenEntity;
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

import com.entity.WanjiaEntity;
import com.entity.view.WanjiaView;

import com.service.WanjiaService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 玩家
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-26 22:38:36
 */
@RestController
@RequestMapping("/wanjia")
public class WanjiaController {
    @Autowired
    private WanjiaService wanjiaService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        WanjiaEntity u = wanjiaService.selectOne(new EntityWrapper<WanjiaEntity>().eq("wanjiazhanghao", username));
        // 当用户不存在或验证密码不通过时
		if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
		
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"wanjia",  "玩家" );
        //返回token
		return R.ok().put("token", token);
	}

    /**
     * 人脸登录
     */
    @IgnoreAuth
    @RequestMapping(value = "/faceLogin")
    public R faceLogin(String face, HttpServletRequest request) {
        //获取所有用户信息
        List<WanjiaEntity> uList = wanjiaService.selectList(new EntityWrapper<WanjiaEntity>());
        R result = null;
        WanjiaEntity user = null;
        for(WanjiaEntity u : uList) {
            if (null != CommonUtil.getPropertyByFieldNames(u, "touxiang", "headportrait")) {
                // 循环将集合中的用户头像跟前端提供的头像照片做比较
                result = CommonUtil.matchFace(face, CommonUtil.getPropertyByFieldNames(u, "touxiang", "headportrait").toString().substring(7));
                // 当照片相似度大于等于80时，则认为人脸匹配成功
                if (new BigDecimal(result.get("score").toString()).compareTo(new BigDecimal(80)) == 1) {
                    user = u;
                    break;
                }
            }
        }
        //当照片未匹配到相似度高于80的用户时，认为人脸识别失败，并返回提示
        if(user==null) {
            return R.error("人脸识别失败");
        }
        // 获取登录token
		String token = tokenService.generateToken(user.getId(), user.getWanjiazhanghao(),"wanjia", "玩家");
        //返回token及登录账号
        return R.ok().put("token", token).put("username", user.getWanjiazhanghao());
    }

	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody WanjiaEntity wanjia){
    	//ValidatorUtils.validateEntity(wanjia);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	WanjiaEntity u = wanjiaService.selectOne(new EntityWrapper<WanjiaEntity>().eq("wanjiazhanghao", wanjia.getWanjiazhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同玩家账号，否则返回错误信息
        if(wanjiaService.selectCount(new EntityWrapper<WanjiaEntity>().eq("wanjiazhanghao", wanjia.getWanjiazhanghao()))>0) {
            return R.error("玩家账号已存在");
        }
		Long uId = new Date().getTime();
		wanjia.setId(uId);
        //保存用户
        wanjiaService.insert(wanjia);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        WanjiaEntity u = wanjiaService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        WanjiaEntity u = wanjiaService.selectOne(new EntityWrapper<WanjiaEntity>().eq("wanjiazhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        wanjiaService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,WanjiaEntity wanjia,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<WanjiaEntity> ew = new EntityWrapper<WanjiaEntity>();


        //查询结果
		PageUtils page = wanjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wanjia), params), params));
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
    public R list(@RequestParam Map<String, Object> params,WanjiaEntity wanjia, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<WanjiaEntity> ew = new EntityWrapper<WanjiaEntity>();

        //查询结果
		PageUtils page = wanjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wanjia), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( WanjiaEntity wanjia){
       	EntityWrapper<WanjiaEntity> ew = new EntityWrapper<WanjiaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( wanjia, "wanjia")); 
        return R.ok().put("data", wanjiaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(WanjiaEntity wanjia){
        EntityWrapper< WanjiaEntity> ew = new EntityWrapper< WanjiaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( wanjia, "wanjia")); 
		WanjiaView wanjiaView =  wanjiaService.selectView(ew);
		return R.ok("查询玩家成功").put("data", wanjiaView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WanjiaEntity wanjia = wanjiaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(wanjia,deSens);
        return R.ok().put("data", wanjia);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        WanjiaEntity wanjia = wanjiaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(wanjia,deSens);
        return R.ok().put("data", wanjia);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WanjiaEntity wanjia, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(wanjiaService.selectCount(new EntityWrapper<WanjiaEntity>().eq("wanjiazhanghao", wanjia.getWanjiazhanghao()))>0) {
            return R.error("玩家账号已存在");
        }
        //ValidatorUtils.validateEntity(wanjia);
        //验证账号唯一性，否则返回错误信息
        WanjiaEntity u = wanjiaService.selectOne(new EntityWrapper<WanjiaEntity>().eq("wanjiazhanghao", wanjia.getWanjiazhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	wanjia.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		wanjia.setId(new Date().getTime());
        wanjiaService.insert(wanjia);
        return R.ok().put("data",wanjia.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody WanjiaEntity wanjia, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(wanjiaService.selectCount(new EntityWrapper<WanjiaEntity>().eq("wanjiazhanghao", wanjia.getWanjiazhanghao()))>0) {
            return R.error("玩家账号已存在");
        }
        //ValidatorUtils.validateEntity(wanjia);
        //验证账号唯一性，否则返回错误信息
        WanjiaEntity u = wanjiaService.selectOne(new EntityWrapper<WanjiaEntity>().eq("wanjiazhanghao", wanjia.getWanjiazhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	wanjia.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		wanjia.setId(new Date().getTime());
        wanjiaService.insert(wanjia);
        return R.ok().put("data",wanjia.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody WanjiaEntity wanjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wanjia);
        //验证字段唯一性，否则返回错误信息
        if(wanjiaService.selectCount(new EntityWrapper<WanjiaEntity>().ne("id", wanjia.getId()).eq("wanjiazhanghao", wanjia.getWanjiazhanghao()))>0) {
            return R.error("玩家账号已存在");
        }
        //全部更新
        wanjiaService.updateById(wanjia);
        if(null!=wanjia.getWanjiazhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(wanjia.getWanjiazhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", wanjia.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        wanjiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
