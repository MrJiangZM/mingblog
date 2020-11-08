//package com.ming.blog.domain;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.List;
//
///**
// * @author Jiang Zaiming
// * @date 2020/4/3 5:31 下午
// */
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "sys_dept")
//public class SysDept implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long deptId;
//    /**
//     * 上级部门ID，一级部门为0
//     */
//    @JsonFormat(shape=JsonFormat.Shape.STRING)
//    private Long parentId;
//    /**
//     * 部门名称
//     */
//    private String name;
//    private String orderNum;
//    private Integer delFlag;
//
//    private Long deptTypeId;
//    private Long districtCode;
//    private String address;
//    private String mobile;
//    private String brief;
//    private Integer level;
//
//}
