package com.imocc.dao;

import com.imocc.entity.PersonInfo;
import com.imocc.entity.Shop;
import com.imocc.entity.UserShopMap;
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
public class UserShopMapDaoTest {
    @Autowired
    private UserShopMapDao userShopMapDao;

    @Test
    public void testAInsertUserShopMap() {
        UserShopMap userShopMap = new UserShopMap();
        PersonInfo customer = new PersonInfo();
        customer.setUserId(1L);
        userShopMap.setUser(customer);
        Shop shop = new Shop();
        shop.setShopId(1L);
        userShopMap.setShop(shop);
        userShopMap.setCreateTime(new Date());
        userShopMap.setPoint(1);
        int effectedNum = userShopMapDao.insertUserShopMap(userShopMap);
        Assert.assertEquals(1, effectedNum);
        UserShopMap userShopMap1 = new UserShopMap();
        PersonInfo customer1 = new PersonInfo();
        customer1.setUserId(1L);
        userShopMap1.setUser(customer1);
        Shop shop1 = new Shop();
        shop1.setShopId(2L);
        userShopMap1.setShop(shop1);
        userShopMap1.setCreateTime(new Date());
        userShopMap1.setPoint(2);
        effectedNum = userShopMapDao.insertUserShopMap(userShopMap1);
        Assert.assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryUserShopMapList() {
        UserShopMap userShopMap = new UserShopMap();
        List<UserShopMap> userShopMapList = userShopMapDao.queryUserShopMapList(userShopMap, 0, 3);
        Assert.assertEquals(2, userShopMapList.size());
        int count = userShopMapDao.queryUserShopMapCount(userShopMap);
        Assert.assertEquals(2, count);
        Shop shop = new Shop();
        shop.setShopId(1L);
        userShopMap.setShop(shop);
        userShopMapList = userShopMapDao.queryUserShopMapList(userShopMap, 0, 3);
        Assert.assertEquals(1, userShopMapList.size());
        count = userShopMapDao.queryUserShopMapCount(userShopMap);
        Assert.assertEquals(1, count);
        userShopMap = userShopMapDao.queryUserShopMap(1, 1);
        Assert.assertEquals("测试", userShopMap.getUser().getName());
    }

    @Test
    public void testCUpdateUserShopMap() {
        UserShopMap userShopMap = userShopMapDao.queryUserShopMap(1, 1);
        Assert.assertTrue("积分不一致", userShopMap.getPoint() == 1);
        userShopMap.setPoint(10);
        int effectedNum = userShopMapDao.updateUserShopMap(userShopMap);
        Assert.assertEquals(1, effectedNum);
    }
}