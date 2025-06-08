package com.dao;

import com.entity.ZhuangbeifenleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZhuangbeifenleiVO;
import com.entity.view.ZhuangbeifenleiView;


/**
 * 装备分类
 * 
 * @author 
 * @email 
 * @date 2025-02-26 22:38:36
 */
public interface ZhuangbeifenleiDao extends BaseMapper<ZhuangbeifenleiEntity> {
	
	List<ZhuangbeifenleiVO> selectListVO(@Param("ew") Wrapper<ZhuangbeifenleiEntity> wrapper);
	
	ZhuangbeifenleiVO selectVO(@Param("ew") Wrapper<ZhuangbeifenleiEntity> wrapper);
	
	List<ZhuangbeifenleiView> selectListView(@Param("ew") Wrapper<ZhuangbeifenleiEntity> wrapper);

	List<ZhuangbeifenleiView> selectListView(Pagination page,@Param("ew") Wrapper<ZhuangbeifenleiEntity> wrapper);

	
	ZhuangbeifenleiView selectView(@Param("ew") Wrapper<ZhuangbeifenleiEntity> wrapper);
	

}
