package com.yr.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Administrator
 *
 * 2018年5月22日 上午9:06:01
 *
 */
@Controller
public class AuthImageServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Integer T17 = 17;
	private static final Integer T255 = 255;
	private static final Integer T20 = 20;
	private static final Integer T110 = 110;
	private static final Integer T102 = 102;
	private static final Integer T200 = 200;
	private static final Integer T250 = 250;
	private static final Integer T6 = 6;
	private static final Integer T12 = 12;
	private static final Integer T155 = 155;
	private static final Integer T70 = 70;
	private static final Integer T65 = 65;
	private static final Integer T26 = 26;
	private static final Integer T16 = 16;
	private static final Integer T10 = 10;
	private static final Integer T160 = 160;
	private static final Integer T15 = 15;
	
	
	// 设置字母的大小,大小
	private Font mFont = new Font("Times New Roman", Font.PLAIN, T17);

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:06:14
	 * 
	 * @param fc  int 参数
	 * @param bc  int 参数
	 * @return 返回Color
	 */
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > T255) {
			fc = T255;
		}
		if (bc > T255) {
			bc = T255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:07:18
	 * 
	 * @param request 参数
	 * @param response 参数
	 * @throws ServletException 异常
	 * @throws IOException 异常
	 */
	@RequestMapping(value = "/authlmageServlet", method = RequestMethod.GET)
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 表明生成的响应是图片
		response.setContentType("image/jpeg");

		int width = T110, height = T20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(T200, T250));
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(T102, T102, T102));
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(mFont);

		g.setColor(getRandColor(T160, T200));

		// 画随机线
		for (int i = 0; i < T155; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(T6) + 1;
			int yl = random.nextInt(T12) + 1;
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 从另一方向画随机线
		for (int i = 0; i < T70; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(T12) + 1;
			int yl = random.nextInt(T6) + 1;
			g.drawLine(x, y, x - xl, y - yl);
		}

		// 生成随机数,并将随机数字转换为字母
		String sRand = "";
		for (int i = 0; i < T6; i++) { //设值验证码的位数
			int itmp = random.nextInt(T26) + T65;
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			g.setColor(new Color(T20 + random.nextInt(T110), T20 
					+ random.nextInt(T110), T20 + random.nextInt(T110)));
			g.drawString(String.valueOf(ctmp), T15 * i + T10, T16);
		}

		//把验证码的值放入到SESSION中保存起来
		HttpSession session = request.getSession(true);
		session.setAttribute("rand", sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

}
