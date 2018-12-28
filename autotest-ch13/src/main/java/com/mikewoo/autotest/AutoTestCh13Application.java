package com.mikewoo.autotest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Eric Gui
 * @date 2018/12/27
 */
@SpringBootApplication
@MapperScan(basePackages = "com.mikewoo.autotest.mapper")
public class AutoTestCh13Application {

    public static void main(String[] args) {
        SpringApplication.run(AutoTestCh13Application.class);
    }
}
