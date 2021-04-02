package com.ming.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
public class OrderDO {

    private Long id;

    private Long userId;

    private BigDecimal totalPrice;

    private String status;

    private Integer src;

    private String testEnum;

    private LocalDate date;

    private String str2Big;

    private BigDecimal big2Str;
    private BigDecimal bigBig;

}
