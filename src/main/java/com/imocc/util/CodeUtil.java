package com.imocc.util;

import com.google.code.kaptcha.Constants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CodeUtil {
    /**
     * 验证码校验
     *
     * @param request
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
        if (null == verifyCodeExpected || null == verifyCodeActual || !verifyCodeExpected.toLowerCase().equals(verifyCodeActual.toLowerCase())) {
            return false;
        }
        return true;
    }

    /**
     * <p>生成二维码图片流
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/6/3 13:46
     */
    public static BitMatrix generateQRCodeStream(String content, HttpServletResponse response) {
        // 给响应添加头部信息，主要是告诉浏览器返回的是图片流
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        // 设置图片的文字编码以及内边框距
        Map<EncodeHintType, Object> hints = new HashMap<>(16);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix;
        try {
            // 参数顺序分别为: 编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 300, 300);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bitMatrix;
    }
}