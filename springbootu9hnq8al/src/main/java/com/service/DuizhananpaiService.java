package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DuizhananpaiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DuizhananpaiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DuizhananpaiView;


/**
 * 对战安排
 *
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public interface DuizhananpaiService extends IService<DuizhananpaiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DuizhananpaiVO> selectListVO(Wrapper<DuizhananpaiEntity> wrapper);
   	
   	DuizhananpaiVO selectVO(@Param("ew") Wrapper<DuizhananpaiEntity> wrapper);
   	
   	List<DuizhananpaiView> selectListView(Wrapper<DuizhananpaiEntity> wrapper);
   	
   	DuizhananpaiView selectView(@Param("ew") Wrapper<DuizhananpaiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DuizhananpaiEntity> wrapper);

   	

}

