package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.BisaifenzuDao;
import com.entity.BisaifenzuEntity;
import com.service.BisaifenzuService;
import com.entity.vo.BisaifenzuVO;
import com.entity.view.BisaifenzuView;

@Service("bisaifenzuService")
public class BisaifenzuServiceImpl extends ServiceImpl<BisaifenzuDao, BisaifenzuEntity> implements BisaifenzuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BisaifenzuEntity> page = this.selectPage(
                new Query<BisaifenzuEntity>(params).getPage(),
                new EntityWrapper<BisaifenzuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BisaifenzuEntity> wrapper) {
		  Page<BisaifenzuView> page =new Query<BisaifenzuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<BisaifenzuVO> selectListVO(Wrapper<BisaifenzuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public BisaifenzuVO selectVO(Wrapper<BisaifenzuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BisaifenzuView> selectListView(Wrapper<BisaifenzuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BisaifenzuView selectView(Wrapper<BisaifenzuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
