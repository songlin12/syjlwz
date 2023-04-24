package com.model;

import java.util.List;

/**
 * 照片信息Model类
 */
public class Zpinfo {
    public Zpinfo() {
    }

    /**
     * id
     */
    private Integer id;
    /**
     * 用户
     */
    private Integer uid;
    /**
     * 照片名称
     */
    private String name;
    /**
     * 区域
     */
    private Integer qyid;
    /**
     * 类型
     */
    private Integer ztid;
    /**
     * 像素
     */
    private Integer xsid;
    /**
     * 照片
     */
    private String fujian;
    /**
     * 发布日期
     */
    private String cdate;

    /**
     * 作品状态
     */
    private int state;
    private String uname;
    private Uinfo uinfoVO;
    private Zpqy zpqyVO;
    private Zptype zptypeVO;
    private Zpxs zpxsVO;
    private List<Plinfo> plist;
    private String qname;

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public List<Plinfo> getPlist() {
        return plist;
    }

    public void setPlist(List<Plinfo> plist) {
        this.plist = plist;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQyid() {
        return this.qyid;
    }

    public void setQyid(Integer qyid) {
        this.qyid = qyid;
    }

    public Integer getZtid() {
        return this.ztid;
    }

    public void setZtid(Integer ztid) {
        this.ztid = ztid;
    }

    public Integer getXsid() {
        return this.xsid;
    }

    public void setXsid(Integer xsid) {
        this.xsid = xsid;
    }

    public String getFujian() {
        return this.fujian;
    }

    public void setFujian(String fujian) {
        this.fujian = fujian;
    }

    public String getCdate() {
        return this.cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Uinfo getUinfoVO() {
        return this.uinfoVO;
    }

    public void setUinfoVO(Uinfo uinfoVO) {
        this.uinfoVO = uinfoVO;
    }

    public Zpqy getZpqyVO() {
        return this.zpqyVO;
    }

    public void setZpqyVO(Zpqy zpqyVO) {
        this.zpqyVO = zpqyVO;
    }

    public Zptype getZptypeVO() {
        return this.zptypeVO;
    }

    public void setZptypeVO(Zptype zptypeVO) {
        this.zptypeVO = zptypeVO;
    }

    public Zpxs getZpxsVO() {
        return this.zpxsVO;
    }

    public void setZpxsVO(Zpxs zpxsVO) {
        this.zpxsVO = zpxsVO;
    }
}
