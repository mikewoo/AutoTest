package com.mikewoo.autotest.mapper;

import com.mikewoo.autotest.domain.GetUserListCase;
import com.mikewoo.autotest.domain.GetUserListCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GetUserListCaseMapper {
    long countByExample(GetUserListCaseExample example);

    int deleteByExample(GetUserListCaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GetUserListCase record);

    int insertSelective(GetUserListCase record);

    List<GetUserListCase> selectByExample(GetUserListCaseExample example);

    GetUserListCase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GetUserListCase record, @Param("example") GetUserListCaseExample example);

    int updateByExample(@Param("record") GetUserListCase record, @Param("example") GetUserListCaseExample example);

    int updateByPrimaryKeySelective(GetUserListCase record);

    int updateByPrimaryKey(GetUserListCase record);
}