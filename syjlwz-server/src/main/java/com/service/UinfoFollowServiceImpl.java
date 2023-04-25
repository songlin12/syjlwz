package com.service;

import com.mapper.UinfoFollowMapper;
import com.model.UinfoFollow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UinfoFollowServiceImpl implements UinfoFollowService{

    @Resource
    private UinfoFollowMapper uinfoFollowMapper;


    @Override
    public int addOrRemoveUinfoFollow(UinfoFollow uinfoFollow) {

        UinfoFollow uinfoFollow1 = uinfoFollowMapper.selectByUid(uinfoFollow.getUid(),uinfoFollow.getFollowid());

        if (uinfoFollow1==null){
            uinfoFollowMapper.insert(uinfoFollow);
        }else {
            uinfoFollowMapper.deleteByPrimaryKey(uinfoFollow1.getId());
        }
        return 1;
    }
}
