package com.controller;

import com.model.Zpqy;
import com.service.ZpqyService;
import com.util.PageBean;

import java.util.List;

import com.response.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 照片区域信息Controller接口类
 */
@RestController
@RequestMapping("/api/zpqy")
public class ZpqyController {
    /**
     * 注入Service
     */
    @Resource
    private ZpqyService zpqyService;

    /**
     * 照片区域信息列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/list")
    @CrossOrigin
    public Response<List<Zpqy>> list(@RequestBody Zpqy zpqy, @RequestParam Integer currentPage, HttpServletRequest request) throws Exception {
        int offset = (currentPage - 1) * PageBean.PAGE_IETM;
        int counts = 0;  //总记录数
        PageBean page = new PageBean(offset);
        //查询记录总数
        counts = zpqyService.getCount(zpqy);
        //获取当前页记录
        List<Zpqy> zpqyList = zpqyService.queryZpqyList(zpqy, page);
        int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1;
        return Response.success(zpqyList, counts, page_count);
    }

    /**
     * 保存照片区域信息
     *
     * @param zpqy
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/add")
    @CrossOrigin
    public Response add(@RequestBody Zpqy zpqy, HttpServletRequest request) throws Exception {
        try {
            zpqyService.insertZpqy(zpqy);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 修改照片区域信息
     *
     * @param zpqy
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @CrossOrigin
    public Response update(@RequestBody Zpqy zpqy, HttpServletRequest request) throws Exception {
        try {
            zpqyService.updateZpqy(zpqy);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 删除照片区域信息
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
            zpqyService.deleteZpqy(id);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }
}
