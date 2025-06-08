package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZhuangbeifenpeiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhuangbeifenpeiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhuangbeifenpeiView;


/**
 * 装备分配
 *
 * @author 
 * @email 
 * @date 2025-02-26 22:38:37
 */
public interface ZhuangbeifenpeiService extends IService<ZhuangbeifenpeiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhuangbeifenpeiVO> selectListVO(Wrapper<ZhuangbeifenpeiEntity> wrapper);
   	
   	ZhuangbeifenpeiVO selectVO(@Param("ew") Wrapper<ZhuangbeifenpeiEntity> wrapper);
   	
   	List<ZhuangbeifenpeiView> selectListView(Wrapper<ZhuangbeifenpeiEntity> wrapper);
   	
   	ZhuangbeifenpeiView selectView(@Param("ew") Wrapper<ZhuangbeifenpeiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhuangbeifenpeiEntity> wrapper);

   	

}

