package com.atguigu.gmall0624.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @BelongProjecet: gmall0624
 * @BelongPackage: com.atguigu.gmall0624.bean
 * @ClassName: SpuInfo
 * @Description: TODO 商品属性（商品表）--实体类
 * @Copyright: 2019-xxx-Powered by 研发四部
 * @Author: LinHong
 * @CreateDate: 2019/12/6 20:11
 * @Version: V1.0
 */
@Data
public class SpuInfo implements Serializable{
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String spuName;

    @Column
    private String description;

    @Column
    private  String catalog3Id;
    // 根据业务需要添加对应的字段

    @Transient
    private List<SpuSaleAttr> spuSaleAttrList;
    // 页面图片

    @Transient
    private List<SpuImage> spuImageList;
}
