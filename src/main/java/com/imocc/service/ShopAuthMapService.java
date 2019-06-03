package com.imocc.service;


import com.imocc.dto.ShopAuthMapExecution;
import com.imocc.entity.ShopAuthMap;
import com.imocc.exceptions.ShopAuthMapOperationException;

public interface ShopAuthMapService {
    /**
     * <p>根据店铺id分页显示该店铺的授权信息
     *
     * @param shopId
     * @param pageIndex
     * @param pageSize
     * @author kqyang
     * @version 1.0
     * @date 2019/6/3 13:12
     */
    ShopAuthMapExecution listShopAuthMapByShopId(Long shopId, Integer pageIndex, Integer pageSize);

    /**
     * <p>根据shopAuthId返回对应的授权信息
     *
     * @param shopAuthId
     * @author kqyang
     * @version 1.0
     * @date 2019/6/3 13:14
     */
    ShopAuthMap getShopAuthMapById(Long shopAuthId);

    /**
     * <p>添加授权信息
     *
     * @param shopAuthMap
     * @throws ShopAuthMapOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/6/3 13:16
     */
    ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException;

    /**
     * <p>更新授权信息，包括职位，状态
     *
     * @param shopAuthMap
     * @throws ShopAuthMapOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/6/3 13:17
     */
    ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException;
}
