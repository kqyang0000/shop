package com.imocc.service.impl;

import com.imocc.dao.LocalAuthDao;
import com.imocc.dto.LocalAuthExecution;
import com.imocc.entity.LocalAuth;
import com.imocc.enums.LocalAuthStateEnum;
import com.imocc.exceptions.LocalAuthOperationException;
import com.imocc.service.LocalAuthService;
import com.imocc.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {
    private static Logger logger = LoggerFactory.getLogger(LocalAuthServiceImpl.class);
    @Autowired
    private LocalAuthDao localAuthDao;

    @Override
    public LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password) {
        return localAuthDao.queryLocalByUserNameAndPwd(userName, MD5Util.getMd5(password));
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return localAuthDao.queryLocalByUserId(userId);
    }

    @Transactional
    @Override
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
        // 空值判断，传入的localAuth账号密码，用户信息特别是userId不能为空，否则直接返回错误
        if (null == localAuth || null == localAuth.getPersonInfo() || null == localAuth.getUsername() ||
                null == localAuth.getPassword() || null == localAuth.getPersonInfo().getUserId()) {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
        // 查询此用户是否已绑定过凭证账号
        LocalAuth tempAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
        if (null != tempAuth) {
            // 如果绑定过则直接退出，以保证平台账号的唯一性
            return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
        }
        try {
            // 如果之前没有绑定过平台账号，则创建一个平台账号与该用户绑定
            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(localAuth.getCreateTime());
            // 对密码进行MD5加密
            localAuth.setPassword(MD5Util.getMd5(localAuth.getPassword()));
            int effectedNum = localAuthDao.insertLocalAuth(localAuth);
            // 判断创建是否成功
            if (effectedNum <= 0) {
                throw new LocalAuthOperationException("账号绑定失败");
            } else {
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, localAuth);
            }
        } catch (Exception e) {
            throw new LocalAuthOperationException("insertLocalAuth error:" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public LocalAuthExecution modifyLocalAuth(Long userId, String userName, String password, String newPassword)
            throws LocalAuthOperationException {
        // 非空判断，判断传入的用户id 账号 新旧密码是否为空，新旧密码是否相同，若不满足条件则返回错误信息
        if (userId != null && userName != null && password != null && newPassword != null && !password.equals(newPassword)) {
            try {
                // 更新密码，并对新密码进行MD5加密
                int effectedNum = localAuthDao.updateLocalAuth(userId, userName, MD5Util.getMd5(password), MD5Util.getMd5(newPassword), new Date());
                // 判断是否更新成功
                if (effectedNum <= 0) {
                    throw new LocalAuthOperationException("用户名密码错误");
                }
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new LocalAuthOperationException(e.getMessage());
            }
        } else {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
    }
}
