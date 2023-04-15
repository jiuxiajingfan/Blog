package com.li.blog.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.blog.bean.JWTToken;
import com.li.blog.bean.R;
import com.li.blog.entity.dto.ChangeMessageDTO;
import com.li.blog.entity.dto.ChangePicDTO;
import com.li.blog.entity.dto.ChangePwdDTO;
import com.li.blog.entity.dto.LoginDTO;
import com.li.blog.entity.po.User;
import com.li.blog.entity.vo.UserVo;
import com.li.blog.mapper.UserMapper;
import com.li.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.blog.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    @SneakyThrows
    public R<UserVo> getMessage() {
        List<User> users = userMapper.selectList(Wrappers.lambdaQuery(User.class).isNotNull(User::getName));
        UserVo copy = CglibUtil.copy(users.get(0), UserVo.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List list = objectMapper.readValue(users.get(0).getBackground(), List.class);
        copy.setBackList(list);
        return R.ok(copy);
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

    @Override
    public R<String> changePwd(ChangePwdDTO changePwdDto) {
        Subject subject = SecurityUtils.getSubject();
        Claims claimByToken = jwtUtils.getClaimByToken(subject.getPrincipal().toString());
        User userBean = userMapper.selectOne(Wrappers.lambdaQuery(User.class)
                .eq(User::getName, claimByToken.getSubject()));
        if (userBean == null)
            return R.error("不存在该用户！");
        if (userBean.getPassword().equals(changePwdDto.getPwdOriginal())) {
            if (changePwdDto.getPwdNew().equals(changePwdDto.getPwdConfirm())) {
                return userMapper.update(null, Wrappers.lambdaUpdate(User.class)
                        .eq(User::getId, userBean.getId())
                        .set(User::getPassword, changePwdDto.getPwdNew()))==1?R.ok("修改成功！请重新登录！"):R.error("修改失败，请联系管理员！");
            } else {
                return R.error("两次密码不一致，请重新确认！");
            }
        }
        return R.error("原密码错误！请重试！");
    }

    @SneakyThrows
    @Override
    public R<String> changePic(ChangePicDTO changePicDTO) {
        List<String> collect = changePicDTO.getBack().stream().filter(e -> {
            return !StringUtils.isBlank(e);
        }).collect(Collectors.toList());
        JwtUtils jwtUtils = new JwtUtils();
        Subject subject = SecurityUtils.getSubject();
        Claims claimByToken = jwtUtils.getClaimByToken(subject.getPrincipal().toString());
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getName, claimByToken.getSubject()));
        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper.writeValueAsString(collect);
        user.setBackground(data);
        user.setImgurl(changePicDTO.getPic());
        int i = userMapper.updateById(user);
        if (i == 1) {
            return R.ok("更新成功");
        }else{
            return R.error("更新失败");
        }
    }

    public R<String> changeMessage(ChangeMessageDTO changeMessageDTO){
        JwtUtils jwtUtils = new JwtUtils();
        Subject subject = SecurityUtils.getSubject();
        Claims claimByToken = jwtUtils.getClaimByToken(subject.getPrincipal().toString());
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getName, claimByToken.getSubject()));
        user.setTitle(changeMessageDTO.getTitle());
        user.setTitle2(changeMessageDTO.getTitle2());
        user.setGithub(changeMessageDTO.getGithub());
        user.setEmail(changeMessageDTO.getEmail());
        user.setRecord(changeMessageDTO.getRecord());
        int i = userMapper.updateById(user);
        if (i == 1) {
            return R.ok("更新成功");
        }else{
            return R.error("更新失败");
        }
    }

}
