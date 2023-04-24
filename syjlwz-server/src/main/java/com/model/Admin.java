package com.model;

public class Admin {
    /**
     * 主键
     */
    private int id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String userpw;

    /**
     * 姓名
     */
    private String name;
    /**
     * 电话
     */
    private String tel;

    private String opwd;


    public String getOpwd() {
        return opwd;
    }

    public void setOpwd(String opwd) {
        this.opwd = opwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpw() {
        return userpw;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
