package com.imocc.dao;


import com.imocc.entity.Area;

import java.util.List;

public interface AreaDao {
    /**
     * <p>列出区域列表
     *
     * @return areaList
     * @author kqyang
     * @version 1.0
     * @date 2019/2/14 13:57
     */
    List<Area> queryArea();
}
