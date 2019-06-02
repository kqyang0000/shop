package com.imocc.dao;

import com.imocc.entity.UserShopMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserShopMapDao {

    /**
     * @param userShopCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<UserShopMap> queryUserShopMapList(@Param("userShopCondition") UserShopMap userShopCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * @param userShopCondition
     * @return
     */
    int queryUserShopMapCount(@Param("userShopCondition") UserShopMap userShopCondition);

    /**
     * @param userId
     * @param shopId
     * @return
     */
    UserShopMap queryUserShopMap(@Param("userId") long userId, @Param("shopId") long shopId);

    /**
     * @param userShopMap
     * @return
     */
    int insertUserShopMap(UserShopMap userShopMap);

    /**
     * @param userShopMap
     * @return
     */
    int updateUserShopMap(UserShopMap userShopMap);
}