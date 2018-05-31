package com.yr.util;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

/** 
 * 原先继承的是AbstractExcelView,但是已经过时了,打开源码,推荐使用 
 * AbstractXlsView / AbstractXlsxView / AbstractXlsxStreamingView 
 * 
 */  
public class ExportExcel extends AbstractXlsView {  
  
	private final Integer a = 2;
	private final Integer b = 3;
	private final Integer c = 4;
	private final Integer d = 5;
	
    @Override  
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
  
        String fileName = "下载测试.xls";  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/ms-excel"); // 文件下载  
        response.setHeader("Content-Disposition", "inline; filename=" + new String(fileName.getBytes(), "iso8859-1"));  
        OutputStream outputStream = response.getOutputStream();  
  
        HSSFWorkbook book = (HSSFWorkbook) workbook;  
        HSSFSheet sheet = book.createSheet();  
  
        HSSFRow row = sheet.createRow(0);  
        String[] headers = new String[] {"数量", "编号", "姓名", "年龄", "性别", "部门" };  
        for (int i = 0; i < headers.length; i++) {  
            row.createCell(i).setCellValue(headers[i]);  
        }  
  
//        List<User> stuList = (List<User>) model.get("stuList");  
//        for (int i = 0; i < stuList.size(); i++) {  
//            User stu = stuList.get(i);  
//            row = sheet.createRow(i + 1);  
//            row.createCell(0).setCellValue(i + 1); 
//            row.createCell(1).setCellValue(stu.getId());
//            row.createCell(a).setCellValue(stu.getName());  
//            row.createCell(b).setCellValue(stu.getAge());
//            if (stu.getSex().equalsIgnoreCase("0")) {
//                row.createCell(c).setCellValue("女");
//            } else {
//                row.createCell(c).setCellValue("男");
//            }
//            row.createCell(d).setCellValue(stu.getDepart().getDepartName());
//        }  
  
        book.write(outputStream);  
        outputStream.flush();  
        outputStream.close();  
  
    }  
}  
