package com.imocc.dao;


import com.imocc.entity.Award;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AwardDao {

    /**
     * <p>依据查询条件分页显示奖品信息列表
     *
     * @param awardCondition
     * @param rowIndex
     * @param pageSize
     * @return awardList
     * @author kqyang
     * @date 2019/6/2 13:49
     */
    List<Award> queryAwardList(@Param("awardCondition") Award awardCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * <p>依据查询条件查询奖品数
     *
     * @param awardCondition
     * @return awardCount
     * @author kqyang
     * @date 2019/6/2 13:50
     */
    int queryAwardCount(@Param("awardCondition") Award awardCondition);

    /**
     * <p>通过awardId查询奖品信息
     *
     * @param awardId
     * @return Award
     * @author kqyang
     * @date 2019/6/2 13:52
     */
    Award queryAwardByAwardId(long awardId);

    /**
     * <p>添加奖品信息
     *
     * @param award
     * @return 插入数量
     * @author kqyang
     * @date 2019/6/2 13:53
     */
    int insertAward(Award award);

    /**
     * <p>更新奖品信息
     *
     * @param award
     * @return 修改数量
     * @author kqyang
     * @date 2019/6/2 13:54
     */
    int updateAward(Award award);

    /**
     * <p>删除奖品信息
     *
     * @param awardId
     * @param shopId
     * @return 删除数量
     * @author kqyang
     * @date 2019/6/2 13:55
     */
    int deleteAward(@Param("awardId") long awardId, @Param("shopId") long shopId);
}