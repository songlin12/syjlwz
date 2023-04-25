package com.controller;

import com.model.Uinfo;
import com.model.UinfoFollow;
import com.response.Response;
import com.service.UinfoFollowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/uinfoFollow")
public class UinfoFollowController {

    @Resource
    private UinfoFollowService uinfoFollowService;
    /**
     * 保存用户信息
     *
     * @param uinfoFollow
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/addOrRemove")
    @CrossOrigin
    public Response addOrRemove(@RequestBody UinfoFollow uinfoFollow, HttpServletRequest request) throws Exception {
        try {
            uinfoFollowService.addOrRemoveUinfoFollow(uinfoFollow);
        } catch (Exception e) {
            return Response.error();
        }
        return Response.success();
    }
}
