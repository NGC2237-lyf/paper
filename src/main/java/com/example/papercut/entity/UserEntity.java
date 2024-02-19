package com.example.papercut.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@ApiModel("用户实体类")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户id")
    private int id;

    @TableField(value = "nick_name")
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

    @TableField(value = "user_name")
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "login_time")
    @ApiModelProperty(value = "登陆时间", required = true)
    private Date loginTime;

    @TableField(value = "avactor")
    @ApiModelProperty(value = "头像", required = false)
    private String avactor;

    @TableField(value = "password")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @TableField(value = "status")
    @ApiModelProperty(value = "状态", required = true)
    private String status;

    @TableField(value = "ip_dir")
    @ApiModelProperty(value = "ip地址", required = true)
    private String ipDir;

    @TableField(value = "role")
    @ApiModelProperty(value = "角色", required = true)
    private int role;
}
