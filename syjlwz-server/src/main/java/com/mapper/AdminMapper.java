package com.mapper;

import com.model.Admin;

import java.util.List;
import java.util.Map;

public interface AdminMapper {
    public int getCount(Map<String, Object> inputParam);

    public List<Admin> query(Map<String, Object> inputParam);

    public int insertAdmin(Admin tAdmin);

    public int deleteAdmin(int id);

    public int updateAdmin(Admin tAdmin);

    public Admin queryAdminById(int id);

}
