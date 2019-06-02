package com.imocc.dao;

import com.imocc.entity.UserProductMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kqyang on 2019/6/2.
 */
public interface UserProductMapDao {
    /**
     * @param userProductCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<UserProductMap> queryUserProductMapList(@Param("userProductCondition") UserProductMap userProductCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * @param userProductCondition
     * @return
     */
    int queryUserProductMapCount(@Param("userProductCondition") UserProductMap userProductCondition);

    /**
     * @param userProductMap
     * @return
     */
    int insertUserProductMap(UserProductMap userProductMap);
}
