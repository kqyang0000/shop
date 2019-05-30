package com.imocc.dao;

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
public class AreaDaoTest {

    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea() {
        List<Area> list = areaDao.queryArea();
        Assert.assertEquals(2, list.size());
    }
}
