package com.li.blog.user.service.impl;

import com.li.blog.user.entity.po.User;
import com.li.blog.user.mapper.UserMapper;
import com.li.blog.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nine
 * @since 2023-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
