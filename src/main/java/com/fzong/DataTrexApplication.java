package com.fzong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
public class DataTrexApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataTrexApplication.class,args);
        System.out.println("启动成功");
    }
}
