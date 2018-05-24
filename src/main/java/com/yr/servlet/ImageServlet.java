package com.java.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成验证码
 * 
 * @作者 千毅
 *
 * @时间 2018年5月11日 上午10:26:00
 */
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("image/jpeg"); // 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache"); // 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response); // 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
