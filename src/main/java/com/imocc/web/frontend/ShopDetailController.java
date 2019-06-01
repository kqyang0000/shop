package com.imocc.web.frontend;

import com.imocc.dto.ProductExecution;
import com.imocc.entity.Product;
import com.imocc.entity.ProductCategory;
import com.imocc.entity.Shop;
import com.imocc.service.ProductCategoryService;
import com.imocc.service.ProductService;
import com.imocc.service.ShopService;
import com.imocc.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class ShopDetailController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * <p>获取店铺信息以及该店铺下的商品类别列表
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/4/16 13:28
     */
    @RequestMapping(value = "/listshopdetailpageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShopDetailPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(16);
        // 获取从前台传过来的shopId
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        Shop shop;
        List<ProductCategory> productCategoryList;
        if (shopId != -1) {
            // 获取店铺id 为shopId 的店铺信息
            shop = shopService.getByShopId(shopId);
            // 获取店铺下的商品类别列表
            productCategoryList = productCategoryService.getProductCategoryList(shopId);
            modelMap.put("success", true);
            modelMap.put("shop", shop);
            modelMap.put("productCategoryList", productCategoryList);
        } else {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "empty shopId");
        }
        return modelMap;
    }

    /**
     * <p>依据查询条件分页列出该店铺下面的所有商品
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/4/16 13:39
     */
    @RequestMapping(value = "/listproductsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listProductsByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(16);
        // 获取页码
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        // 获取一页需要显示的条数
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        // 获取店铺id
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        // 空值判断
        if (pageSize > -1 && pageIndex > -1 && shopId > -1) {
            // 尝试获取商品类别id
            long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
            // 尝试获取模糊查找的商品名称
            String productName = HttpServletRequestUtil.getString(request, "productName");
            // 组合查询条件
            Product productCondition = compactProductCondition4Search(shopId, productCategoryId, productName);
            // 按照传入的查询条件以及分页信息返回相应的商品列表以及总数
            ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
            modelMap.put("success", true);
            modelMap.put("count", pe.getCount());
            modelMap.put("productList", pe.getProductList());
        } else {
            modelMap.put("success", false);
            modelMap.put("errorMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }

    /**
     * <p>组合查询商品条件
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/4/16 13:55
     */
    private Product compactProductCondition4Search(long shopId, long productCategoryId, String productName) {
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondition.setShop(shop);
        if (productCategoryId != -1L) {
            // 查询某个商品类别下面的商品列表
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        if (null != productName) {
            // 查询名字里包含productName 的店铺列表
            productCondition.setProductName(productName);
        }
        // 只允许选出状态为上架的商品
        productCondition.setEnableStatus(1);
        return productCondition;
    }
}