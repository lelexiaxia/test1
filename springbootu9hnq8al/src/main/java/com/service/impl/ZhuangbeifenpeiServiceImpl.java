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


import com.dao.ZhuangbeifenpeiDao;
import com.entity.ZhuangbeifenpeiEntity;
import com.service.ZhuangbeifenpeiService;
import com.entity.vo.ZhuangbeifenpeiVO;
import com.entity.view.ZhuangbeifenpeiView;

@Service("zhuangbeifenpeiService")
public class ZhuangbeifenpeiServiceImpl extends ServiceImpl<ZhuangbeifenpeiDao, ZhuangbeifenpeiEntity> implements ZhuangbeifenpeiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhuangbeifenpeiEntity> page = this.selectPage(
                new Query<ZhuangbeifenpeiEntity>(params).getPage(),
                new EntityWrapper<ZhuangbeifenpeiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhuangbeifenpeiEntity> wrapper) {
		  Page<ZhuangbeifenpeiView> page =new Query<ZhuangbeifenpeiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ZhuangbeifenpeiVO> selectListVO(Wrapper<ZhuangbeifenpeiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZhuangbeifenpeiVO selectVO(Wrapper<ZhuangbeifenpeiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZhuangbeifenpeiView> selectListView(Wrapper<ZhuangbeifenpeiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZhuangbeifenpeiView selectView(Wrapper<ZhuangbeifenpeiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
