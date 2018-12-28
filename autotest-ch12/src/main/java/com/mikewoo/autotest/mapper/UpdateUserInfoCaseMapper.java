package com.mikewoo.autotest.mapper;

import com.mikewoo.autotest.domain.UpdateUserInfoCase;
import com.mikewoo.autotest.domain.UpdateUserInfoCaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpdateUserInfoCaseMapper {
    long countByExample(UpdateUserInfoCaseExample example);

    int deleteByExample(UpdateUserInfoCaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UpdateUserInfoCase record);

    int insertSelective(UpdateUserInfoCase record);

    List<UpdateUserInfoCase> selectByExample(UpdateUserInfoCaseExample example);

    UpdateUserInfoCase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UpdateUserInfoCase record, @Param("example") UpdateUserInfoCaseExample example);

    int updateByExample(@Param("record") UpdateUserInfoCase record, @Param("example") UpdateUserInfoCaseExample example);

    int updateByPrimaryKeySelective(UpdateUserInfoCase record);

    int updateByPrimaryKey(UpdateUserInfoCase record);
}