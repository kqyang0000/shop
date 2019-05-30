package com.imocc.entity;

import java.util.Date;

/**
 * <p>商铺种类实体</p>
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/1/29 21:15
 */
public class ShopCategory {
    /**
     * ID
     */
    private Long shopCategoryId;
    /**
     * 商铺种类名称
     */
    private String shopCategoryName;
    /**
     * 商铺种类描述
     */
    private String shopCategoryDesc;
    /**
     * 商铺种类图片
     */
    private String shopCategoryImg;
    /**
     * 权重
     */
    private Integer priority;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date lastEditTime;
    /**
     * 商铺上级
     */
    private ShopCategory parent;


    public Long getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Long shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    public String getShopCategoryDesc() {
        return shopCategoryDesc;
    }

    public void setShopCategoryDesc(String shopCategoryDesc) {
        this.shopCategoryDesc = shopCategoryDesc;
    }

    public String getShopCategoryImg() {
        return shopCategoryImg;
    }

    public void setShopCategoryImg(String shopCategoryImg) {
        this.shopCategoryImg = shopCategoryImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public ShopCategory getParent() {
        return parent;
    }

    public void setParent(ShopCategory parent) {
        this.parent = parent;
    }
}
