package com.service;

import java.util.List;

import com.model.Plinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import com.util.PageBean;

import java.util.Map;

import com.mapper.PlinfoMapper;

/**
 * 评论信息Service实现类
 */
@Service
public class PlinfoServiceImpl implements PlinfoService {
    /**
     * 注入mapper
     */
    @Autowired
    private PlinfoMapper plinfoMapper;

    /**
     * 查询评论信息记录数
     *
     * @param plinfo
     * @return
     */
    public int getCount(Plinfo plinfo) {
        Map<String, Object> map = getQueryMap(plinfo, null);
        return plinfoMapper.getCount(map);
    }

    /**
     * 查询所有评论信息
     *
     * @return
     */
    public List<Plinfo> queryPlinfoList(Plinfo plinfo, PageBean page) throws Exception {
        Map<String, Object> map = getQueryMap(plinfo, page);
        List<Plinfo> list = plinfoMapper.query(map);
        return list;
    }

    /**
     * 封装查询条件
     *
     * @param plinfo
     * @param page
     * @return
     */
    private Map<String, Object> getQueryMap(Plinfo plinfo, PageBean page) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (plinfo != null) {
            map.put("id", plinfo.getId());
            map.put("zpid", plinfo.getZpid());
            map.put("uid", plinfo.getUid());
            map.put("zpname", plinfo.getZpname());
            map.put("zpuid", plinfo.getZpuid());

        }
        if (page != null) {
            PageBean.setPageMap(map, page);
        }
        return map;
    }

    /**
     * 保存评论信息
     *
     * @param plinfo
     * @return
     */
    public int insertPlinfo(Plinfo plinfo) throws Exception {
        return plinfoMapper.insertPlinfo(plinfo);
    }

    /**
     * 删除评论信息
     *
     * @param id
     * @return
     */
    public int deletePlinfo(int id) throws Exception {
        return plinfoMapper.deletePlinfo(id);
    }

    /**
     * 更新评论信息
     *
     * @param plinfo
     * @return
     */
    public int updatePlinfo(Plinfo plinfo) throws Exception {
        return plinfoMapper.updatePlinfo(plinfo);
    }

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Plinfo queryPlinfoById(int id) throws Exception {
        return plinfoMapper.queryPlinfoById(id);
    }
}
