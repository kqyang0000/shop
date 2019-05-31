package com.imocc.service;

import com.imocc.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList(){
        List<Area> list = areaService.getAreaList();
        Assert.assertEquals("东苑", list.get(0).getAreaName());
    }
}
