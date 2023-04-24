package com.service;

import java.util.List;

import com.util.PageBean;
import com.model.Uinfo;

/**
 * 用户信息Service接口
 */
public interface UinfoService {
    /**
     * 查询用户信息记录数
     *
     * @param uinfo
     * @return
     */
    public int getCount(Uinfo uinfo);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<Uinfo> queryUinfoList(Uinfo uinfo, PageBean page) throws Exception;

    /**
     * 保存用户信息
     *
     * @param uinfo
     * @return
     */
    public int insertUinfo(Uinfo uinfo) throws Exception;

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    public int deleteUinfo(int id) throws Exception;

    /**
     * 更新用户信息
     *
     * @param uinfo
     * @return
     */
    public int updateUinfo(Uinfo uinfo) throws Exception;

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Uinfo queryUinfoById(int id) throws Exception;
}
