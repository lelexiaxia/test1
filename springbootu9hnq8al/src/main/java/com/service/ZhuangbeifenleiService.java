package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZhuangbeifenleiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhuangbeifenleiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhuangbeifenleiView;


/**
 * 装备分类
 *
 * @author 
 * @email 
 * @date 2025-02-26 22:38:36
 */
public interface ZhuangbeifenleiService extends IService<ZhuangbeifenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhuangbeifenleiVO> selectListVO(Wrapper<ZhuangbeifenleiEntity> wrapper);
   	
   	ZhuangbeifenleiVO selectVO(@Param("ew") Wrapper<ZhuangbeifenleiEntity> wrapper);
   	
   	List<ZhuangbeifenleiView> selectListView(Wrapper<ZhuangbeifenleiEntity> wrapper);
   	
   	ZhuangbeifenleiView selectView(@Param("ew") Wrapper<ZhuangbeifenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhuangbeifenleiEntity> wrapper);

   	

}

