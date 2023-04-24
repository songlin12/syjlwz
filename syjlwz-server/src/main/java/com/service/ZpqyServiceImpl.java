package com.service;

import java.util.List;

import com.model.Zpqy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import com.util.PageBean;

import java.util.Map;

import com.mapper.ZpqyMapper;

/**
 * 照片区域信息Service实现类
 */
@Service
public class ZpqyServiceImpl implements ZpqyService {
    /**
     * 注入mapper
     */
    @Autowired
    private ZpqyMapper zpqyMapper;

    /**
     * 查询照片区域信息记录数
     *
     * @param zpqy
     * @return
     */
    public int getCount(Zpqy zpqy) {
        Map<String, Object> map = getQueryMap(zpqy, null);
        return zpqyMapper.getCount(map);
    }

    /**
     * 查询所有照片区域信息
     *
     * @return
     */
    public List<Zpqy> queryZpqyList(Zpqy zpqy, PageBean page) throws Exception {
        Map<String, Object> map = getQueryMap(zpqy, page);
        List<Zpqy> list = zpqyMapper.query(map);
        return list;
    }

    /**
     * 封装查询条件
     *
     * @param zpqy
     * @param page
     * @return
     */
    private Map<String, Object> getQueryMap(Zpqy zpqy, PageBean page) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (zpqy != null) {
            map.put("id", zpqy.getId());
            map.put("name", zpqy.getName());
        }
        if (page != null) {
            PageBean.setPageMap(map, page);
        }
        return map;
    }

    /**
     * 保存照片区域信息
     *
     * @param zpqy
     * @return
     */
    public int insertZpqy(Zpqy zpqy) throws Exception {
        return zpqyMapper.insertZpqy(zpqy);
    }

    /**
     * 删除照片区域信息
     *
     * @param id
     * @return
     */
    public int deleteZpqy(int id) throws Exception {
        return zpqyMapper.deleteZpqy(id);
    }

    /**
     * 更新照片区域信息
     *
     * @param zpqy
     * @return
     */
    public int updateZpqy(Zpqy zpqy) throws Exception {
        return zpqyMapper.updateZpqy(zpqy);
    }

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Zpqy queryZpqyById(int id) throws Exception {
        return zpqyMapper.queryZpqyById(id);
    }
}
