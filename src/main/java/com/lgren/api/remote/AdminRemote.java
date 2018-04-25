package com.lgren.api.remote;

import com.lgren.pojo.po.Admin;
import com.lgren.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class AdminRemote {
    @Autowired
    private AdminService adminService;
    //查询所有
    @RequestMapping(value = "/admin.do",method = RequestMethod.GET)
    public List<Admin> getAllAdmin() {
        return adminService.selectAll();
    }
    //根据主键ID查询
    @RequestMapping(value = "/admin.do/{adminId}",method = RequestMethod.GET)
    public Admin getAdminById(@PathVariable("adminId") Long adminId) {
        return adminService.selectByPrimaryKey(adminId);
    }
    //添加
    @RequestMapping(value = "/admin.do",method = RequestMethod.POST)
    public boolean addAdmin(Admin admin) {
        return adminService.insertSelective(admin) > 0 ? true : false;
    }
    //通过主键ID删除
    @RequestMapping(value = "/admin.do/{adminId}",method = RequestMethod.DELETE)
    public boolean deleteAdminById(@PathVariable("adminId") Long adminId) {
        return adminService.deleteByPrimaryKey(adminId) > 0 ? true : false;
    }
    //通过传输一个Admin对象修改
    @RequestMapping(value = "/admin.do",method = RequestMethod.PUT)
    public boolean updateAdminById(Admin admin) {
        return adminService.updateByPrimaryKeySelective(admin) > 0 ? true : false;
    }



}
