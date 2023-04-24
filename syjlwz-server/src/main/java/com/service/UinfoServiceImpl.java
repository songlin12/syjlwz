package com.service;

import java.util.List;

import com.model.Uinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import com.util.PageBean;

import java.util.Map;

import com.mapper.UinfoMapper;

/**
 * 用户信息Service实现类
 */
@Service
public class UinfoServiceImpl implements UinfoService {
    /**
     * 注入mapper
     */
    @Autowired
    private UinfoMapper uinfoMapper;

    /**
     * 查询用户信息记录数
     *
     * @param uinfo
     * @return
     */
    public int getCount(Uinfo uinfo) {
        Map<String, Object> map = getQueryMap(uinfo, null);
        return uinfoMapper.getCount(map);
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<Uinfo> queryUinfoList(Uinfo uinfo, PageBean page) throws Exception {
        Map<String, Object> map = getQueryMap(uinfo, page);
        List<Uinfo> list = uinfoMapper.query(map);
        return list;
    }

    /**
     * 封装查询条件
     *
     * @param uinfo
     * @param page
     * @return
     */
    private Map<String, Object> getQueryMap(Uinfo uinfo, PageBean page) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (uinfo != null) {
            map.put("id", uinfo.getId());
            map.put("username", uinfo.getUsername());
            map.put("userpwd", uinfo.getUserpwd());
            map.put("name", uinfo.getName());
        }
        if (page != null) {
            PageBean.setPageMap(map, page);
        }
        return map;
    }

    /**
     * 保存用户信息
     *
     * @param uinfo
     * @return
     */
    public int insertUinfo(Uinfo uinfo) throws Exception {
        return uinfoMapper.insertUinfo(uinfo);
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    public int deleteUinfo(int id) throws Exception {
        return uinfoMapper.deleteUinfo(id);
    }

    /**
     * 更新用户信息
     *
     * @param uinfo
     * @return
     */
    public int updateUinfo(Uinfo uinfo) throws Exception {
        return uinfoMapper.updateUinfo(uinfo);
    }

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Uinfo queryUinfoById(int id) throws Exception {
        return uinfoMapper.queryUinfoById(id);
    }
}
