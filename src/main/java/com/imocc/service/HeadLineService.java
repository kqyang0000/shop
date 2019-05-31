package com.imocc.service;


import com.imocc.entity.HeadLine;
import com.imocc.exceptions.HeadLineOperationException;

import java.util.List;

public interface HeadLineService {
    String HEADLINELISTKEY = "headlinelist";

    /**
     * <p>根据传入的条件返回指定的头条列表
     *
     * @param headLineCondition
     * @return 头条列表
     * @throws HeadLineOperationException
     * @author kqyang
     * @version 1.0
     * @date 2019/4/11 20:06
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws HeadLineOperationException;
}
