package com.imocc.dto;


import com.imocc.entity.ShopAuthMap;
import com.imocc.enums.ShopAuthMapStateEnum;

import java.util.List;

public class ShopAuthMapExecution {
    /**
     * 结果状态
     */
    private int state;

    /**
     * 状态标识
     */
    private String stateInfo;

    /**
     * 店铺数量
     */
    private int count;

    private ShopAuthMap shopAuthMap;

    private List<ShopAuthMap> shopAuthMapList;

    public ShopAuthMapExecution() {
    }

    public ShopAuthMapExecution(ShopAuthMapStateEnum shopAuthMapStateEnum) {
        this.state = shopAuthMapStateEnum.getState();
        this.stateInfo = shopAuthMapStateEnum.getStateInfo();
    }

    public ShopAuthMapExecution(ShopAuthMapStateEnum shopAuthMapStateEnum, ShopAuthMap shopAuthMap) {
        this.state = shopAuthMapStateEnum.getState();
        this.stateInfo = shopAuthMapStateEnum.getStateInfo();
        this.shopAuthMap = shopAuthMap;
    }

    public ShopAuthMapExecution(ShopAuthMapStateEnum shopAuthMapStateEnum, List<ShopAuthMap> shopAuthMapList) {
        this.state = shopAuthMapStateEnum.getState();
        this.stateInfo = shopAuthMapStateEnum.getStateInfo();
        this.shopAuthMapList = shopAuthMapList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ShopAuthMap getShopAuthMap() {
        return shopAuthMap;
    }

    public void setShopAuthMap(ShopAuthMap shopAuthMap) {
        this.shopAuthMap = shopAuthMap;
    }

    public List<ShopAuthMap> getShopAuthMapList() {
        return shopAuthMapList;
    }

    public void setShopAuthMapList(List<ShopAuthMap> shopAuthMapList) {
        this.shopAuthMapList = shopAuthMapList;
    }
}
