package com.example.papercut.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel("通知")
@AllArgsConstructor
@NoArgsConstructor
public class NoticeEntity {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private int id;
    @TableField(value = "title")
    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @TableField(value = "context")
    @ApiModelProperty(value = "内容", required = true)
    private String context;

    @TableField(value = "status")
    @ApiModelProperty(value = "状态", required = true)
    private String status;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;

    @TableField(value = "update_user")
    @ApiModelProperty(value = "更新用户", required = true)
    private int updateUser;
}
