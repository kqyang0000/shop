package com.imocc.service;


import com.imocc.entity.PersonInfo;

public interface PersonInfoService {

    /**
     * <p>通过userId 获取用户信息
     *
     * @param userId
     * @return PersonInfo
     * @author kqyang
     * @version 1.0
     * @date 2019/4/22 13:37
     */
    PersonInfo getPersonInfoById(long userId);
}
