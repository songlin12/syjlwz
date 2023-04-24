package com.mapper;

import com.model.Zpxs;

import java.util.List;
import java.util.Map;

public interface ZpxsMapper {
    /**
     * 查询所有照片像素
     *
     * @return
     */
    public List<Zpxs> query(Map<String, Object> inputParam);

    /**
     * 查询照片像素记录数
     *
     * @param inputParam
     * @return
     */
    public int getCount(Map<String, Object> inputParam);

    /**
     * 保存照片像素
     *
     * @param zpxs
     * @return
     */
    public int insertZpxs(Zpxs zpxs);

    /**
     * 删除照片像素
     *
     * @param id
     * @return
     */
    public int deleteZpxs(int id);

    /**
     * 更新照片像素
     *
     * @param zpxs
     * @return
     */
    public int updateZpxs(Zpxs zpxs);

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Zpxs queryZpxsById(int id);
}
