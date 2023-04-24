package com.service;

import com.model.Admin;
import com.util.PageBean;

import java.util.List;
import java.util.Map;

public interface AdminService {

    public int getCount(Admin admin) throws Exception;

    public List<Admin> queryAdminList(Admin admin, PageBean page) throws Exception;

    public int insertAdmin(Admin admin) throws Exception;

    public int deleteAdmin(int id) throws Exception;

    public int updateAdmin(Admin admin) throws Exception;

    public Admin queryAdminById(int id) throws Exception;

}
