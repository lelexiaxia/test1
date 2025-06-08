package com.dao;

import com.entity.ZhuangbeifenpeiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZhuangbeifenpeiVO;
import com.entity.view.ZhuangbeifenpeiView;


/**
 * 装备分配
 * 
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public interface ZhuangbeifenpeiDao extends BaseMapper<ZhuangbeifenpeiEntity> {
	
	List<ZhuangbeifenpeiVO> selectListVO(@Param("ew") Wrapper<ZhuangbeifenpeiEntity> wrapper);
	
	ZhuangbeifenpeiVO selectVO(@Param("ew") Wrapper<ZhuangbeifenpeiEntity> wrapper);
	
	List<ZhuangbeifenpeiView> selectListView(@Param("ew") Wrapper<ZhuangbeifenpeiEntity> wrapper);

	List<ZhuangbeifenpeiView> selectListView(Pagination page,@Param("ew") Wrapper<ZhuangbeifenpeiEntity> wrapper);

	
	ZhuangbeifenpeiView selectView(@Param("ew") Wrapper<ZhuangbeifenpeiEntity> wrapper);
	

}
