package com.imocc.service;


import com.imocc.entity.Area;
import com.imocc.exceptions.AreaOperationException;

import java.util.List;

public interface AreaService {
    String AREALISTKEY = "arealist";

    List<Area> getAreaList() throws AreaOperationException;
}
