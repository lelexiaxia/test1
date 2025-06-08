package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZhuangbeixinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhuangbeixinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhuangbeixinxiView;


/**
 * 装备信息
 *
 * @author 
 * @email 
 * @date 2025-02-26 22:38:36
 */
public interface ZhuangbeixinxiService extends IService<ZhuangbeixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhuangbeixinxiVO> selectListVO(Wrapper<ZhuangbeixinxiEntity> wrapper);
   	
   	ZhuangbeixinxiVO selectVO(@Param("ew") Wrapper<ZhuangbeixinxiEntity> wrapper);
   	
   	List<ZhuangbeixinxiView> selectListView(Wrapper<ZhuangbeixinxiEntity> wrapper);
   	
   	ZhuangbeixinxiView selectView(@Param("ew") Wrapper<ZhuangbeixinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhuangbeixinxiEntity> wrapper);

   	

}

