package com.model;

/**
 * 照片像素Model类
 */
public class Zpxs {
    public Zpxs() {
    }

    /**
     * id
     */
    private Integer id;
    /**
     * 像素
     */
    private String name;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
