package com.oyzc.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oyzc.boot.bean.User;
import com.oyzc.boot.mapper.UserMapper;
import com.oyzc.boot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {}
