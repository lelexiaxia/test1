package com.dao;

import com.entity.DuizhananpaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DuizhananpaiVO;
import com.entity.view.DuizhananpaiView;


/**
 * 对战安排
 * 
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public interface DuizhananpaiDao extends BaseMapper<DuizhananpaiEntity> {
	
	List<DuizhananpaiVO> selectListVO(@Param("ew") Wrapper<DuizhananpaiEntity> wrapper);
	
	DuizhananpaiVO selectVO(@Param("ew") Wrapper<DuizhananpaiEntity> wrapper);
	
	List<DuizhananpaiView> selectListView(@Param("ew") Wrapper<DuizhananpaiEntity> wrapper);

	List<DuizhananpaiView> selectListView(Pagination page,@Param("ew") Wrapper<DuizhananpaiEntity> wrapper);

	
	DuizhananpaiView selectView(@Param("ew") Wrapper<DuizhananpaiEntity> wrapper);
	

}
