package com.li.blog.user.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author nine
 * @since 2023-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @TableField("nickname")
    private String nickname;

      @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;


}
