package com.imocc.service;

import com.imocc.dto.WechatAuthExecution;
import com.imocc.entity.PersonInfo;
import com.imocc.entity.WechatAuth;
import com.imocc.enums.WechatAuthStateEnum;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WechatAuthServiceTest {

    @Autowired
    private WechatAuthService wechatAuthService;

    @Test
    public void testRegister(){
        // 新增一条微信账号
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        String openId = "asdfagdfgasdg";
        personInfo.setCreateTime(new Date());
        personInfo.setName("测试一下");
        personInfo.setUserType(1);
        wechatAuth.setPersonInfo(personInfo);
        wechatAuth.setOpenId(openId);
        wechatAuth.setCreateTime(new Date());
        WechatAuthExecution we = wechatAuthService.register(wechatAuth);
        Assert.assertEquals(WechatAuthStateEnum.SUCCESS.getState(), we.getState());
        // 通过openId找到新增的wechatAuth
        wechatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
        System.out.println(wechatAuth.getPersonInfo().getName());
    }
}
