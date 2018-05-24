package com.yr.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @功能说明：生成验证码
 * @作者 千毅
 *
 * @时间 2018年5月11日 上午10:27:18
 */
public class RandomValidateCode {

    public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY"; // 放到session中的key
    private Random random = new Random();
    private String randString = "0123456789"; // 随机产生的字符串

    private final int width = 100; // 图片宽
    private final int height = 37; // 图片高
    private final int lineSize = 10; // 干扰线数量
    private final int stringNum = 6; // 随机产生字符数量
    private final int fontSize = 24; // 字体大小
    private static final Integer ONE = 1;
    private static final Integer TWO = 2;
    private static final Integer THREE = 3;
    private static final Integer FOUR = 4;
    private static final Integer FIVE = 5;

    /*
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, fontSize);
    }

    /**
     * 获得颜色
     * 
     * @param fc
     *            fc
     * @param bc
     *            bc
     * @return Color
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > ((FIVE * FIVE) * (FIVE * TWO) + FIVE)) {
            fc = ((FIVE * FIVE) * (FIVE * TWO) + FIVE);
        }
        if (bc > ((FIVE * FIVE) * (FIVE * TWO) + FIVE)) {
            bc = ((FIVE * FIVE) * (FIVE * TWO) + FIVE);
        }
        int r = fc + random.nextInt(bc - fc - (FOUR * FOUR));
        int g = fc + random.nextInt(bc - fc - (TWO * FIVE + FOUR));
        int b = fc + random.nextInt(bc - fc - ((TWO * FOUR) * TWO));
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片
     * 
     * @param request
     *            req
     * @param response
     *            resp
     */
    public void getRandcode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics(); // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, fontSize));
        g.setColor(getRandColor((TWO * FIVE * TWO * FIVE + (TWO * FIVE)),
                (TWO * FIVE * TWO * FIVE + (TWO * FIVE * THREE + THREE))));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
        }
        session.removeAttribute(RANDOMCODEKEY);
        session.setAttribute(RANDOMCODEKEY, randomString);
        g.dispose();
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream()); // 将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘制字符串
     * 
     * @param g
     *            g
     * @param randomString
     *            randomString
     * @param i
     *            i
     * @return String
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(TWO * FIVE * TWO * FIVE + ONE),
                random.nextInt(TWO * FIVE * TWO * FIVE + (TWO * FIVE + ONE)),
                random.nextInt(TWO * FIVE * TWO * FIVE + (TWO * FIVE * TWO + ONE))));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString += rand;
        g.translate(random.nextInt(THREE), random.nextInt(THREE));
        g.drawString(rand, (TWO * FIVE + THREE) * i, TWO * FIVE + THREE + THREE);
        return randomString;
    }

    /**
     * 绘制干扰线
     * 
     * @param g
     *            g
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(TWO * FIVE + THREE);
        int yl = random.nextInt(TWO * FIVE + FIVE);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     * 
     * @param num
     *            num
     * @return String
     */
    public String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

}
