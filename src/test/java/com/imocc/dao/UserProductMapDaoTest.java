package com.imocc.dao;

import com.imocc.entity.PersonInfo;
import com.imocc.entity.Product;
import com.imocc.entity.Shop;
import com.imocc.entity.UserProductMap;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserProductMapDaoTest {
    @Autowired
    private UserProductMapDao userProductMapDao;

    @Test
    public void testAInsertUserProductMap() throws Exception {
        UserProductMap userProductMap = new UserProductMap();
        PersonInfo customer = new PersonInfo();
        customer.setUserId(1L);
        userProductMap.setUser(customer);
        userProductMap.setOperator(customer);
        Product product = new Product();
        product.setProductId(1L);
        userProductMap.setProduct(product);
        Shop shop = new Shop();
        shop.setShopId(1L);
        userProductMap.setShop(shop);
        userProductMap.setCreateTime(new Date());
        int effectedNum = userProductMapDao.insertUserProductMap(userProductMap);
        assertEquals(1, effectedNum);
        UserProductMap userProductMap1 = new UserProductMap();
        PersonInfo customer1 = new PersonInfo();
        customer1.setUserId(1L);
        userProductMap1.setUser(customer1);
        userProductMap1.setOperator(customer1);
        Product product1 = new Product();
        product1.setProductId(2L);
        userProductMap1.setProduct(product1);
        Shop shop1 = new Shop();
        shop1.setShopId(1L);
        userProductMap1.setShop(shop1);
        userProductMap1.setCreateTime(new Date());
        effectedNum = userProductMapDao.insertUserProductMap(userProductMap1);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryUserProductMapList() throws Exception {
        UserProductMap userProductMap = new UserProductMap();
        List<UserProductMap> userProductMapList = userProductMapDao.queryUserProductMapList(userProductMap, 0, 3);
        assertEquals(2, userProductMapList.size());
        int count = userProductMapDao.queryUserProductMapCount(userProductMap);
        assertEquals(2, count);
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("测试");
        userProductMap.setUser(personInfo);
        userProductMapList = userProductMapDao.queryUserProductMapList(userProductMap, 0, 3);
        assertEquals(2, userProductMapList.size());
        count = userProductMapDao.queryUserProductMapCount(userProductMap);
        assertEquals(2, count);
    }
}