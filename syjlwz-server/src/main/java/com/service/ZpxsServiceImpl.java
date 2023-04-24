package com.service;

import java.util.List;

import com.model.Zpxs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import com.util.PageBean;

import java.util.Map;

import com.mapper.ZpxsMapper;

/**
 * 照片像素Service实现类
 */
@Service
public class ZpxsServiceImpl implements ZpxsService {
    /**
     * 注入mapper
     */
    @Autowired
    private ZpxsMapper zpxsMapper;

    /**
     * 查询照片像素记录数
     *
     * @param zpxs
     * @return
     */
    public int getCount(Zpxs zpxs) {
        Map<String, Object> map = getQueryMap(zpxs, null);
        return zpxsMapper.getCount(map);
    }

    /**
     * 查询所有照片像素
     *
     * @return
     */
    public List<Zpxs> queryZpxsList(Zpxs zpxs, PageBean page) throws Exception {
        Map<String, Object> map = getQueryMap(zpxs, page);
        List<Zpxs> list = zpxsMapper.query(map);
        return list;
    }

    /**
     * 封装查询条件
     *
     * @param zpxs
     * @param page
     * @return
     */
    private Map<String, Object> getQueryMap(Zpxs zpxs, PageBean page) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (zpxs != null) {
            map.put("id", zpxs.getId());
            map.put("name", zpxs.getName());
        }
        if (page != null) {
            PageBean.setPageMap(map, page);
        }
        return map;
    }

    /**
     * 保存照片像素
     *
     * @param zpxs
     * @return
     */
    public int insertZpxs(Zpxs zpxs) throws Exception {
        return zpxsMapper.insertZpxs(zpxs);
    }

    /**
     * 删除照片像素
     *
     * @param id
     * @return
     */
    public int deleteZpxs(int id) throws Exception {
        return zpxsMapper.deleteZpxs(id);
    }

    /**
     * 更新照片像素
     *
     * @param zpxs
     * @return
     */
    public int updateZpxs(Zpxs zpxs) throws Exception {
        return zpxsMapper.updateZpxs(zpxs);
    }

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Zpxs queryZpxsById(int id) throws Exception {
        return zpxsMapper.queryZpxsById(id);
    }
}
