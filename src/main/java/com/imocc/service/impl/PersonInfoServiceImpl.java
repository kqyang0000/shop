package com.imocc.service.impl;

import com.imocc.dao.PersonInfoDao;
import com.imocc.entity.PersonInfo;
import com.imocc.service.PersonInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {
    private static Logger logger = LoggerFactory.getLogger(PersonInfoServiceImpl.class);

    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public PersonInfo getPersonInfoById(long userId) {
        return personInfoDao.queryPersonInfoById(userId);
    }
}