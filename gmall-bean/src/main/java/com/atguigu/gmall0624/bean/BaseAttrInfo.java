package com.atguigu.gmall0624.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @BelongProjecet: gmall0624
 * @BelongPackage: com.atguigu.gmall0624.bean
 * @ClassName: BaseAttrInfo
 * @Description: TODO 平台属性
 * @Copyright: 2019-xxx-Powered by 研发四部
 * @Author: LinHong
 * @CreateDate: 2019/12/5 10:50
 * @Version: V1.0
 */
@Data
public class BaseAttrInfo implements Serializable{
    @Id
    @Column
    //mysql获取方式，表示获取主键自增 oracle:GenerationType.Auto

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;

    //在数据库中并没有此字段，根据业务需求添加

    @Transient
    List<BaseAttrValue> attrValueList;

}
