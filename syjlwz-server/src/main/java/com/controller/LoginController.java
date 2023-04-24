package com.controller;

import com.model.Admin;
import com.response.Response;
import com.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/ulogin")
public class LoginController {
    @Resource
    private AdminService adminService;


    /**
     * 管理员登录
     *
     * @param admin
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/login")
    @CrossOrigin
    public Response login(@RequestBody Admin admin, HttpServletRequest request) throws Exception {
        try {
            List<Admin> adminList = adminService.queryAdminList(admin, null);
            if (adminList != null && adminList.size() > 0) {
                Admin ainfo = adminList.get(0);
                return Response.success(ainfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error();
        }
        return Response.error(201, "用户名密码错误");
    }


}
