package com.mapper;

import com.model.Zpinfo;

import java.util.List;
import java.util.Map;

public interface ZpinfoMapper {
    /**
     * 查询所有审核状态通过的照片信息
     *
     * @return
     */
    public List<Zpinfo> queryByState(Map<String, Object> inputParam);

    /**
     * 查询所有的照片信息
     *
     * @return
     */
    public List<Zpinfo> queryAll(Map<String, Object> inputParam);

    /**
     * 查询照片信息记录数
     *
     * @param inputParam
     * @return
     */
    public int getCount(Map<String, Object> inputParam);

    /**
     * 保存照片信息
     *
     * @param zpinfo
     * @return
     */
    public int insertZpinfo(Zpinfo zpinfo);

    /**
     * 删除照片信息
     *
     * @param id
     * @return
     */
    public int deleteZpinfo(int id);

    /**
     * 更新照片信息
     *
     * @param zpinfo
     * @return
     */
    public int updateZpinfo(Zpinfo zpinfo);

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Zpinfo queryZpinfoById(int id);
}
