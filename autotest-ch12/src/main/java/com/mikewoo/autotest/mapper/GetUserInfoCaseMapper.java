package com.mikewoo.autotest.mapper;

import com.mikewoo.autotest.domain.GetUserInfoCase;
import com.mikewoo.autotest.domain.GetUserInfoCaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GetUserInfoCaseMapper {
    long countByExample(GetUserInfoCaseExample example);

    int deleteByExample(GetUserInfoCaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GetUserInfoCase record);

    int insertSelective(GetUserInfoCase record);

    List<GetUserInfoCase> selectByExample(GetUserInfoCaseExample example);

    GetUserInfoCase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GetUserInfoCase record, @Param("example") GetUserInfoCaseExample example);

    int updateByExample(@Param("record") GetUserInfoCase record, @Param("example") GetUserInfoCaseExample example);

    int updateByPrimaryKeySelective(GetUserInfoCase record);

    int updateByPrimaryKey(GetUserInfoCase record);
}