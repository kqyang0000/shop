package com.imocc.dao;

import com.imocc.entity.ProductSellDaily;
import com.imocc.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by kqyang on 2019/6/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductSellDailyDaoTest {
    @Autowired
    private ProductSellDailyDao productSellDailyDao;

    @Test
    public void testAInsertProductSellDaily() {
        int effectedNum = productSellDailyDao.insertProductSellDaily();
        System.out.print(effectedNum);
    }

    @Test
    public void testBQueryProductSellDailyList() {
        ProductSellDaily productSellDaily = new ProductSellDaily();
        Shop shop = new Shop();
        shop.setShopId(1L);
        productSellDaily.setShop(shop);
        List<ProductSellDaily> productSellDailyList = productSellDailyDao.queryProductSellDailyList(productSellDaily, null, null);
        System.out.print(productSellDailyList.size());
    }
}