package com.service;

import java.util.List;

import com.util.PageBean;
import com.model.Zptype;

/**
 * 类型信息Service接口
 */
public interface ZptypeService {
    /**
     * 查询类型信息记录数
     *
     * @param zptype
     * @return
     */
    public int getCount(Zptype zptype);

    /**
     * 查询所有类型信息
     *
     * @return
     */
    public List<Zptype> queryZptypeList(Zptype zptype, PageBean page) throws Exception;

    /**
     * 保存类型信息
     *
     * @param zptype
     * @return
     */
    public int insertZptype(Zptype zptype) throws Exception;

    /**
     * 删除类型信息
     *
     * @param id
     * @return
     */
    public int deleteZptype(int id) throws Exception;

    /**
     * 更新类型信息
     *
     * @param zptype
     * @return
     */
    public int updateZptype(Zptype zptype) throws Exception;

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Zptype queryZptypeById(int id) throws Exception;
}
