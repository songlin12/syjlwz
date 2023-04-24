package com.service;

import java.util.List;

import com.util.PageBean;
import com.model.Zpxs;

/**
 * 照片像素Service接口
 */
public interface ZpxsService {
    /**
     * 查询照片像素记录数
     *
     * @param zpxs
     * @return
     */
    public int getCount(Zpxs zpxs);

    /**
     * 查询所有照片像素
     *
     * @return
     */
    public List<Zpxs> queryZpxsList(Zpxs zpxs, PageBean page) throws Exception;

    /**
     * 保存照片像素
     *
     * @param zpxs
     * @return
     */
    public int insertZpxs(Zpxs zpxs) throws Exception;

    /**
     * 删除照片像素
     *
     * @param id
     * @return
     */
    public int deleteZpxs(int id) throws Exception;

    /**
     * 更新照片像素
     *
     * @param zpxs
     * @return
     */
    public int updateZpxs(Zpxs zpxs) throws Exception;

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Zpxs queryZpxsById(int id) throws Exception;
}
