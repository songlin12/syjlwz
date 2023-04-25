package com.controller;

import com.model.*;
import com.service.*;
import com.util.PageBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.response.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 照片信息Controller接口类
 */
@RestController
@RequestMapping("/api/zpinfo")
public class ZpinfoController {
    /**
     * 注入Service
     */
    @Resource
    private ZpinfoService zpinfoService;
    @Resource
    private UinfoService uinfoService;
    @Resource
    private ZpqyService zpqyService;
    @Resource
    private ZptypeService zptypeService;
    @Resource
    private ZpxsService zpxsService;
    @Resource
    private PlinfoService plinfoService;
    /**
     * 照片信息列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/list")
    @CrossOrigin
    public Response<List<Zpinfo>> list(@RequestBody Zpinfo zpinfo, @RequestParam Integer currentPage, HttpServletRequest request) throws Exception {
        int offset = (currentPage - 1) * PageBean.PAGE_IETM;
        int counts = 0;  //总记录数
        PageBean page = new PageBean(offset);
        //查询记录总数
        counts = zpinfoService.getCount(zpinfo);
        //获取当前页记录
        List<Zpinfo> zpinfoList = zpinfoService.queryZpinfoList(zpinfo, page);
        int page_count = counts % PageBean.PAGE_IETM == 0 ? counts / PageBean.PAGE_IETM : counts / PageBean.PAGE_IETM + 1;
        return Response.success(zpinfoList, counts, page_count);
    }


    /**
     * 最新发布照片信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/newlist")
    @CrossOrigin
    public Response<List<Zpinfo>> newlist(  HttpServletRequest request) throws Exception {
        PageBean page = new PageBean(0,4);
        //获取当前页记录
        List<Zpinfo> zpinfoList = zpinfoService.queryByState(new Zpinfo(), page);
        return Response.success(zpinfoList);
    }

    /**
     * 获取照片详情 和评论
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/get")
    @CrossOrigin
    public Response<Zpinfo> get(@RequestParam Integer id,HttpServletRequest request) throws Exception {

        Zpinfo zpinfo = new Zpinfo();
        zpinfo.setId(id);
        List<Zpinfo> zpinfoList = zpinfoService.queryZpinfoList(zpinfo, null);


        zpinfo = zpinfoList.get(0);

        Plinfo plinfo = new Plinfo();
        plinfo.setZpid(id);
        List<Plinfo> plinfos = plinfoService.queryPlinfoList(plinfo,null);
        zpinfo.setPlist(plinfos);
        return Response.success(zpinfo);
    }


    /**
     * 保存照片信息
     *
     * @param zpinfo
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/add")
    @CrossOrigin
    public Response add(@RequestBody Zpinfo zpinfo, HttpServletRequest request) throws Exception {
        try {
            zpinfo.setCdate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            zpinfoService.insertZpinfo(zpinfo);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 修改照片信息
     *
     * @param zpinfo
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @CrossOrigin
    public Response update(@RequestBody Zpinfo zpinfo, HttpServletRequest request) throws Exception {
        try {
            zpinfoService.updateZpinfo(zpinfo);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }

    /**
     * 删除照片信息
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
            zpinfoService.deleteZpinfo(id);
            Plinfo plinfo = new Plinfo();
            plinfo.setZpid(id);
            List<Plinfo> plinfos = plinfoService.queryPlinfoList(plinfo,null);
            if(plinfos!=null && plinfos.size()>0){
                for (Plinfo pl:plinfos) {
                    plinfoService.deletePlinfo(pl.getId());
                }
            }
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
    @PostMapping("/listUinfoAll")
    @CrossOrigin
    public Response<List<Uinfo>> listUinfoAll(HttpServletRequest request) throws Exception {
        Uinfo uinfo = new Uinfo();
        List<Uinfo> uinfoList = uinfoService.queryUinfoList(uinfo, null);
        request.setAttribute("uinfoList", uinfoList);
        return Response.success(uinfoList);
    }

    /*
     * 获取所有信息
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/listZpqyAll")
    @CrossOrigin
    public Response<List<Zpqy>> listZpqyAll(HttpServletRequest request) throws Exception {
        Zpqy zpqy = new Zpqy();
        List<Zpqy> zpqyList = zpqyService.queryZpqyList(zpqy, null);
        request.setAttribute("zpqyList", zpqyList);
        return Response.success(zpqyList);
    }

    /*
     * 获取所有信息
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/listZptypeAll")
    @CrossOrigin
    public Response<List<Zptype>> listZptypeAll(HttpServletRequest request) throws Exception {
        Zptype zptype = new Zptype();
        List<Zptype> zptypeList = zptypeService.queryZptypeList(zptype, null);
        request.setAttribute("zptypeList", zptypeList);
        return Response.success(zptypeList);
    }

    /*
     * 获取所有信息
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/listZpxsAll")
    @CrossOrigin
    public Response<List<Zpxs>> listZpxsAll(HttpServletRequest request) throws Exception {
        Zpxs zpxs = new Zpxs();
        List<Zpxs> zpxsList = zpxsService.queryZpxsList(zpxs, null);
        request.setAttribute("zpxsList", zpxsList);
        return Response.success(zpxsList);
    }

    /**
     * 审核作品信息
     *
     * @param zpinfo
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/addCollection")
    @CrossOrigin
    public Response addCollection(@RequestBody ZpinfoCollection zpinfoCollection, HttpServletRequest request) throws Exception {
        try {
            zpinfoService.addCollection(zpinfoCollection);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }
}
