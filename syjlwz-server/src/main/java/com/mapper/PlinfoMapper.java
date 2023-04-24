package com.mapper;

import com.model.Plinfo;

import java.util.List;
import java.util.Map;

public interface PlinfoMapper {
    /**
     * 查询所有评论信息
     *
     * @return
     */
    public List<Plinfo> query(Map<String, Object> inputParam);

    /**
     * 查询评论信息记录数
     *
     * @param inputParam
     * @return
     */
    public int getCount(Map<String, Object> inputParam);

    /**
     * 保存评论信息
     *
     * @param plinfo
     * @return
     */
    public int insertPlinfo(Plinfo plinfo);

    /**
     * 删除评论信息
     *
     * @param id
     * @return
     */
    public int deletePlinfo(int id);

    /**
     * 更新评论信息
     *
     * @param plinfo
     * @return
     */
    public int updatePlinfo(Plinfo plinfo);

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Plinfo queryPlinfoById(int id);
}
