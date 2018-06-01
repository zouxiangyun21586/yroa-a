package com.yr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片文件上传下载工具类
 * @author zxy
 *
 * 2018年6月1日 上午10:35:08
 *
 */
public final class FileUtils {
	
	private static final Integer S1 = 1;
	
	private FileUtils() {
		super();
	}

	/**
	 * 文件下载
	 * @author zxy
	 * 
	 * 2018年6月1日 上午10:46:58
	 */
	public void down() {
		try {
			File file = new File("E://123.jpg");
	        byte[] body = null;
	        InputStream is = new FileInputStream(file);
	        body = new byte[is.available()];
	        is.read(body);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
	        HttpStatus statusCode = HttpStatus.OK;
	        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
//	        return entity;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件上传
	 * @author zxy
	 * 
	 * 2018年6月1日 上午10:57:55
	 * 
	 * @param request 发送
	 * @param file 文件
	 * @return String
	 */
	public static String filesUpload(HttpServletRequest request, MultipartFile  file) {
		String path = null; // 文件路径
		String type = null; // 文件类型
		try {
			String fileName = file.getOriginalFilename(); // 文件原名称
			// 判断文件类型
			type = fileName.indexOf(".") != -S1 ? fileName.substring(fileName.lastIndexOf(".") + S1,
					fileName.length()) : null;
				if (type != null) { // 判断文件类型是否为空
		            if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
		            		|| "JPG".equals(type.toUpperCase())) {
		                // 项目在容器中实际发布运行的根路径
		                String realPath = request.getSession().getServletContext().getRealPath("/");
		                // 自定义的文件名称
		                String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
		                // 设置存放图片文件的路径 /*System.getProperty("file.separator")+*/
		                path = realPath + trueFileName; // 存放图片文件的路径
		                // 转存文件到指定的路径
		                file.transferTo(new File(path)); // 文件成功上传到指定目录下
		            } else {
		                return null; // 不是我们想要的文件类型,请按要求重新上传
		            }
		        } else {
		            return null; // 文件类型为空
		        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return path;
	}
	
	/**
	 * 保存文件
	 * @author zxy
	 * 
	 * 2018年6月1日 上午10:58:57
	 * 
	 * @param file 文件
	 * @param path 文件路径
	 * @return  文件路径
	 */
	private String saveFile(MultipartFile file, String path) {
	    // 判断文件是否为空  
		if (!file.isEmpty()) {  
		    try {
		        File filepath = new File(path);
		        if (!filepath.exists()) { // 如果文件 不存在
					filepath.mkdirs(); // 创建文件夹
					// 文件保存路径  
					String savePath = path + file.getOriginalFilename();
					// 转存文件  
		            file.transferTo(new File(savePath));
		            return "";
	        	}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return null;
	}  

}
