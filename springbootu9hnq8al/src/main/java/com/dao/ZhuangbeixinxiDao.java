package com.dao;

import com.entity.ZhuangbeixinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZhuangbeixinxiVO;
import com.entity.view.ZhuangbeixinxiView;


/**
 * 装备信息
 * 
 * @author 
 * @email 
 * @date 2025-02-26 22:38:36
 */
public interface ZhuangbeixinxiDao extends BaseMapper<ZhuangbeixinxiEntity> {
	
	List<ZhuangbeixinxiVO> selectListVO(@Param("ew") Wrapper<ZhuangbeixinxiEntity> wrapper);
	
	ZhuangbeixinxiVO selectVO(@Param("ew") Wrapper<ZhuangbeixinxiEntity> wrapper);
	
	List<ZhuangbeixinxiView> selectListView(@Param("ew") Wrapper<ZhuangbeixinxiEntity> wrapper);

	List<ZhuangbeixinxiView> selectListView(Pagination page,@Param("ew") Wrapper<ZhuangbeixinxiEntity> wrapper);

	
	ZhuangbeixinxiView selectView(@Param("ew") Wrapper<ZhuangbeixinxiEntity> wrapper);
	

}
