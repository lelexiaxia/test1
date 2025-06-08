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


import com.dao.DuizhananpaiDao;
import com.entity.DuizhananpaiEntity;
import com.service.DuizhananpaiService;
import com.entity.vo.DuizhananpaiVO;
import com.entity.view.DuizhananpaiView;

@Service("duizhananpaiService")
public class DuizhananpaiServiceImpl extends ServiceImpl<DuizhananpaiDao, DuizhananpaiEntity> implements DuizhananpaiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DuizhananpaiEntity> page = this.selectPage(
                new Query<DuizhananpaiEntity>(params).getPage(),
                new EntityWrapper<DuizhananpaiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DuizhananpaiEntity> wrapper) {
		  Page<DuizhananpaiView> page =new Query<DuizhananpaiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DuizhananpaiVO> selectListVO(Wrapper<DuizhananpaiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DuizhananpaiVO selectVO(Wrapper<DuizhananpaiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DuizhananpaiView> selectListView(Wrapper<DuizhananpaiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DuizhananpaiView selectView(Wrapper<DuizhananpaiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
