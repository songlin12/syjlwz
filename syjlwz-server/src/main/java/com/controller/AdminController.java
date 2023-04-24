package com.controller;

import com.model.Admin;
import com.response.Response;
import com.service.AdminService;
import com.util.PageBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    /**
     * 管理员管理列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/list")
    @CrossOrigin
    public Response<List<Admin>> list(@RequestBody Admin admin, @RequestParam Integer currentPage, HttpServletRequest request) throws Exception {
        int offset = (currentPage - 1) * PageBean.PAGE_IETM;
        int counts = 0;  //总记录数
        PageBean page = new PageBean(offset);
        //查询记录总数
        counts = adminService.getCount(admin);
        //获取当前页记录
        List<Admin> adminList = adminService.queryAdminList(admin, page);
        int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1;
        return Response.success(adminList, counts, page_count);
    }


    /**
     * 保存新增管理员管理
     *
     * @param admin
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/add")
    @CrossOrigin
    public Response add(@RequestBody Admin admin, HttpServletRequest request) throws Exception {
        try {
            adminService.insertAdmin(admin);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 修改管理员信息
     *
     * @param admin
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @CrossOrigin
    public Response update(@RequestBody Admin admin, HttpServletRequest request) throws Exception {
        try {
            adminService.updateAdmin(admin);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 删除管理员
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/del")
    @CrossOrigin
    public Response del(@RequestParam Integer id, HttpServletRequest request) throws Exception {
        try {
            adminService.deleteAdmin(id);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }


    /**
     * 修改密码
     *
     * @param admin
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/updatePwd")
    @CrossOrigin
    public Response updatePwd(@RequestBody Admin admin, HttpServletRequest request) throws Exception {
        try {
            Admin oadmin = adminService.queryAdminById(admin.getId());
            if (!oadmin.getUserpw().equals(admin.getOpwd())) {
                return Response.error(201, "原密码错误");
            } else {
                oadmin.setUserpw(admin.getUserpw());
                adminService.updateAdmin(oadmin);
                return Response.success();
            }
        } catch (Exception e) {
            return Response.error();
        }
    }

    /**
     * 检验用户名是否唯一
     *
     * @param username
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/checkUsername")
    @CrossOrigin
    public Response checkUsername(@RequestParam String username, HttpServletRequest request) throws Exception {
        try {
            Admin admin = new Admin();
            admin.setUsername(username);
            List<Admin> adminList = adminService.queryAdminList(admin, null);
            if (adminList != null && adminList.size() > 0) {
                return Response.error(201, "用户名已存在");
            }
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }


}
