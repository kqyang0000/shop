package com.imocc.enums;

public enum ShopAuthMapStateEnum {
    SUCCESS(1, "操作成功"),
    INNER_ERROR(-1001, "操作失败"),
    NULL_SHOPAUTH_ID(-1002, "shopAuthId为空"),
    NULL_SHOPAUTH_INFO(-1003, "传入了空的信息");

    private int state;
    private String stateInfo;

    private ShopAuthMapStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * <p>依据传入的state返回相应的enum值
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/2/28 13:52
     */
    public static ShopAuthMapStateEnum stateOf(int state) {
        for (ShopAuthMapStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
