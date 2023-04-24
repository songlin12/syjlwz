package com.service;

import java.util.List;

import com.util.PageBean;
import com.model.Zpqy;

/**
 * 照片区域信息Service接口
 */
public interface ZpqyService {
    /**
     * 查询照片区域信息记录数
     *
     * @param zpqy
     * @return
     */
    public int getCount(Zpqy zpqy);

    /**
     * 查询所有照片区域信息
     *
     * @return
     */
    public List<Zpqy> queryZpqyList(Zpqy zpqy, PageBean page) throws Exception;

    /**
     * 保存照片区域信息
     *
     * @param zpqy
     * @return
     */
    public int insertZpqy(Zpqy zpqy) throws Exception;

    /**
     * 删除照片区域信息
     *
     * @param id
     * @return
     */
    public int deleteZpqy(int id) throws Exception;

    /**
     * 更新照片区域信息
     *
     * @param zpqy
     * @return
     */
    public int updateZpqy(Zpqy zpqy) throws Exception;

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Zpqy queryZpqyById(int id) throws Exception;
}
