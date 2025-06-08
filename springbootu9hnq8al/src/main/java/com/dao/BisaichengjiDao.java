package com.dao;

import com.entity.BisaichengjiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BisaichengjiVO;
import com.entity.view.BisaichengjiView;


/**
 * 比赛成绩
 * 
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public interface BisaichengjiDao extends BaseMapper<BisaichengjiEntity> {
	
	List<BisaichengjiVO> selectListVO(@Param("ew") Wrapper<BisaichengjiEntity> wrapper);
	
	BisaichengjiVO selectVO(@Param("ew") Wrapper<BisaichengjiEntity> wrapper);
	
	List<BisaichengjiView> selectListView(@Param("ew") Wrapper<BisaichengjiEntity> wrapper);

	List<BisaichengjiView> selectListView(Pagination page,@Param("ew") Wrapper<BisaichengjiEntity> wrapper);

	
	BisaichengjiView selectView(@Param("ew") Wrapper<BisaichengjiEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<BisaichengjiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<BisaichengjiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<BisaichengjiEntity> wrapper);



}
