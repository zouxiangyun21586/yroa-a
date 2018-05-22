package com.yr.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 * 
 * @作者 千毅
 *
 * @时间 2018年5月11日 上午11:05:26
 */
public class PageUtil {

    // 状态码
    private int code;
    // 返回的提示内容
    private String msg;
    // 每页大小
    private int pageSize;
    // 当前页
    private int pageCurr;
    // 总页数
    private int pageCount;
    // 总条数
    private int count;
    // 数据
    private List data = new ArrayList();

    /**
     * 根据传入的当前多少页
     * 
     * @param size
     * @param number
     * @param count
     */
    public PageUtil(int pageSize, int pageCurr, int count) {
        this.count = count;
        this.pageSize = pageSize;
        this.pageCurr = pageCurr < 0 ? 0 : pageCurr;
        this.pageCount = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
    }

    public PageUtil() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCurr() {
        return pageCurr;
    }

    /**
     * 设置当前页
     * 
     * @param pageCurr
     *            当前页
     */
    public void setpageCurr(int pageCurr) {
        this.pageCurr = pageCurr;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageUtil [code=" + code + ", msg=" + msg + ", page_size=" + pageSize + ", pageCurr=" + pageCurr
                + ", pageCount=" + pageCount + ", count=" + count + ", data=" + data + "]";
    }

    /**
     * 特殊字符转义
     * 
     * @param content
     *            需要转义的字符串
     * @return String
     */
    public static String decodeSpecialCharsWhenLikeUseSlash(String content) {
        String afterDecode = content.replaceAll("'", "''");
        afterDecode = afterDecode.replaceAll("\\\\", "\\\\\\\\");
        afterDecode = afterDecode.replaceAll("%", "\\\\%");
        afterDecode = afterDecode.replaceAll("_", "\\\\_");
        afterDecode = afterDecode.replaceAll("=", "\\=");
        return afterDecode;
    }

}
