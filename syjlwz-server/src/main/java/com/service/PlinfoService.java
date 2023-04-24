package com.service;

import java.util.List;

import com.util.PageBean;
import com.model.Plinfo;

/**
 * 评论信息Service接口
 */
public interface PlinfoService {
    /**
     * 查询评论信息记录数
     *
     * @param plinfo
     * @return
     */
    public int getCount(Plinfo plinfo);

    /**
     * 查询所有评论信息
     *
     * @return
     */
    public List<Plinfo> queryPlinfoList(Plinfo plinfo, PageBean page) throws Exception;

    /**
     * 保存评论信息
     *
     * @param plinfo
     * @return
     */
    public int insertPlinfo(Plinfo plinfo) throws Exception;

    /**
     * 删除评论信息
     *
     * @param id
     * @return
     */
    public int deletePlinfo(int id) throws Exception;

    /**
     * 更新评论信息
     *
     * @param plinfo
     * @return
     */
    public int updatePlinfo(Plinfo plinfo) throws Exception;

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Plinfo queryPlinfoById(int id) throws Exception;
}
