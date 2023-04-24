package com.model;

/**
 * 评论信息Model类
 */
public class Plinfo {
    public Plinfo() {
    }

    /**
     * id
     */
    private Integer id;
    /**
     * 照片
     */
    private Integer zpid;
    /**
     * 用户
     */
    private Integer uid;
    /**
     * 评论内容
     */
    private String plnr;
    /**
     * 评论时间
     */
    private String plsj;

    private String plhf;
    private Zpinfo zpinfoVO;
    private Uinfo uinfoVO;
    private String zpname;
    private Integer zpuid;

    public String getPlhf() {
        return plhf;
    }

    public void setPlhf(String plhf) {
        this.plhf = plhf;
    }

    public Integer getZpuid() {
        return zpuid;
    }

    public void setZpuid(Integer zpuid) {
        this.zpuid = zpuid;
    }

    public String getZpname() {
        return zpname;
    }

    public void setZpname(String zpname) {
        this.zpname = zpname;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getZpid() {
        return this.zpid;
    }

    public void setZpid(Integer zpid) {
        this.zpid = zpid;
    }

    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPlnr() {
        return this.plnr;
    }

    public void setPlnr(String plnr) {
        this.plnr = plnr;
    }

    public String getPlsj() {
        return this.plsj;
    }

    public void setPlsj(String plsj) {
        this.plsj = plsj;
    }

    public Zpinfo getZpinfoVO() {
        return this.zpinfoVO;
    }

    public void setZpinfoVO(Zpinfo zpinfoVO) {
        this.zpinfoVO = zpinfoVO;
    }

    public Uinfo getUinfoVO() {
        return this.uinfoVO;
    }

    public void setUinfoVO(Uinfo uinfoVO) {
        this.uinfoVO = uinfoVO;
    }
}
