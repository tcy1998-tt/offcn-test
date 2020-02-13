package com.offcn.controller;

import com.offcn.pojo.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/userManage")
public class UserController {
    @Autowired
    private UserService userService;
    //展示用户信息
    @RequestMapping("/")
    public String getUserList(Model model){
        List<User> userList = userService.getUserList();
        model.addAttribute("page",userList);
        return "list";
    }
    //跳转到添加页面
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "userAdd";
    }
    //添加用户
    @PostMapping("/add")
    public String addUser(User user){
        userService.createUser(user);
        //获取列表数据并显示
        return "redirect:/userManage/";
    }
    //跳转到编辑页面
    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model, @PathVariable("id")Long id){
        //查找该用户
        User user = userService.getUser(id);
        //将该用户的信息放到model里
        model.addAttribute("user",user);
        //跳转到编辑页面
        return "userEdit";
    }
    //编辑用户
    @RequestMapping("/edit")
    public String edit(User user){
        userService.updateUser(user.getId(),user);
        //获取列表数据并显示
        return "redirect:/userManage/";
    }
    //根据id删除指定用户
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        //获取列表数据并显示
        return "redirect:/userManage/";
    }
}
