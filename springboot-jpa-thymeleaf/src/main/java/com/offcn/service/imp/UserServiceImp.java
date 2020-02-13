package com.offcn.service.imp;

import com.offcn.dao.UserDao;
import com.offcn.pojo.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> getUserList() {
        return userDao.findAll();
    }

    @Override
    public void createUser(User user) {
        userDao.save(user);
    }

    @Override
    public User getUser(Long id) {
        Optional<User> optional =userDao.findById(id);
        //判断获取到的是否为空 不为空则返回该对象
        if(optional.isPresent()){
            return optional.get();
        }
        //否则返回空
        return null;
    }

    @Override
    public void updateUser(Long id, User user) {
        user.setId(id);
        userDao.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
