package com.li.blog.service;

import com.li.blog.bean.R;
import com.li.blog.entity.dto.LoginDTO;
import com.li.blog.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.li.blog.entity.vo.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nine
 * @since 2023-04-12
 */
public interface UserService extends IService<User> {

    User getUser(String username);

    R<UserVo> getMessage();

    R<String> login(LoginDTO loginDTO);
}
