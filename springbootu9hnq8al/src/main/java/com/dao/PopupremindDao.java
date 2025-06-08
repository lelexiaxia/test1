package com.dao;

import com.entity.PopupremindEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.PopupremindVO;
import com.entity.view.PopupremindView;


/**
 * 弹窗提醒
 * 
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public interface PopupremindDao extends BaseMapper<PopupremindEntity> {
	
	List<PopupremindVO> selectListVO(@Param("ew") Wrapper<PopupremindEntity> wrapper);
	
	PopupremindVO selectVO(@Param("ew") Wrapper<PopupremindEntity> wrapper);
	
	List<PopupremindView> selectListView(@Param("ew") Wrapper<PopupremindEntity> wrapper);

	List<PopupremindView> selectListView(Pagination page,@Param("ew") Wrapper<PopupremindEntity> wrapper);

	
	PopupremindView selectView(@Param("ew") Wrapper<PopupremindEntity> wrapper);
	

}
