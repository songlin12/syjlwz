package com.controller;

import com.model.Zpxs;
import com.service.ZpxsService;
import com.util.PageBean;

import java.util.List;

import com.response.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 照片像素Controller接口类
 */
@RestController
@RequestMapping("/api/zpxs")
public class ZpxsController {
    /**
     * 注入Service
     */
    @Resource
    private ZpxsService zpxsService;

    /**
     * 照片像素列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/list")
    @CrossOrigin
    public Response<List<Zpxs>> list(@RequestBody Zpxs zpxs, @RequestParam Integer currentPage, HttpServletRequest request) throws Exception {
        int offset = (currentPage - 1) * PageBean.PAGE_IETM;
        int counts = 0;  //总记录数
        PageBean page = new PageBean(offset);
        //查询记录总数
        counts = zpxsService.getCount(zpxs);
        //获取当前页记录
        List<Zpxs> zpxsList = zpxsService.queryZpxsList(zpxs, page);
        int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1;
        return Response.success(zpxsList, counts, page_count);
    }

    /**
     * 保存照片像素
     *
     * @param zpxs
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/add")
    @CrossOrigin
    public Response add(@RequestBody Zpxs zpxs, HttpServletRequest request) throws Exception {
        try {
            zpxsService.insertZpxs(zpxs);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 修改照片像素
     *
     * @param zpxs
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @CrossOrigin
    public Response update(@RequestBody Zpxs zpxs, HttpServletRequest request) throws Exception {
        try {
            zpxsService.updateZpxs(zpxs);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 删除照片像素
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
            zpxsService.deleteZpxs(id);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }
}
