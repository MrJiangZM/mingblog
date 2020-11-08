package com.ming.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.websocket.Session;
import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2019/11/26 3:16 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    /**
     * 能够唯一区分当前人的标识 id或userName
     */
    private String userName;

    private Session session;

}
