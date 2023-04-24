package com.mapper;

import com.model.Uinfo;

import java.util.List;
import java.util.Map;

public interface UinfoMapper {
    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<Uinfo> query(Map<String, Object> inputParam);

    /**
     * 查询用户信息记录数
     *
     * @param inputParam
     * @return
     */
    public int getCount(Map<String, Object> inputParam);

    /**
     * 保存用户信息
     *
     * @param uinfo
     * @return
     */
    public int insertUinfo(Uinfo uinfo);

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    public int deleteUinfo(int id);

    /**
     * 更新用户信息
     *
     * @param uinfo
     * @return
     */
    public int updateUinfo(Uinfo uinfo);

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Uinfo queryUinfoById(int id);
}
