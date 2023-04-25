package com.model;

import java.io.Serializable;

/**
 * 
 * @TableName zpinfo_collection
 */
public class ZpinfoCollection implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 作品id
     */
    private Integer zpid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 收藏时间
     */
    private String cdate;

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 作品id
     */
    public Integer getZpid() {
        return zpid;
    }

    /**
     * 作品id
     */
    public void setZpid(Integer zpid) {
        this.zpid = zpid;
    }

    /**
     * 用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 收藏时间
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 收藏时间
     */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ZpinfoCollection other = (ZpinfoCollection) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getZpid() == null ? other.getZpid() == null : this.getZpid().equals(other.getZpid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getCdate() == null ? other.getCdate() == null : this.getCdate().equals(other.getCdate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getZpid() == null) ? 0 : getZpid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getCdate() == null) ? 0 : getCdate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", zpid=").append(zpid);
        sb.append(", uid=").append(uid);
        sb.append(", cdate=").append(cdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}