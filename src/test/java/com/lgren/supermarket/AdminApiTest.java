package com.lgren.supermarket;

import com.lgren.pojo.po.Admin;
import com.lgren.service.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApiTest {
    private Admin admin;
    @Autowired
    private AdminService adminService;

    @Before
    public void init() {
        admin = adminService.selectByPrimaryKey(10L);
    }

    @Test
    public void getPOorVO() {
        System.out.println(admin);
        admin = null;
    }

    @Test
    public void getPOorVO1() {
    }

    @Test
    public void getPOorDTO() {
    }

    @Test
    public void getPOorDTO1() {
    }
}