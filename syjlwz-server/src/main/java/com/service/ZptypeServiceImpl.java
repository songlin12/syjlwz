package com.service;

import java.util.List;

import com.model.Zptype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import com.util.PageBean;

import java.util.Map;

import com.mapper.ZptypeMapper;

/**
 * 类型信息Service实现类
 */
@Service
public class ZptypeServiceImpl implements ZptypeService {
    /**
     * 注入mapper
     */
    @Autowired
    private ZptypeMapper zptypeMapper;

    /**
     * 查询类型信息记录数
     *
     * @param zptype
     * @return
     */
    public int getCount(Zptype zptype) {
        Map<String, Object> map = getQueryMap(zptype, null);
        return zptypeMapper.getCount(map);
    }

    /**
     * 查询所有类型信息
     *
     * @return
     */
    public List<Zptype> queryZptypeList(Zptype zptype, PageBean page) throws Exception {
        Map<String, Object> map = getQueryMap(zptype, page);
        List<Zptype> list = zptypeMapper.query(map);
        return list;
    }

    /**
     * 封装查询条件
     *
     * @param zptype
     * @param page
     * @return
     */
    private Map<String, Object> getQueryMap(Zptype zptype, PageBean page) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (zptype != null) {
            map.put("id", zptype.getId());
            map.put("name", zptype.getName());
        }
        if (page != null) {
            PageBean.setPageMap(map, page);
        }
        return map;
    }

    /**
     * 保存类型信息
     *
     * @param zptype
     * @return
     */
    public int insertZptype(Zptype zptype) throws Exception {
        return zptypeMapper.insertZptype(zptype);
    }

    /**
     * 删除类型信息
     *
     * @param id
     * @return
     */
    public int deleteZptype(int id) throws Exception {
        return zptypeMapper.deleteZptype(id);
    }

    /**
     * 更新类型信息
     *
     * @param zptype
     * @return
     */
    public int updateZptype(Zptype zptype) throws Exception {
        return zptypeMapper.updateZptype(zptype);
    }

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Zptype queryZptypeById(int id) throws Exception {
        return zptypeMapper.queryZptypeById(id);
    }
}
