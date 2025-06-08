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


import com.dao.ZhuangbeixinxiDao;
import com.entity.ZhuangbeixinxiEntity;
import com.service.ZhuangbeixinxiService;
import com.entity.vo.ZhuangbeixinxiVO;
import com.entity.view.ZhuangbeixinxiView;

@Service("zhuangbeixinxiService")
public class ZhuangbeixinxiServiceImpl extends ServiceImpl<ZhuangbeixinxiDao, ZhuangbeixinxiEntity> implements ZhuangbeixinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhuangbeixinxiEntity> page = this.selectPage(
                new Query<ZhuangbeixinxiEntity>(params).getPage(),
                new EntityWrapper<ZhuangbeixinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhuangbeixinxiEntity> wrapper) {
		  Page<ZhuangbeixinxiView> page =new Query<ZhuangbeixinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ZhuangbeixinxiVO> selectListVO(Wrapper<ZhuangbeixinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZhuangbeixinxiVO selectVO(Wrapper<ZhuangbeixinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZhuangbeixinxiView> selectListView(Wrapper<ZhuangbeixinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZhuangbeixinxiView selectView(Wrapper<ZhuangbeixinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
