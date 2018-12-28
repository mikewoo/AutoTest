package com.mikewoo.autotest.mapper;

import com.mikewoo.autotest.domain.LoginCase;
import com.mikewoo.autotest.domain.LoginCaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginCaseMapper {
    long countByExample(LoginCaseExample example);

    int deleteByExample(LoginCaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LoginCase record);

    int insertSelective(LoginCase record);

    List<LoginCase> selectByExample(LoginCaseExample example);

    LoginCase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LoginCase record, @Param("example") LoginCaseExample example);

    int updateByExample(@Param("record") LoginCase record, @Param("example") LoginCaseExample example);

    int updateByPrimaryKeySelective(LoginCase record);

    int updateByPrimaryKey(LoginCase record);
}