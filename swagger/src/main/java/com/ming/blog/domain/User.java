package com.ming.blog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2019/11/25 5:36 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户的实体类")
public class User<T> implements Serializable {

    @ApiModelProperty(value = "用户id", name = "userId", dataType = "int", example = "100")
    private Integer userId;
    @ApiModelProperty(value = "名称", name = "name", dataType = "string", example = "小明")
    private String name;
    @ApiModelProperty(value = "内容", name = "data", dataType = "t")
    private T data;


}
