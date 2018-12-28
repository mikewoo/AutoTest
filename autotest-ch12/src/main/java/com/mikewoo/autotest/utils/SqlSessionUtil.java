package com.mikewoo.autotest.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

/**
 * @author Eric Gui
 * @date 2018/12/27
 */
public class SqlSessionUtil {

    public static SqlSession getSqlSession() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = sessionFactory.openSession();

        return sqlSession;
    }

}
