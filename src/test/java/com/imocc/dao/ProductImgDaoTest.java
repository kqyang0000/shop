package com.imocc.dao;

import com.imocc.entity.ProductImg;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductImgDaoTest {
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    @Ignore
    public void testABatchInsertProductImg() {
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片三");
        productImg1.setImgDesc("测试图片三");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(1L);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片四");
        productImg2.setImgDesc("测试图片四");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(1L);
        List<ProductImg> productImgList = new ArrayList<>(32);
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        Assert.assertEquals(2, effectedNum);
    }

    @Test
    @Ignore
    public void testCDeleteProductImgByProductId() {
        long productId = 1L;
        int effectedNum = productImgDao.deleteProductImgByProductId(productId);
        Assert.assertEquals(8, effectedNum);
    }
}
