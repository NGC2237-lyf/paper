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
@ApiModel("剪纸实体类")
@AllArgsConstructor
@NoArgsConstructor
public class PaperImgEntity {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "剪纸id", required = true)
    int id;

    @TableField(value = "img")
    @ApiModelProperty(value = "剪纸图片", required = true)
    String img;

    @TableField(value = "detail_text")
    @ApiModelProperty(value = "展品详情", required = true)
    String detailText;

    @TableField(value = "paper_title")
    @ApiModelProperty(value = "展品标题", required = true)
    String paperTitle;

    @TableField(value = "paper_kind")
    @ApiModelProperty(value = "展品类别", required = true)
    String paperKind;

    @TableField(value = "paper_num")
    @ApiModelProperty(value = "展品数量", required = true)
    String paperNum;
}
