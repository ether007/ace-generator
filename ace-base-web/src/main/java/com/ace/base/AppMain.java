package com.ace.base;

import com.ace.base.service.BaseService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.ace.base.dao"})
public class AppMain implements CommandLineRunner{

    public static void main(String[] args) {

        SpringApplication.run(AppMain.class, args);
    }

    @Autowired
    private BaseService baseService;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("run");
        long count = baseService.countByByExample(null);
        System.out.println(count);
    }
}
