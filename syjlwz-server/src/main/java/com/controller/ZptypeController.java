package com.controller;

import com.model.Zptype;
import com.service.ZptypeService;
import com.util.PageBean;

import java.util.List;

import com.response.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 类型信息Controller接口类
 */
@RestController
@RequestMapping("/api/zptype")
public class ZptypeController {
    /**
     * 注入Service
     */
    @Resource
    private ZptypeService zptypeService;

    /**
     * 类型信息列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/list")
    @CrossOrigin
    public Response<List<Zptype>> list(@RequestBody Zptype zptype, @RequestParam Integer currentPage, HttpServletRequest request) throws Exception {
        int offset = (currentPage - 1) * PageBean.PAGE_IETM;
        int counts = 0;  //总记录数
        PageBean page = new PageBean(offset);
        //查询记录总数
        counts = zptypeService.getCount(zptype);
        //获取当前页记录
        List<Zptype> zptypeList = zptypeService.queryZptypeList(zptype, page);
        int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1;
        return Response.success(zptypeList, counts, page_count);
    }

    /**
     * 保存类型信息
     *
     * @param zptype
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/add")
    @CrossOrigin
    public Response add(@RequestBody Zptype zptype, HttpServletRequest request) throws Exception {
        try {
            zptypeService.insertZptype(zptype);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 修改类型信息
     *
     * @param zptype
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @CrossOrigin
    public Response update(@RequestBody Zptype zptype, HttpServletRequest request) throws Exception {
        try {
            zptypeService.updateZptype(zptype);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 删除类型信息
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
            zptypeService.deleteZptype(id);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }
}
