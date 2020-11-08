package com.ming.blog.domain.es;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor



@Slf4j
@Document(indexName = "person", type = "docs")
public class Person implements Serializable {

    @Id
    private Integer id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Date)
    private Date date;

    @Field(type = FieldType.Keyword, index = false)
    private String url;

    @Field(type = FieldType.Double)
    private Double price;

}
