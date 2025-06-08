package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BisaifenzuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.BisaifenzuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BisaifenzuView;


/**
 * 比赛分组
 *
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public interface BisaifenzuService extends IService<BisaifenzuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BisaifenzuVO> selectListVO(Wrapper<BisaifenzuEntity> wrapper);
   	
   	BisaifenzuVO selectVO(@Param("ew") Wrapper<BisaifenzuEntity> wrapper);
   	
   	List<BisaifenzuView> selectListView(Wrapper<BisaifenzuEntity> wrapper);
   	
   	BisaifenzuView selectView(@Param("ew") Wrapper<BisaifenzuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BisaifenzuEntity> wrapper);

   	

}

