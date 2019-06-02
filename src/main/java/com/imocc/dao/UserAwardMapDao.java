package com.imocc.dao;

import com.imocc.entity.UserAwardMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAwardMapDao {

    /**
     * <p>依据查询条件分页显示用户兑换奖品记录的列表信息
     *
     * @param userAwardCondition
     * @param rowIndex
     * @param pageSize
     * @return awardList
     * @author kqyang
     * @date 2019/6/2 14:45
     */
    List<UserAwardMap> queryUserAwardMapList(@Param("userAwardCondition") UserAwardMap userAwardCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * <p>依据查询条件查询用户兑换奖品记录数
     *
     * @param userAwardCondition
     * @return userAwardCount
     * @author kqyang
     * @date 2019/6/2 14:47
     */
    int queryUserAwardMapCount(@Param("userAwardCondition") UserAwardMap userAwardCondition);

    /**
     * <p>通过userAwardId查询用户兑换信息
     *
     * @param userAwardId
     * @return UserAwardMap
     * @author kqyang
     * @date 2019/6/2 14:50
     */
    UserAwardMap queryUserAwardMapById(long userAwardId);

    /**
     * <p>添加奖品兑换信息
     *
     * @param userAwardMap
     * @return 插入数量
     * @author kqyang
     * @date 2019/6/2 14::52
     */
    int insertUserAwardMap(UserAwardMap userAwardMap);

    /**
     * <p>更新奖品兑换信息
     *
     * @param userAwardMap
     * @return 修改数量
     * @author kqyang
     * @date 2019/6/2 14:54
     */
    int updateUserAwardMap(UserAwardMap userAwardMap);
}