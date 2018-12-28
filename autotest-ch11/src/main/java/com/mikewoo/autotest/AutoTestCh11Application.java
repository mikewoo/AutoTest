package com.mikewoo.autotest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Eric Gui
 * @date 2018/12/25
 */
@SpringBootApplication
@MapperScan(basePackages = "com.mikewoo.autotest.mapper")
public class AutoTestCh11Application {

    public static void main(String[] args) {
        SpringApplication.run(AutoTestCh11Application.class, args);
    }
}
