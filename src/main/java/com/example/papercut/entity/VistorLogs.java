package com.example.papercut.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("访客记录类")
public class VistorLogs {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "记录id", required = true)
    private int id;

    @TableField(value = "ip_dir")
    @ApiModelProperty(value = "ip地址", required = true)
    private String ipDir;

    @TableField(value = "status")
    @ApiModelProperty(value = "登陆状态", required = true)
    private String status;

    public VistorLogs(int id, String ipDir, String status) {
        this.id = id;
        this.ipDir = ipDir;
        this.status = status;
    }
    public VistorLogs(String ipDir, String status) {
        this.ipDir = ipDir;
        this.status = status;
    }
    public VistorLogs() {
    }
}
