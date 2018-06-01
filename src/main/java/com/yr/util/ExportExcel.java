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

import com.yr.entity.StudentCheck;

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
	private final Integer e = 6;
	private final Integer f = 7;
	private final Integer g = 8;
	
    @Override  
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
    	List<StudentCheck> stuList = (List<StudentCheck>) model.get("stuLists");  
        String fileName = DateUtils.getCurrentDate() + "学生考勤.xls";  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/ms-excel"); // 文件下载  
        response.setHeader("Content-Disposition", "inline; filename=" + new String(fileName.getBytes(), "iso8859-1"));  
        OutputStream outputStream = response.getOutputStream();  
        HSSFWorkbook book = (HSSFWorkbook) workbook;  
        HSSFSheet sheet = book.createSheet();  
        HSSFRow row = sheet.createRow(0);  
        try {
        	
        String[] headers = new String[] {"数量", "届次名称", "学生姓名", "考勤时间", "上课时间", "到达时间", "迟到时间(分钟)", "状态", "请假条" };  
        	for (int i = 0; i < headers.length; i++) {  
        		row.createCell(i).setCellValue(headers[i]);  
        	}  
        	for (int i = 0; i < stuList.size(); i++) {  
        		StudentCheck stu = stuList.get(i);  
        		row = sheet.createRow(i + 1);  
        		row.createCell(0).setCellValue(i + 1); 
        		row.createCell(1).setCellValue(stu.getClassCode() + "-" + stu.getClassName());
        		row.createCell(a).setCellValue(stu.getStudentName());  
        	row.createCell(b).setCellValue(DateUtils.getCurrentDateT(stu.getCheckTime()) + stu.getCheckTimeDesc());
        		row.createCell(c).setCellValue(stu.getStartTime());
        		row.createCell(d).setCellValue(stu.getRetyTime());
        		row.createCell(e).setCellValue(stu.getLateTime());
        		if (0 == stu.getStatus()) {
        			row.createCell(f).setCellValue("没迟到");
        		} else if (1 == stu.getStatus()) {
        			row.createCell(f).setCellValue("迟到");
        		} else if (a == stu.getStatus()) {
        			row.createCell(f).setCellValue("旷课");
        		} else if (b == stu.getStatus()) {
        			row.createCell(f).setCellValue("请假");
        		} else if (c == stu.getStatus()) {
        			row.createCell(f).setCellValue("早退");
        		}
        		if (0 == stu.getIsNote()) {
        			row.createCell(g).setCellValue("没有");
        		} else {
        			row.createCell(g).setCellValue("有");
        		}
        	}  
        } catch (Exception e) {
        	e.printStackTrace();
        }
        book.write(outputStream);  
        outputStream.flush();  
        outputStream.close();  
    }  
}  
