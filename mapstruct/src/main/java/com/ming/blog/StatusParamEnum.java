package com.ming.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Jiang Zaiming
 * @date 2021/4/2 16:37
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusParamEnum {

    YES(1, "YES"),
    NO(2, "NO"),
    HH(3, "HH");

    private Integer num;
    private String code;

    public static StatusParamEnum getByNum(Integer num) {
        StatusParamEnum[] values = StatusParamEnum.values();
        for (StatusParamEnum statusParamEnum : values) {
            if (statusParamEnum.getNum().equals(num)) {
                return statusParamEnum;
            }
        }
        return null;
    }

    public static StatusParamEnum getByCode(String code) {
        StatusParamEnum[] values = StatusParamEnum.values();
        for (StatusParamEnum statusParamEnum : values) {
            if (statusParamEnum.getCode().equals(code)) {
                return statusParamEnum;
            }
        }
        return null;
    }

}
