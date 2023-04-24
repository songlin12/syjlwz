package com.mapper;

import com.model.Zptype;

import java.util.List;
import java.util.Map;

public interface ZptypeMapper {
    /**
     * 查询所有类型信息
     *
     * @return
     */
    public List<Zptype> query(Map<String, Object> inputParam);

    /**
     * 查询类型信息记录数
     *
     * @param inputParam
     * @return
     */
    public int getCount(Map<String, Object> inputParam);

    /**
     * 保存类型信息
     *
     * @param zptype
     * @return
     */
    public int insertZptype(Zptype zptype);

    /**
     * 删除类型信息
     *
     * @param id
     * @return
     */
    public int deleteZptype(int id);

    /**
     * 更新类型信息
     *
     * @param zptype
     * @return
     */
    public int updateZptype(Zptype zptype);

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Zptype queryZptypeById(int id);
}
