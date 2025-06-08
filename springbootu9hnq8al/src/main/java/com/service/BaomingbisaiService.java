package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BaomingbisaiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.BaomingbisaiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BaomingbisaiView;


/**
 * 报名比赛
 *
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public interface BaomingbisaiService extends IService<BaomingbisaiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BaomingbisaiVO> selectListVO(Wrapper<BaomingbisaiEntity> wrapper);
   	
   	BaomingbisaiVO selectVO(@Param("ew") Wrapper<BaomingbisaiEntity> wrapper);
   	
   	List<BaomingbisaiView> selectListView(Wrapper<BaomingbisaiEntity> wrapper);
   	
   	BaomingbisaiView selectView(@Param("ew") Wrapper<BaomingbisaiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BaomingbisaiEntity> wrapper);

   	

}

