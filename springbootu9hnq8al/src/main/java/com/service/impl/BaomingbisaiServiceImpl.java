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


import com.dao.BaomingbisaiDao;
import com.entity.BaomingbisaiEntity;
import com.service.BaomingbisaiService;
import com.entity.vo.BaomingbisaiVO;
import com.entity.view.BaomingbisaiView;

@Service("baomingbisaiService")
public class BaomingbisaiServiceImpl extends ServiceImpl<BaomingbisaiDao, BaomingbisaiEntity> implements BaomingbisaiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaomingbisaiEntity> page = this.selectPage(
                new Query<BaomingbisaiEntity>(params).getPage(),
                new EntityWrapper<BaomingbisaiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BaomingbisaiEntity> wrapper) {
		  Page<BaomingbisaiView> page =new Query<BaomingbisaiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<BaomingbisaiVO> selectListVO(Wrapper<BaomingbisaiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public BaomingbisaiVO selectVO(Wrapper<BaomingbisaiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BaomingbisaiView> selectListView(Wrapper<BaomingbisaiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BaomingbisaiView selectView(Wrapper<BaomingbisaiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
