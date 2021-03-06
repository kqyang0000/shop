package com.imocc.service.impl;

import com.imocc.dao.PersonInfoDao;
import com.imocc.dao.WechatAuthDao;
import com.imocc.dto.WechatAuthExecution;
import com.imocc.entity.PersonInfo;
import com.imocc.entity.WechatAuth;
import com.imocc.enums.WechatAuthStateEnum;
import com.imocc.exceptions.WechatAuthOperationException;
import com.imocc.service.WechatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {
    private static Logger logger = LoggerFactory.getLogger(WechatAuthServiceImpl.class);

    @Autowired
    private WechatAuthDao wechatAuthDao;
    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public WechatAuth getWechatAuthByOpenId(String openId) {
        return wechatAuthDao.queryWechatInfoByOpenId(openId);
    }

    @Transactional
    @Override
    public WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException {
        if (null == wechatAuth || null == wechatAuth.getOpenId()) {
            return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
        }
        try {
            // 设置创建时间
            wechatAuth.setCreateTime(new Date());
            // 如果微信账号里夹带着用户信息并且用户id为空，则认为该用户第一次使用平台（且通过微信登录）
            // 则自动创建用户信息
            if (null != wechatAuth.getPersonInfo() && null == wechatAuth.getPersonInfo().getUserId()) {
                try {
                    wechatAuth.getPersonInfo().setCreateTime(new Date());
                    wechatAuth.getPersonInfo().setLastEditTime(new Date());
                    wechatAuth.getPersonInfo().setEnableStatus(1);
                    PersonInfo personInfo = wechatAuth.getPersonInfo();
                    int effectedNum = personInfoDao.insertPersonInfo(personInfo);
                    wechatAuth.setPersonInfo(personInfo);
                    if (effectedNum <= 0) {
                        throw new WechatAuthOperationException("添加用户信息失败");
                    }
                } catch (Exception e) {
                    logger.error("insertPersonInfo error:" + e);
                    throw new WechatAuthOperationException("insertPersonInfo error:" + e.getMessage());
                }
            }
            // 创建专属于平台的微信账号
            int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
            if (effectedNum <= 0) {
                throw new WechatAuthOperationException("账号创建失败");
            } else {
                return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);
            }
        } catch (Exception e) {
            logger.error("insertWechatAuth error:" + e);
            throw new WechatAuthOperationException("insertWechatAuth error:" + e.getMessage());
        }
    }
}