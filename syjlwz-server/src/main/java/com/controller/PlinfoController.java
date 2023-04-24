package com.controller;

import com.model.Plinfo;
import com.service.PlinfoService;
import com.util.PageBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.response.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.service.ZpinfoService;
import com.model.Zpinfo;
import com.service.UinfoService;
import com.model.Uinfo;

/**
 * 评论信息Controller接口类
 */
@RestController
@RequestMapping("/api/plinfo")
public class PlinfoController {
    /**
     * 注入Service
     */
    @Resource
    private PlinfoService plinfoService;
    @Resource
    private ZpinfoService zpinfoService;
    @Resource
    private UinfoService uinfoService;

    /**
     * 评论信息列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/list")
    @CrossOrigin
    public Response<List<Plinfo>> list(@RequestBody Plinfo plinfo, @RequestParam Integer currentPage, HttpServletRequest request) throws Exception {
        int offset = (currentPage - 1) * PageBean.PAGE_IETM;
        int counts = 0;  //总记录数
        PageBean page = new PageBean(offset);
        //查询记录总数
        counts = plinfoService.getCount(plinfo);
        //获取当前页记录
        List<Plinfo> plinfoList = plinfoService.queryPlinfoList(plinfo, page);
        int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1;
        return Response.success(plinfoList, counts, page_count);
    }

    /**
     * 保存评论信息
     *
     * @param plinfo
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/add")
    @CrossOrigin
    public Response add(@RequestBody Plinfo plinfo, HttpServletRequest request) throws Exception {
        try {
            plinfo.setPlsj(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            plinfoService.insertPlinfo(plinfo);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 修改评论信息
     *
     * @param plinfo
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @CrossOrigin
    public Response update(@RequestBody Plinfo plinfo, HttpServletRequest request) throws Exception {
        try {
            plinfoService.updatePlinfo(plinfo);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 删除评论信息
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
            plinfoService.deletePlinfo(id);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /*
     * 获取所有信息
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/listZpinfoAll")
    @CrossOrigin
    public Response<List<Zpinfo>> listZpinfoAll(HttpServletRequest request) throws Exception {
        Zpinfo zpinfo = new Zpinfo();
        List<Zpinfo> zpinfoList = zpinfoService.queryZpinfoList(zpinfo, null);
        request.setAttribute("zpinfoList", zpinfoList);
        return Response.success(zpinfoList);
    }

    /*
     * 获取所有信息
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/listUinfoAll")
    @CrossOrigin
    public Response<List<Uinfo>> listUinfoAll(HttpServletRequest request) throws Exception {
        Uinfo uinfo = new Uinfo();
        List<Uinfo> uinfoList = uinfoService.queryUinfoList(uinfo, null);
        request.setAttribute("uinfoList", uinfoList);
        return Response.success(uinfoList);
    }
}
