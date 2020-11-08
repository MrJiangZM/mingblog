package com.ming.blog.util;

import lombok.Data;

@Data
public class SessionVo {
    private byte[] data;

    public static SessionVo from(byte[] data) {
        SessionVo vo = new SessionVo();
        vo.setData(data);
        return vo;
    }
}
