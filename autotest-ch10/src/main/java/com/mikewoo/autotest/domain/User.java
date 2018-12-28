package com.mikewoo.autotest.domain;

import lombok.Data;

/**
 * @author Eric Gui
 * @date 2018/12/25
 */
@Data
public class User {

    private String username;

    private String password;

    private String name;

    private Integer age;

    private String sex;
}
