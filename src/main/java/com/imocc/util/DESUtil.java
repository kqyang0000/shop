package com.imocc.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * <p>DES加密算法是一种堆成加密算法，即加密和解密使用相同秘钥的算法
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/4/22 20:43
 */
public class DESUtil {
    private static Key key;
    private static String KEY_STR = "o2o";
    private static String CHARSETNAME = "UTF-8";
    private static String ALGORITHM = "DES";

    static {
        try {
            // 生成DES算法对象
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            // 运用SHA1安全策略
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // 设置上秘钥种子
            secureRandom.setSeed(KEY_STR.getBytes());
            // 初始化基于SHA1的算法对象
            generator.init(secureRandom);
            // 生成秘钥对象
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        System.out.println("redis.hostname=" + getDecryptString("MONM4Rg8i9OYABmawCDn6A=="));
        System.out.println("redis.password=" + getDecryptString("PRxySZlsVwFF2ip81kw8q83er5/0OqF8"));
        System.out.println("redis.port=" + getDecryptString("fOlON+9ireE="));
        System.out.println("jdbc.username=" + getDecryptString("SD2pCqRvhjw="));
        System.out.println("jdbc.password=" + getDecryptString("PRxySZlsVwHVWAzQYb2pBs3er5/0OqF8"));
    }

    /**
     * <p>获取加密后的信息
     *
     * @param str
     * @author kqyang
     * @version 1.0
     * @date 2019/4/22 20:54
     */
    public static String getEncryptString(String str) {
        // 基于BASE64编码，接收byte[]并转换成String
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            // 按UTF8编码
            byte[] bytes = str.getBytes(CHARSETNAME);
            // 获取加密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化密码信息
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] doFinal = cipher.doFinal(bytes);
            // byte[] to encode好的String并返回
            return base64Encoder.encode(doFinal);
        } catch (Exception e) {
            // TODO 处理异常
            throw new RuntimeException();
        }
    }

    /**
     * <p>获取解密之后的信息
     *
     * @param str
     * @author kqyang
     * @version 1.0
     * @date 2019/4/23 13:50
     */
    public static String getDecryptString(String str) {
        // 基于BASE64编码，接收byte[]并转换成String
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            // 将字符串decode成byte[]
            byte[] bytes = base64Decoder.decodeBuffer(str);
            // 获取解密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化解密信息
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 解密
            byte[] doFinal = cipher.doFinal(bytes);
            // 返回解密之后的信息
            return new String(doFinal, CHARSETNAME);
        } catch (Exception e) {
            // TODO 处理异常
            throw new RuntimeException();
        }
    }
}