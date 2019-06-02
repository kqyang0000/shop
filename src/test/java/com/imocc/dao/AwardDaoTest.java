package com.imocc.dao;

import com.imocc.entity.Award;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by kqyang on 2019/6/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AwardDaoTest {
    @Autowired
    private AwardDao awardDao;

    @Test
    public void testAInsertAward() {
        long shopId = 1;
        Award award1 = new Award();
        award1.setAwardName("测试一");
        award1.setAwardImg("test");
        award1.setAwardDesc("测试描述");
        award1.setPoint(10);
        award1.setEnableStatus(1);
        award1.setCreateTime(new Date());
        award1.setLastEditTime(new Date());
        award1.setShopId(shopId);
        int effectedNum = awardDao.insertAward(award1);
        Assert.assertEquals(1, effectedNum);

        Award award2 = new Award();
        award2.setAwardName("测试二");
        award2.setAwardImg("test2");
        award2.setAwardDesc("测试描述");
        award2.setPoint(12);
        award2.setEnableStatus(2);
        award2.setCreateTime(new Date());
        award2.setLastEditTime(new Date());
        award2.setShopId(shopId);
        effectedNum = awardDao.insertAward(award2);
        Assert.assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryAwardList() {
        Award award = new Award();
        List<Award> awardList = awardDao.queryAwardList(award, 0, 3);
        Assert.assertEquals(2, awardList.size());
        int count = awardDao.queryAwardCount(award);
        Assert.assertEquals(2, count);
        award.setAwardName("测试一");
        awardList = awardDao.queryAwardList(award, 0, 3);
        Assert.assertEquals(1, awardList.size());
        count = awardDao.queryAwardCount(award);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testCQueryAwardByAwardId() {
        Award award = new Award();
        award.setAwardName("测试一");
        List<Award> awardList = awardDao.queryAwardList(award, 0, 3);
        Assert.assertEquals(1, awardList.size());
        Award award1 = awardDao.queryAwardByAwardId(awardList.get(0).getAwardId());
        Assert.assertEquals("测试一", award1.getAwardName());
    }

    @Test
    public void testDUpdateAward() {
        Award award = new Award();
        award.setAwardName("测试一");
        List<Award> awardList = awardDao.queryAwardList(award, 0, 3);
        awardList.get(0).setAwardName("第一个测试奖品");
        int effectedNum = awardDao.updateAward(awardList.get(0));
        Assert.assertEquals(1, effectedNum);
        Award award1 = awardDao.queryAwardByAwardId(awardList.get(0).getAwardId());
        Assert.assertEquals("第一个测试奖品", award1.getAwardName());
    }

    @Test
    public void testEDeleteAward() {
        Award award = new Award();
        award.setAwardName("测试");
        List<Award> awardList = awardDao.queryAwardList(award, 0, 3);
        Assert.assertEquals(2, awardList.size());
        for (int i = 0; i < awardList.size(); i++) {
            int effectedNum = awardDao.deleteAward(awardList.get(i).getAwardId(), awardList.get(i).getShopId());
            Assert.assertEquals(1, effectedNum);
        }
    }
}