package com.mapper;

import com.model.Zpqy;

import java.util.List;
import java.util.Map;

public interface ZpqyMapper {
    /**
     * 查询所有照片区域信息
     *
     * @return
     */
    public List<Zpqy> query(Map<String, Object> inputParam);

    /**
     * 查询照片区域信息记录数
     *
     * @param inputParam
     * @return
     */
    public int getCount(Map<String, Object> inputParam);

    /**
     * 保存照片区域信息
     *
     * @param zpqy
     * @return
     */
    public int insertZpqy(Zpqy zpqy);

    /**
     * 删除照片区域信息
     *
     * @param id
     * @return
     */
    public int deleteZpqy(int id);

    /**
     * 更新照片区域信息
     *
     * @param zpqy
     * @return
     */
    public int updateZpqy(Zpqy zpqy);

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Zpqy queryZpqyById(int id);
}
