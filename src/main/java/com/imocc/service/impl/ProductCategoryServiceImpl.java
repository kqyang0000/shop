package com.imocc.service.impl;

import com.imocc.dao.ProductCategoryDao;
import com.imocc.dao.ProductDao;
import com.imocc.dto.ProductCategoryExecution;
import com.imocc.entity.ProductCategory;
import com.imocc.enums.ProductCategoryStateEnum;
import com.imocc.exceptions.ProductCategoryOperationException;
import com.imocc.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Transactional
    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException {
        if (null != productCategoryList && productCategoryList.size() > 0) {
            for (ProductCategory productCategory : productCategoryList) {
                productCategory.setCreateTime(new Date());
            }
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error:" + e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Transactional
    @Override
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException {
        try {
            int effectedNumDelete = productDao.updateProductCategoryToNull(productCategoryId);
            if (effectedNumDelete < 0) {
                throw new ProductCategoryOperationException("商品类别更新失败");
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("deleteProductCategory error:" + e.getMessage());
        }
        try {

            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if (effectedNum <= 0) {
                throw new ProductCategoryOperationException("商品类别删除失败");
            } else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("deleteProductCategory error:" + e.getMessage());
        }
    }
}
