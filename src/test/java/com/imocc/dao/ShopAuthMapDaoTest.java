package com.imocc.dao;

import com.imocc.entity.PersonInfo;
import com.imocc.entity.Shop;
import com.imocc.entity.ShopAuthMap;
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
public class ShopAuthMapDaoTest {
    @Autowired
    private ShopAuthMapDao shopAuthMapDao;

    @Test
    public void testAInsertShopAuthMap() {
        ShopAuthMap shopAuthMap = new ShopAuthMap();
        PersonInfo employee = new PersonInfo();
        employee.setUserId(1L);
        shopAuthMap.setEmployee(employee);
        Shop shop = new Shop();
        shop.setShopId(1L);
        shopAuthMap.setShop(shop);
        shopAuthMap.setTitle("CEO");
        shopAuthMap.setTitleFlag(1);
        shopAuthMap.setCreateTime(new Date());
        shopAuthMap.setLastEditTime(new Date());
        shopAuthMap.setEnableStatus(1);
        int effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
        Assert.assertEquals(1, effectedNum);
        ShopAuthMap shopAuthMap1 = new ShopAuthMap();
        PersonInfo employee1 = new PersonInfo();
        employee1.setUserId(1L);
        shopAuthMap1.setEmployee(employee1);
        Shop shop1 = new Shop();
        shop1.setShopId(2L);
        shopAuthMap1.setShop(shop1);
        shopAuthMap1.setTitle("打工仔");
        shopAuthMap1.setTitleFlag(2);
        shopAuthMap1.setCreateTime(new Date());
        shopAuthMap1.setLastEditTime(new Date());
        shopAuthMap1.setEnableStatus(1);
        effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap1);
        Assert.assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryShopAuthMapListByShopId() {
        List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapListByShopId(1, 0, 3);
        Assert.assertEquals(1, shopAuthMapList.size());
        int count = shopAuthMapDao.queryShopAuthMapCountByShopId(1);
        Assert.assertEquals(1, count);
        ShopAuthMap shopAuthMap = shopAuthMapDao.queryShopAuthMapById(9);
        Assert.assertEquals("CEO", shopAuthMap.getTitle());
    }

    @Test
    public void testCUpdateUserShopMap() {
        ShopAuthMap shopAuthMap = shopAuthMapDao.queryShopAuthMapById(9);
        shopAuthMap.setTitle("CCO");
        shopAuthMap.setTitleFlag(2);
        int effectedNum = shopAuthMapDao.updateUserShopMap(shopAuthMap);
        Assert.assertEquals(1, effectedNum);
    }

    @Test
    public void testDeleteUserShopMap(){
        int effectedNum = shopAuthMapDao.deleteUserShopMap(9);
        Assert.assertEquals(1, effectedNum);
        effectedNum = shopAuthMapDao.deleteUserShopMap(10);
        Assert.assertEquals(1, effectedNum);
    }
}