package com.service;

import com.mapper.AdminMapper;
import com.model.Admin;
import com.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public int getCount(Admin admin) throws Exception {
        Map<String, Object> map = getQueryMap(admin, null);
        return adminMapper.getCount(map);
    }

    @Override
    public List<Admin> queryAdminList(Admin admin, PageBean page) throws Exception {
        Map<String, Object> map = getQueryMap(admin, page);
        List<Admin> adminList = adminMapper.query(map);
        return adminList;
    }

    /**
     * 封装查询条件
     *
     * @param admin
     * @param page
     * @return
     */
    private Map<String, Object> getQueryMap(Admin admin, PageBean page) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (admin != null) {
            map.put("username", admin.getUsername());
            map.put("userpw", admin.getUserpw());
            map.put("name", admin.getName());
        }
        if (page != null) {
            PageBean.setPageMap(map, page);
        }
        return map;
    }

    @Override
    public int insertAdmin(Admin admin) throws Exception {
        return adminMapper.insertAdmin(admin);
    }

    @Override
    public int deleteAdmin(int id) throws Exception {
        return adminMapper.deleteAdmin(id);
    }

    @Override
    public int updateAdmin(Admin admin) throws Exception {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public Admin queryAdminById(int id) throws Exception {
        return adminMapper.queryAdminById(id);
    }
}
