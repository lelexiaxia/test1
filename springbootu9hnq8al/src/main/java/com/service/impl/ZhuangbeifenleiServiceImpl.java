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


import com.dao.ZhuangbeifenleiDao;
import com.entity.ZhuangbeifenleiEntity;
import com.service.ZhuangbeifenleiService;
import com.entity.vo.ZhuangbeifenleiVO;
import com.entity.view.ZhuangbeifenleiView;

@Service("zhuangbeifenleiService")
public class ZhuangbeifenleiServiceImpl extends ServiceImpl<ZhuangbeifenleiDao, ZhuangbeifenleiEntity> implements ZhuangbeifenleiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhuangbeifenleiEntity> page = this.selectPage(
                new Query<ZhuangbeifenleiEntity>(params).getPage(),
                new EntityWrapper<ZhuangbeifenleiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhuangbeifenleiEntity> wrapper) {
		  Page<ZhuangbeifenleiView> page =new Query<ZhuangbeifenleiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ZhuangbeifenleiVO> selectListVO(Wrapper<ZhuangbeifenleiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZhuangbeifenleiVO selectVO(Wrapper<ZhuangbeifenleiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZhuangbeifenleiView> selectListView(Wrapper<ZhuangbeifenleiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZhuangbeifenleiView selectView(Wrapper<ZhuangbeifenleiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
