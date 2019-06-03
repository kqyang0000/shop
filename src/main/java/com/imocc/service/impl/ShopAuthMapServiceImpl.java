package com.imocc.service.impl;

import com.imocc.dao.ShopAuthMapDao;
import com.imocc.dto.ShopAuthMapExecution;
import com.imocc.entity.ShopAuthMap;
import com.imocc.enums.ShopAuthMapStateEnum;
import com.imocc.exceptions.ShopAuthMapOperationException;
import com.imocc.service.ShopAuthMapService;
import com.imocc.util.PageCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ShopAuthMapServiceImpl implements ShopAuthMapService {
    private static Logger logger = LoggerFactory.getLogger(ShopAuthMapServiceImpl.class);

    @Autowired
    private ShopAuthMapDao shopAuthMapDao;

    @Override
    public ShopAuthMapExecution listShopAuthMapByShopId(Long shopId, Integer pageIndex, Integer pageSize) {
        if (shopId != null && pageIndex != null && pageSize != null) {
            int beginIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
            List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapListByShopId(shopId, beginIndex, pageSize);
            int count = shopAuthMapDao.queryShopAuthMapCountByShopId(shopId);
            ShopAuthMapExecution se = new ShopAuthMapExecution();
            se.setShopAuthMapList(shopAuthMapList);
            se.setCount(count);
            return se;
        } else {
            return null;
        }
    }

    @Override
    public ShopAuthMap getShopAuthMapById(Long shopAuthId) {
        return shopAuthMapDao.queryShopAuthMapById(shopAuthId);
    }

    @Transactional
    @Override
    public ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException {
        if (shopAuthMap != null && shopAuthMap.getShop() != null && shopAuthMap.getShop().getShopId() != null &&
                shopAuthMap.getEmployee() != null && shopAuthMap.getEmployee().getUserId() != null) {
            shopAuthMap.setCreateTime(new Date());
            shopAuthMap.setLastEditTime(shopAuthMap.getCreateTime());
            shopAuthMap.setEnableStatus(1);
            shopAuthMap.setTitleFlag(0);
            try {
                int effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
                if (effectedNum <= 0) {
                    throw new ShopAuthMapOperationException("添加授权失败");
                }
                return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS, shopAuthMap);
            } catch (Exception e) {
                throw new ShopAuthMapOperationException("添加授权失败 errorMsg: " + e);
            }
        } else {
            return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTH_INFO);
        }
    }

    @Transactional
    @Override
    public ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException {
        if (shopAuthMap != null && shopAuthMap.getShopAuthId() != null) {
            try {
                int effectedNum = shopAuthMapDao.updateUserShopMap(shopAuthMap);
                if (effectedNum <= 0) {
                    return new ShopAuthMapExecution(ShopAuthMapStateEnum.INNER_ERROR);
                } else {
                    return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS, shopAuthMap);
                }
            } catch (Exception e) {
                throw new ShopAuthMapOperationException("modifyShopAuthMap() errorMsg: " + e);
            }
        } else {
            return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTH_INFO);
        }
    }
}