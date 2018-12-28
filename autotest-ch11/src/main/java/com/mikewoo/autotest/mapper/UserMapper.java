package com.mikewoo.autotest.mapper;

import com.mikewoo.autotest.domian.User;

/**
 * @author Eric Gui
 * @date 2018/12/25
 */
public interface UserMapper {

    Long getUserCount();

    Long insert(User user);
}
