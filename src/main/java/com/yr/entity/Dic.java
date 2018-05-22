package com.yr.entity;

/**
 * 参考数据库
 * @author Administrator
 *
 * 2018年5月22日 上午9:04:59
 *
 */
public class Dic {
    private Integer id;

    private String type;

    private String typeName;

    private String keyw;

    private String val;

    private String pKey;

    private String isUsed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getKeyw() {
        return keyw;
    }

    public void setKeyw(String keyw) {
        this.keyw = keyw == null ? null : keyw.trim();
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val == null ? null : val.trim();
    }

    /**
     * 
     * @author zxy
     * 
     * 2018年5月22日 上午9:05:20
     * 
     * @return String
     */
    public String getpKey() {
        return pKey;
    }

    /**
     * 
     * @author zxy
     * 
     * 2018年5月22日 上午9:05:24
     * 
     * @param pKey pKey
     */
    public void setpKey(String pKey) {
        this.pKey = pKey == null ? null : pKey.trim();
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed == null ? null : isUsed.trim();
    }
}
