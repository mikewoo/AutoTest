package com.mikewoo.autotest.domian;

import lombok.Data;

/**
 * @author Eric Gui
 * @date 2018/12/25
 */
@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String nikeName;

    private Integer userSex;
}
