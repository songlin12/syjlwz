package com.service;

import java.util.List;

import com.model.ZpinfoCollection;
import com.util.PageBean;
import com.model.Zpinfo;

/**
 * 照片信息Service接口
 */
public interface ZpinfoService {
    /**
     * 查询照片信息记录数
     *
     * @param zpinfo
     * @return
     */
    public int getCount(Zpinfo zpinfo);

    /**
     * 查询所有照片信息
     *
     * @return
     */
    public List<Zpinfo> queryZpinfoList(Zpinfo zpinfo, PageBean page) throws Exception;

    /**
     * 查询审核通过的照片信息
     *
     * @return
     */
    public List<Zpinfo> queryByState(Zpinfo zpinfo, PageBean page) throws Exception;

    /**
     * 保存照片信息
     *
     * @param zpinfo
     * @return
     */
    public int insertZpinfo(Zpinfo zpinfo) throws Exception;

    /**
     * 删除照片信息
     *
     * @param id
     * @return
     */
    public int deleteZpinfo(int id) throws Exception;

    /**
     * 更新照片信息
     *
     * @param zpinfo
     * @return
     */
    public int updateZpinfo(Zpinfo zpinfo) throws Exception;

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Zpinfo queryZpinfoById(int id) throws Exception;

    public int addCollection(ZpinfoCollection zpinfoCollection);
}
