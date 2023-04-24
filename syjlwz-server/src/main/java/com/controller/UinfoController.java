package com.controller;

import com.model.Plinfo;
import com.model.Uinfo;
import com.model.Zpinfo;
import com.service.PlinfoService;
import com.service.UinfoService;
import com.service.ZpinfoService;
import com.util.EmailUtil;
import com.util.PageBean;

import java.util.List;

import com.response.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息Controller接口类
 */
@RestController
@RequestMapping("/api/uinfo")
public class UinfoController {
    /**
     * 注入Service
     */
    @Resource
    private UinfoService uinfoService;
    @Resource
    private ZpinfoService zpinfoService;
    @Resource
    private PlinfoService plinfoService;
    /**
     * 用户信息列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/list")
    @CrossOrigin
    public Response<List<Uinfo>> list(@RequestBody Uinfo uinfo, @RequestParam Integer currentPage, HttpServletRequest request) throws Exception {
        int offset = (currentPage - 1) * PageBean.PAGE_IETM;
        int counts = 0;  //总记录数
        PageBean page = new PageBean(offset);
        //查询记录总数
        counts = uinfoService.getCount(uinfo);
        //获取当前页记录
        List<Uinfo> uinfoList = uinfoService.queryUinfoList(uinfo, page);
        int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1;
        return Response.success(uinfoList, counts, page_count);
    }

    /**
     * 保存用户信息
     *
     * @param uinfo
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/add")
    @CrossOrigin
    public Response add(@RequestBody Uinfo uinfo, HttpServletRequest request) throws Exception {
        try {
            uinfoService.insertUinfo(uinfo);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 修改用户信息
     *
     * @param uinfo
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @CrossOrigin
    public Response update(@RequestBody Uinfo uinfo, HttpServletRequest request) throws Exception {
        try {
            uinfoService.updateUinfo(uinfo);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 删除用户信息
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
            uinfoService.deleteUinfo(id);
            Zpinfo zpinfo = new Zpinfo();
            zpinfo.setUid(id);
            List<Zpinfo> zpinfos = zpinfoService.queryZpinfoList(zpinfo,null);
            if(zpinfos!=null && zpinfos.size()>0){
                for (Zpinfo zp:zpinfos) {
                    Plinfo plinfo = new Plinfo();
                    plinfo.setZpid(zp.getId());
                    List<Plinfo> plinfos = plinfoService.queryPlinfoList(plinfo,null);
                    if(plinfos!=null && plinfos.size()>0){
                        for (Plinfo pl:plinfos) {
                            plinfoService.deletePlinfo(pl.getId());
                        }
                    }

                    zpinfoService.deleteZpinfo(zp.getId());
                }
            }
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }


    /**
     * 发送邮件警告
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/sendEmail")
    @CrossOrigin
    public Response sendEmail(@RequestParam Integer id, HttpServletRequest request) throws Exception {
        try {
            Uinfo uinfo = uinfoService.queryUinfoById(id);
            String message ="请上传合法的图片，发表合规的评论，否则就要删除账号！！！！";
            EmailUtil.sendEmail(uinfo.getEmail(),message);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }


    /**
     * 判断用户名是否存在
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/usernameExist")
    @CrossOrigin
    public Response usernameExist(@RequestParam String username, HttpServletRequest request) throws Exception {
        try {
            Uinfo uinfo = new Uinfo();
            uinfo.setUsername(username);
            List list = uinfoService.queryUinfoList(uinfo, null);
            if (list != null && list.size() > 0) {
                return Response.error(201, "用户名已存在");
            }
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }




    /**
     * 用户注册
     *
     * @param userinfo
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/uregister")
    @CrossOrigin
    public Response uregister(@RequestBody Uinfo userinfo, HttpServletRequest request) throws Exception {
        try {
            Uinfo uinfo = new Uinfo();
            uinfo.setUsername(userinfo.getUsername());
            List<Uinfo> ulist = uinfoService.queryUinfoList(uinfo,null);
            if(ulist!=null && ulist.size()>0){
                return Response.error(201,"用户名已存在");
            }else{
                uinfoService.insertUinfo(userinfo);
            }


        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 用户登录
     *
     * @param userinfo
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/login")
    @CrossOrigin
    public Response login(@RequestBody Uinfo userinfo, HttpServletRequest request) throws Exception {
        try {
            Uinfo uinfo = new Uinfo();
            uinfo.setUsername(userinfo.getUsername());
            uinfo.setUserpwd(userinfo.getUserpwd());
            List<Uinfo> ulist = uinfoService.queryUinfoList(uinfo,null);
            if(ulist!=null && ulist.size()>0){
                return Response.success(ulist.get(0));
            }else{
                return Response.error(201,"用户名密码错误");
            }
        } catch (Exception e) {
            return Response.error();
        }

    }

}
