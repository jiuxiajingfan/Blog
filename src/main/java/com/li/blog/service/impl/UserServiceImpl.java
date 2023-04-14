package com.li.blog.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.li.blog.bean.JWTToken;
import com.li.blog.bean.R;
import com.li.blog.entity.dto.LoginDTO;
import com.li.blog.entity.po.User;
import com.li.blog.entity.vo.UserVo;
import com.li.blog.mapper.UserMapper;
import com.li.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.blog.util.JwtUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private UserMapper userMapper;

    @Resource
    private JwtUtils jwtUtils;
    @Override
    public User getUser(String username) {
        return userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getName,username));
    }

    @Override
    public R<UserVo> getMessage() {
        List<User> users = userMapper.selectList(Wrappers.lambdaQuery(User.class).isNotNull(User::getName));
        return R.ok(CglibUtil.copy(users.get(0),UserVo.class));
    }

    @Override
    public R<String> login(LoginDTO loginDTO) {
        User userBean = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getName, loginDTO.getName()));
        if (null == userBean) {
            return R.error("不存在该用户！");
        }
        if (userBean.getPassword().equals(loginDTO.getPassword())) {
            //封装用户的登录数据
            JWTToken jwtToken = new JWTToken(jwtUtils.generateToken(loginDTO.getName(),"UserRealm"));
            return R.ok(jwtToken.getPrincipal().toString());
        }
        return R.error("密码错误！请重试！");
    }

}
