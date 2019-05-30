package com.imocc.service;

public interface CacheService {
    /**
     * <p>依据key前缀删除匹配该模式下的所有key-value 如传入:shopcategory，则shopcategory_allfirstlevel等
     * 以shopcategory打头的key-value都会被清空
     *
     * @param keyPrefix
     * @author kqyang
     * @version 1.0
     * @date 2019/4/28 20:14
     */
    void removeFromCache(String keyPrefix);
}
