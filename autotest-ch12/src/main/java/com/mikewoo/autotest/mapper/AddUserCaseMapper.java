package com.mikewoo.autotest.mapper;

import com.mikewoo.autotest.domain.AddUserCase;
import com.mikewoo.autotest.domain.AddUserCaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddUserCaseMapper {
    long countByExample(AddUserCaseExample example);

    int deleteByExample(AddUserCaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AddUserCase record);

    int insertSelective(AddUserCase record);

    List<AddUserCase> selectByExample(AddUserCaseExample example);

    AddUserCase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AddUserCase record, @Param("example") AddUserCaseExample example);

    int updateByExample(@Param("record") AddUserCase record, @Param("example") AddUserCaseExample example);

    int updateByPrimaryKeySelective(AddUserCase record);

    int updateByPrimaryKey(AddUserCase record);
}