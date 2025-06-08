package com.dao;

import com.entity.BisaifenzuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BisaifenzuVO;
import com.entity.view.BisaifenzuView;


/**
 * 比赛分组
 * 
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public interface BisaifenzuDao extends BaseMapper<BisaifenzuEntity> {
	
	List<BisaifenzuVO> selectListVO(@Param("ew") Wrapper<BisaifenzuEntity> wrapper);
	
	BisaifenzuVO selectVO(@Param("ew") Wrapper<BisaifenzuEntity> wrapper);
	
	List<BisaifenzuView> selectListView(@Param("ew") Wrapper<BisaifenzuEntity> wrapper);

	List<BisaifenzuView> selectListView(Pagination page,@Param("ew") Wrapper<BisaifenzuEntity> wrapper);

	
	BisaifenzuView selectView(@Param("ew") Wrapper<BisaifenzuEntity> wrapper);
	

}
