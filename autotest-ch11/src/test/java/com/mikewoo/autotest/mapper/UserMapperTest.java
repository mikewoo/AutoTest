package com.mikewoo.autotest.mapper;

import com.mikewoo.autotest.domian.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Eric Gui
 * @date 2018/12/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUserCount() {
        Long count = userMapper.getUserCount();
        System.out.println(count);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("123456");
        user.setNikeName("lisi123");
        user.setUserSex(1);
        Long id = userMapper.insert(user);
        System.out.println(id);
        System.out.println(user.getId());
    }
}
