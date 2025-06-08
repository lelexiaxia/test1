package com.dao;

import com.entity.BaomingbisaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BaomingbisaiVO;
import com.entity.view.BaomingbisaiView;


/**
 * 报名比赛
 * 
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public interface BaomingbisaiDao extends BaseMapper<BaomingbisaiEntity> {
	
	List<BaomingbisaiVO> selectListVO(@Param("ew") Wrapper<BaomingbisaiEntity> wrapper);
	
	BaomingbisaiVO selectVO(@Param("ew") Wrapper<BaomingbisaiEntity> wrapper);
	
	List<BaomingbisaiView> selectListView(@Param("ew") Wrapper<BaomingbisaiEntity> wrapper);

	List<BaomingbisaiView> selectListView(Pagination page,@Param("ew") Wrapper<BaomingbisaiEntity> wrapper);

	
	BaomingbisaiView selectView(@Param("ew") Wrapper<BaomingbisaiEntity> wrapper);
	

}
