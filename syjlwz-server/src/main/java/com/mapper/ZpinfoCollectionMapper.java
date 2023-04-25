package com.mapper;

import com.model.ZpinfoCollection;
import org.apache.ibatis.annotations.Param;

/**
* @author hsltu
* @description 针对表【zpinfo_collection】的数据库操作Mapper
* @createDate 2023-04-25 15:22:12
* @Entity com.model.ZpinfoCollection
*/
public interface ZpinfoCollectionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ZpinfoCollection record);

    int insertSelective(ZpinfoCollection record);

    ZpinfoCollection selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZpinfoCollection record);

    int updateByPrimaryKey(ZpinfoCollection record);

    ZpinfoCollection selectByUidAndZpid(@Param("uid") Integer uid, @Param("zpid")Integer zpid);

    void deleteByUidAndZpid(@Param("uid") Integer uid, @Param("zpid") Integer zpid);
}
