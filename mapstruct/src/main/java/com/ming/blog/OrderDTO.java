package com.ming.blog;

import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;

/**
 * @author MrJiangZM
 * @date 2021/4/2 16:08
 */
@ToString
@Data
public class OrderDTO extends OrderDO {

    private String testName;

    private StatusParamEnum testEnumDTO;

    private String dateStr;

    private BigDecimal str2BigDTO;

    private String big2StrDTO;

    private BigDecimal bigBigTTT;

    private String testTwoObj;

}
