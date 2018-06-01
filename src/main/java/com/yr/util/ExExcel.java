package com.yr.util;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.yr.entity.Report;

/** 
 * 原先继承的是AbstractExcelView,但是已经过时了,打开源码,推荐使用 
 * AbstractXlsView / AbstractXlsxView / AbstractXlsxStreamingView 
 * 
 */  
public class ExExcel extends AbstractXlsView {  
  
    @Override  
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
    	List<Report> stuList = (List<Report>) model.get("reprots");  
        String fileName = DateUtils.getCurrentDate() + "学生当天考勤.xls";  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/ms-excel"); // 文件下载  
        response.setHeader("Content-Disposition", "inline; filename=" + new String(fileName.getBytes(), "iso8859-1"));  
        OutputStream outputStream = response.getOutputStream();  
        HSSFWorkbook book = (HSSFWorkbook) workbook;  
        HSSFSheet sheet = book.createSheet();  
        HSSFRow row = sheet.createRow(0);  
        try {
        	
        String[] headers = new String[] {"数量", "当天考勤报告" };  
        	for (int i = 0; i < headers.length; i++) {  
        		row.createCell(i).setCellValue(headers[i]);  
        	}  
        	for (int i = 0; i < stuList.size(); i++) {  
        		Report report = stuList.get(i);  
        		row = sheet.createRow(i + 1);  
        		row.createCell(0).setCellValue(i + 1); 
        		row.createCell(1).setCellValue(report.getName() + report.getStatusDesc());
        	}  
        } catch (Exception e) {
        	e.printStackTrace();
        }
        book.write(outputStream);  
        outputStream.flush();  
        outputStream.close();  
    }  
}  
