package com.imocc.dao;

import com.imocc.entity.HeadLine;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class HeadLineDaoTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    @Ignore
    public void testBQueryHeadLine() {
        List<HeadLine> headLineList = headLineDao.queryHeadLine(new HeadLine());
        Assert.assertEquals(1, headLineList.size());
    }
}
