package com.ming.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author MrJiangZM
 * @date 2021/4/2 17:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderNumCopyDO {

    private String testNumDOStr;

    private Integer src;

    private String testEnum;

    private LocalDateTime date;

    private String str2Big;

    private BigDecimal big2Str;

}
