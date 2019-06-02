package com.imocc.dao;

import com.imocc.entity.ShopAuthMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopAuthMapDao {

    /**
     * @param shopId
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<ShopAuthMap> queryShopAuthMapListByShopId(@Param("shopId") long shopId, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * @param shopId
     * @return
     */
    int queryShopAuthMapCountByShopId(@Param("shopId") long shopId);

    /**
     * @param shopAuthMap
     * @return
     */
    int insertShopAuthMap(ShopAuthMap shopAuthMap);

    /**
     * @param shopAuthMap
     * @return
     */
    int updateUserShopMap(ShopAuthMap shopAuthMap);

    /**
     * 对某员工除权
     *
     * @param shopAuthId
     * @return
     */
    int deleteUserShopMap(long shopAuthId);

    /**
     * 查询员工授权信息
     *
     * @param shopAuthId
     * @return
     */
    ShopAuthMap queryShopAuthMapById(long shopAuthId);
}