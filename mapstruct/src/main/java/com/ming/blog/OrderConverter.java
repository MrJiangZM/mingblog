package com.ming.blog;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jiang Zaiming
 * @date 2021/4/2 15:46
 */
@Mapper
public interface OrderConverter {

    OrderConverter INSTACNE = Mappers.getMapper(OrderConverter.class);

    @Mappings({
//            @Mapping(source = "status", target = "testName"),
//            @Mapping(source = "status", target = "testName"),
//            @Mapping(source = "testEnum", target = "testEnumDTO.code"),
            @Mapping(target = "testEnumDTO", expression = "java(getEnumByCode(aaa.getTestEnum()))"),
            @Mapping(target = "testName", expression = "java(setTest(aaa.getStatus()))"),
            @Mapping(source = "aaa.date", target = "dateStr", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "str2Big", target = "str2BigDTO", numberFormat = "#.0000"),
            @Mapping(source = "big2Str", target = "big2StrDTO", numberFormat = "#.000"),
            @Mapping(source = "bigBig", target = "bigBigTTT", numberFormat = "#.000"),
    })
    OrderDTO do2DTO(OrderDO aaa);

    @Mappings({
            @Mapping(target = "testEnumDTO", expression = "java(getEnumByCode(aaa.getTestEnum()))"),
            @Mapping(target = "testName", expression = "java(setTest(aaa.getStatus()))"),
            @Mapping(source = "aaa.date", target = "dateStr", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "str2Big", target = "str2BigDTO", numberFormat = "#.0000"),
            @Mapping(source = "big2Str", target = "big2StrDTO", numberFormat = "#.000"),
            @Mapping(source = "bigBig", target = "bigBigTTT", numberFormat = "#.000"),
    })
    List<OrderDTO> do2DTOList(List<OrderDO> list);

    @Mappings({
//            @Mapping(source = "status", target = "testName"),
//            @Mapping(source = "status", target = "testName"),
//            @Mapping(source = "testEnum", target = "testEnumDTO.code"),
            @Mapping(target = "testEnumDTO", expression = "java(getEnumByCode(aaa.getTestEnum()))"),
            @Mapping(target = "testName", expression = "java(setTest(aaa.getStatus()))"),
            @Mapping(source = "aaa.date", target = "dateStr", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "aaa.str2Big", target = "str2BigDTO", numberFormat = "#.0000"),
            @Mapping(source = "aaa.big2Str", target = "big2StrDTO", numberFormat = "#.000"),
            @Mapping(source = "aaa.bigBig", target = "bigBigTTT", numberFormat = "#.000"),
            @Mapping(source = "bbb.testNumDOStr", target = "testTwoObj"),
    })
    OrderDTO do2DTOFromTwo(OrderDO aaa, OrderNumDO bbb);

    void copyProperties(OrderDO carDto, @MappingTarget OrderNumCopyDO car);

    @InheritInverseConfiguration(name = "do2DTO")
    OrderDO dto2do(OrderDTO carDto);

    @MapMapping(valueDateFormat = "dd.MM.yyyy", keyTargetType = Integer.class)
    Map<Integer, String> longDateMapToStringStringMap(Map<Long, Date> source);

    /**
     * map 和 object 的互相转化是有问题的，编译时期，压根没有进行类型强转，所以会遗留大坑
     * @param code
     * @return
     */
//    @MapMapping(valueDateFormat = "dd.MM.yyyy", keyTargetType = String.class)
//    Map<String, String> obj2Map(OrderDO carDto);

//    @Mappings({@Mapping(expression = "java(map.get(\"id\"))", target = "id"),
//            @Mapping(expression = "java(map.get(\"totalPrice\"))", target = "totalPrice"),
//            @Mapping(expression = "java(map.get(\"status\"))", target = "status")})
//    OrderDO map2Obj(Map<String, Object> map);

    default StatusParamEnum getEnumByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        return StatusParamEnum.getByCode(code);
    }

    default String setTest(String code) {
        return code + "哈哈哈";
    }

}
