package com.imocc.service;

import com.imocc.dto.ImageHolder;
import com.imocc.dto.ProductExecution;
import com.imocc.entity.Product;
import com.imocc.entity.ProductCategory;
import com.imocc.entity.Shop;
import com.imocc.enums.ProductStateEnum;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    @Ignore
    public void testAAddProduct() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(4L);
        Product product = new Product();
        product.setProductName("测试商品1");
        product.setProductDesc("商品详情1");
        product.setPriority(10);
        product.setCreateTime(new Date());
        product.setEnableStatus(1);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        File file = new File("C:\\Users\\SL_ykq\\Desktop\\file\\picture\\xiaohuangren.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        ImageHolder imageHolder = new ImageHolder(file.getName(), fileInputStream);
        File file1 = new File("C:\\Users\\SL_ykq\\Desktop\\file\\picture\\login_1.jpg");
        FileInputStream fileInputStream1 = new FileInputStream(file1);
        ImageHolder imageHolder1 = new ImageHolder(file1.getName(), fileInputStream1);
        File file2 = new File("C:\\Users\\SL_ykq\\Desktop\\file\\picture\\login_2.jpg");
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        ImageHolder imageHolder2 = new ImageHolder(file2.getName(), fileInputStream2);
        List<ImageHolder> imageList = new ArrayList<>(32);
        imageList.add(imageHolder1);
        imageList.add(imageHolder2);
        ProductExecution pe = productService.addProduct(product, imageHolder, imageList);
        Assert.assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
    }

    @Test
    public void testBModifyProduct() throws IOException {
        Product product = new Product();
        ProductCategory productCategory = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(1L);
        productCategory.setProductCategoryId(5L);
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("正式的商品");
        product.setProductDesc("正式的商品详情");
        File file = new File("C:\\Users\\SL_ykq\\Desktop\\file\\picture\\xiaohuangren.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        ImageHolder imageHolder = new ImageHolder(file.getName(), fileInputStream);
        File file1 = new File("C:\\Users\\SL_ykq\\Desktop\\file\\picture\\login_1.jpg");
        FileInputStream fileInputStream1 = new FileInputStream(file1);
        ImageHolder imageHolder1 = new ImageHolder(file1.getName(), fileInputStream1);
        File file2 = new File("C:\\Users\\SL_ykq\\Desktop\\file\\picture\\login_2.jpg");
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        ImageHolder imageHolder2 = new ImageHolder(file2.getName(), fileInputStream2);
        List<ImageHolder> imageList = new ArrayList<>(32);
        imageList.add(imageHolder1);
        imageList.add(imageHolder2);
        // 修改商品信息
        ProductExecution productExecution = productService.modifyProduct(product, imageHolder, imageList);
        Assert.assertEquals(ProductStateEnum.SUCCESS.getState(), productExecution.getState());
    }
}