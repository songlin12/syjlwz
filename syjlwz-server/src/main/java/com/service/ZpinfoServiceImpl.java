package com.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.mapper.ZpinfoCollectionMapper;
import com.model.Zpinfo;
import com.model.ZpinfoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import com.util.PageBean;

import java.util.Map;

import com.mapper.ZpinfoMapper;

/**
 * 照片信息Service实现类
 */
@Service
public class ZpinfoServiceImpl implements ZpinfoService {
    /**
     * 注入mapper
     */
    @Autowired
    private ZpinfoMapper zpinfoMapper;

    @Autowired
    private ZpinfoCollectionMapper zpinfoCollectionMapper;
    /**
     * 查询照片信息记录数
     *
     * @param zpinfo
     * @return
     */
    public int getCount(Zpinfo zpinfo) {
        Map<String, Object> map = getQueryMap(zpinfo, null);
        return zpinfoMapper.getCount(map);
    }

    /**
     * 查询所有照片信息
     *
     * @return
     */
    public List<Zpinfo> queryZpinfoList(Zpinfo zpinfo, PageBean page) throws Exception {
        Map<String, Object> map = getQueryMap(zpinfo, page);
        List<Zpinfo> list = zpinfoMapper.queryAll(map);
        return list;
    }

    /**
     * 查询审核通过的照片信息
     *
     * @return
     */
    public List<Zpinfo> queryByState(Zpinfo zpinfo, PageBean page) throws Exception {
        Map<String, Object> map = getQueryMap(zpinfo, page);
        List<Zpinfo> list = zpinfoMapper.queryByState(map);
        return list;
    }

    /**
     * 封装查询条件
     *
     * @param zpinfo
     * @param page
     * @return
     */
    private Map<String, Object> getQueryMap(Zpinfo zpinfo, PageBean page) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (zpinfo != null) {
            map.put("id", zpinfo.getId());
            map.put("uid", zpinfo.getUid());
            map.put("qyid", zpinfo.getQyid());
            map.put("ztid", zpinfo.getZtid());
            map.put("xsid", zpinfo.getXsid());
            map.put("uname", zpinfo.getUname());
            map.put("qname", zpinfo.getQname());
        }
        if (page != null) {
            PageBean.setPageMap(map, page);
        }
        return map;
    }

    /**
     * 保存照片信息
     *
     * @param zpinfo
     * @return
     */
    public int insertZpinfo(Zpinfo zpinfo) throws Exception {
        return zpinfoMapper.insertZpinfo(zpinfo);
    }

    /**
     * 删除照片信息
     *
     * @param id
     * @return
     */
    public int deleteZpinfo(int id) throws Exception {
        return zpinfoMapper.deleteZpinfo(id);
    }

    /**
     * 更新照片信息
     *
     * @param zpinfo
     * @return
     */
    public int updateZpinfo(Zpinfo zpinfo) throws Exception {
        return zpinfoMapper.updateZpinfo(zpinfo);
    }

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public Zpinfo queryZpinfoById(int id) throws Exception {
        return zpinfoMapper.queryZpinfoById(id);
    }

    @Override
    public int addCollection(ZpinfoCollection zpinfoCollection) {
        Integer uid = zpinfoCollection.getUid();
        Integer zpid = zpinfoCollection.getZpid();
        ZpinfoCollection zc =zpinfoCollectionMapper.selectByUidAndZpid(uid,zpid);
        if (zc!=null){
            return zpinfoCollectionMapper.updateByPrimaryKeySelective(zpinfoCollection);
        }else {
            zpinfoCollection.setCdate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            return zpinfoCollectionMapper.insert(zpinfoCollection);
        }
    }
}
