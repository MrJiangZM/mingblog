package com.ming.blog.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Data {

    PRIMARY_DATASOURCE("primaryDataSource"),
    SECONDARY_DATASOURCE("secondaryDataSource");

    private String value;

}
