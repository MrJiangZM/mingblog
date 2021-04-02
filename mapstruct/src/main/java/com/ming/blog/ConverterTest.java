package com.ming.blog;

import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Maps;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author MrJiangZM
 * @date 2021/4/2 15:51
 */
public class ConverterTest {

    public static void main(String[] args) {
//        one2One();
//        many2Many();
//        copyProperties();
//        dto2do();
//        mapToMap();
//        objToMap();
//        map2Obj();
    }

    public static void one2One() {
        OrderDO orderDO = new OrderDO();
        orderDO.setStatus("YES");
        orderDO.setDate(LocalDate.now());
        orderDO.setTestEnum("TT");
        orderDO.setBig2Str(BigDecimal.valueOf(214231234.47846587987646));
        orderDO.setStr2Big("485173298172384798.11111111111");
        orderDO.setBigBig(BigDecimal.valueOf(214231234.47846587987646));
        orderDO.setTotalPrice(BigDecimal.valueOf(1.25));
        orderDO.setId(123L);

        OrderDTO orderDTO = OrderConverter.INSTACNE.do2DTO(orderDO);

//        OrderNumDO numDO = new OrderNumDO();
//        numDO.setTestNumDOStr("哈哈哈");
//        OrderDTO orderDTO = OrderConverter.INSTACNE.do2DTOFromTwo(orderDO, numDO);

        System.out.println(orderDTO);
        System.out.println(orderDO);
    }

    public static void dto2do() {
        OrderDO orderDO = new OrderDO();
        orderDO.setStatus("YES");
        orderDO.setDate(LocalDate.now());
        orderDO.setTestEnum("TT");
        orderDO.setBig2Str(BigDecimal.valueOf(214231234.47846587987646));
        orderDO.setStr2Big("485173298172384798.11111111111");
        orderDO.setBigBig(BigDecimal.valueOf(214231234.47846587987646));
        orderDO.setTotalPrice(BigDecimal.valueOf(1.25));
        orderDO.setId(123L);

        OrderDTO orderDTO = OrderConverter.INSTACNE.do2DTO(orderDO);

        System.out.println(orderDTO);
        OrderDO orderDO1 = OrderConverter.INSTACNE.dto2do(orderDTO);
        System.out.println(orderDO1);
    }

    public static void mapToMap() {
        Map<Long, Date> map = Maps.newHashMap();
        map.put(1L, new Date());
        map.put(2L, new Date());
        map.put(3L, new Date());
        Map<Integer, String> stringStringMap = OrderConverter.INSTACNE.longDateMapToStringStringMap(map);
        System.out.println(map);
        System.out.println(stringStringMap);
    }

    /**
     * map 和 object 的互相转化是有问题的，编译时期，压根没有进行类型强转，所以会遗留大坑
     * @param code
     * @return
     */
    public static void objToMap() {
        OrderDO orderDO = new OrderDO();
        orderDO.setStatus("YES");
        orderDO.setDate(LocalDate.now());
        orderDO.setTestEnum("TT");
        orderDO.setBig2Str(BigDecimal.valueOf(214231234.47846587987646));
        orderDO.setStr2Big("485173298172384798.11111111111");
        orderDO.setBigBig(BigDecimal.valueOf(214231234.47846587987646));
        orderDO.setTotalPrice(BigDecimal.valueOf(1.25));
        orderDO.setId(123L);

//        Map<String, String> stringStringMap = OrderConverter.INSTACNE.obj2Map(orderDO);
//        System.out.println(orderDO);
//        System.out.println(stringStringMap);
    }

    /**
     * map 和 object 的互相转化是有问题的，编译时期，压根没有进行类型强转，所以会遗留大坑
     * @param code
     * @return
     */
    public static void map2Obj() {
        Map<String, Object> objectObjectMap = Maps.newHashMap();
        objectObjectMap.put("id", 1);
        objectObjectMap.put("totalPrice", new BigDecimal("1.02"));
        objectObjectMap.put("status", "haha");
        OrderDO orderDO = OrderConverter.INSTACNE.map2Obj(objectObjectMap);
        System.out.println(objectObjectMap);
        System.out.println(orderDO);
    }

    public static void copyProperties() {
        OrderDO orderDO = new OrderDO();
        orderDO.setStatus("YES");
        orderDO.setDate(LocalDate.now());
        orderDO.setTestEnum("TT");
        orderDO.setBig2Str(BigDecimal.valueOf(214231234.47846587987646));
        orderDO.setStr2Big("485173298172384798.11111111111");
        orderDO.setBigBig(BigDecimal.valueOf(214231234.47846587987646));
        orderDO.setTotalPrice(BigDecimal.valueOf(1.25));
        orderDO.setId(123L);

        OrderNumCopyDO orderNumDO = new OrderNumCopyDO();
        OrderConverter.INSTACNE.copyProperties(orderDO, orderNumDO);
        System.out.println(orderNumDO);
        System.out.println(orderDO);
    }

    public static void many2Many() {
        OrderDO orderDO = new OrderDO();
        orderDO.setStatus("YES");
        orderDO.setDate(LocalDate.now());
        orderDO.setTestEnum("TT");
        orderDO.setBig2Str(BigDecimal.valueOf(214231234.47846587987646));
        orderDO.setStr2Big("485173298172384798.11111111111");
        orderDO.setBigBig(BigDecimal.valueOf(214231234.47846587987646));
        orderDO.setTotalPrice(BigDecimal.valueOf(1.25));
        orderDO.setId(123L);

        OrderDO orderDO2 = new OrderDO();
        orderDO2.setStatus("YES");
        orderDO2.setDate(LocalDate.now());
        orderDO2.setTestEnum("TT");
        orderDO2.setBig2Str(BigDecimal.valueOf(214231234.47846587987646));
        orderDO2.setStr2Big("485173298172384798.11111111111");
        orderDO2.setBigBig(BigDecimal.valueOf(214231234.47846587987646));
        orderDO2.setTotalPrice(BigDecimal.valueOf(1.25));
        orderDO2.setId(123L);

        List<OrderDO> orderDOS = Lists.newArrayList(orderDO, orderDO2);
        List<OrderDTO> orderDTOList = OrderConverter.INSTACNE.do2DTOList(orderDOS);
        System.out.println(orderDTOList);
        System.out.println(orderDOS);
    }

}
