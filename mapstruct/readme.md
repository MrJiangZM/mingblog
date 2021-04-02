# mapstruct

## POJO DTO VO DO 相关转化

## 坑！！！！

- maven-compiler-plugin 版本必须3.6.0以上

- 和lombok不兼容
---
    需要在pom.xml中配置对应的lombok 和 mapstruct的plugins
    
    <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.1</version>
                    <configuration>
                        <source>1.8</source> <!-- depending on your project -->
                        <target>1.8</target> <!-- depending on your project -->
                        <annotationProcessorPaths>
                            <path>
                                <groupId>org.projectlombok</groupId>
                                <artifactId>lombok</artifactId>
                                <version>1.18.8</version>
                            </path>
                            <path>
                                <groupId>org.mapstruct</groupId>
                                <artifactId>mapstruct-processor</artifactId>
                                <version>1.4.2.Final</version>
                            </path>
                            <!-- other annotation processors -->
                        </annotationProcessorPaths>
                    </configuration>
    
                </plugin>
            </plugins>
        </build>

---
- 不同名属性，通过@Mapping指定

- 枚举相关
---
    - 枚举不带参数，那么相同，可以直接映射，即使是不同枚举
    - 枚举和String字符串可以直接映射，只要name相同，要求枚举没有属性
    - 带属性枚举必须单独处理，可以通过default一个方法来进行映射
    - **大坑**  
        BasicOrderDTO do2DTO(BasicOrderDO aaa)   
        @Mapping(target = "testEnumDTO",expression = "java(getEnumByCode(aaa.getTestEnum()))")  
        @Mapping(target = "repairAmount", expression = "java(com.souche.connector.common.util.AmountConvertUtil.convertToFen(vo.getRepairAmount()))"),
        两个aaa 必须保持一致，因为是编译期的应用不一致会出问题。
    - 时间类型，dateFormat
    - bigdecimal 和  string 转化 
        bigdecimal转string可以格式化，string转bigDecimal和bigDecimal转bigDecimal没有能正常格式化
    - 多个对象的属性映射，指明用了哪个对象就可以
    - 可以把一个对象的同名属性复制到另一个对象中
    - do2DTO DTO2DO   不用多写，但是要注意时间类型以及使用了default方法的地方，没有办法转化回去
        @InheritInverseConfiguration(name = "carToCarDto")
        Car carDTOTocar(CarDto carDto);
    - 属性嵌套映射的使用，
        @Mapping(target = "quality.report.organisation.name", source = "quality.report.organisationName")
    - 默认值和常量值 constant defaultValue
    - 集合的映射   对象中不能包含更加复杂的属性映射
        Set<String> integerSetToStringSet(Set<Integer> integers);
        List<CarDto> carsToCarDtos(List<Car> cars);
    - map映射的使用
        @MapMapping(valueDateFormat = "dd.MM.yyyy")
        Map<String, String> longDateMapToStringStringMap(Map<Long, Date> source);
---