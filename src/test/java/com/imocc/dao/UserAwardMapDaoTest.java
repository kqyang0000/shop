package com.imocc.dao;

import com.imocc.entity.Award;
import com.imocc.entity.PersonInfo;
import com.imocc.entity.Shop;
import com.imocc.entity.UserAwardMap;
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
public class UserAwardMapDaoTest {
    @Autowired
    private UserAwardMapDao userAwardMapDao;

    @Test
    public void testAInsertUserAwardMap() {
        UserAwardMap userAwardMap = new UserAwardMap();
        PersonInfo customer = new PersonInfo();
        customer.setUserId(1L);
        userAwardMap.setUser(customer);
        userAwardMap.setOperator(customer);
        Award award = new Award();
        award.setAwardId(7L);
        userAwardMap.setAward(award);
        Shop shop = new Shop();
        shop.setShopId(1L);
        userAwardMap.setShop(shop);
        userAwardMap.setCreateTime(new Date());
        userAwardMap.setUsedStatus(1);
        userAwardMap.setPoint(1);
        int effectedNum = userAwardMapDao.insertUserAwardMap(userAwardMap);
        Assert.assertEquals(1, effectedNum);

        UserAwardMap userAwardMap2 = new UserAwardMap();
        PersonInfo customer2 = new PersonInfo();
        customer2.setUserId(1L);
        userAwardMap2.setUser(customer2);
        userAwardMap2.setOperator(customer2);
        Award award2 = new Award();
        award2.setAwardId(7L);
        userAwardMap2.setAward(award2);
        Shop shop2 = new Shop();
        shop2.setShopId(1L);
        userAwardMap2.setShop(shop2);
        userAwardMap2.setCreateTime(new Date());
        userAwardMap2.setUsedStatus(0);
        userAwardMap2.setPoint(2);
        effectedNum = userAwardMapDao.insertUserAwardMap(userAwardMap2);
        Assert.assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryUserAwardMapList() {
        UserAwardMap userAwardMap = new UserAwardMap();
        List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap, 0, 3);
        Assert.assertEquals(2, userAwardMapList.size());
        int count = userAwardMapDao.queryUserAwardMapCount(userAwardMap);
        Assert.assertEquals(2, count);
        PersonInfo customer = new PersonInfo();
        customer.setName("测试");
        userAwardMap.setUser(customer);
        userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap, 0, 3);
        Assert.assertEquals(2, userAwardMapList.size());
        count = userAwardMapDao.queryUserAwardMapCount(userAwardMap);
        Assert.assertEquals(2, count);
        userAwardMap = userAwardMapDao.queryUserAwardMapById(userAwardMapList.get(0).getUserAwardId());
        Assert.assertEquals("测试一", userAwardMap.getAward().getAwardName());
    }

    @Test
    public void testCUpdateUserAwardMap() {
        UserAwardMap userAwardMap = new UserAwardMap();
        PersonInfo customer = new PersonInfo();
        customer.setName("测试");
        userAwardMap.setUser(customer);
        List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap, 0, 3);
        Assert.assertTrue("积分不一致", userAwardMapList.get(0).getUsedStatus() == 0);
        userAwardMapList.get(0).setUsedStatus(1);
        int effectedNum = userAwardMapDao.updateUserAwardMap(userAwardMapList.get(0));
        Assert.assertEquals(1, effectedNum);
    }
}