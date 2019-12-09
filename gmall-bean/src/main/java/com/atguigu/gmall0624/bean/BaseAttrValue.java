package com.atguigu.gmall0624.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @BelongProjecet: gmall0624
 * @BelongPackage: com.atguigu.gmall0624.bean
 * @ClassName: BaseAttrValue
 * @Description: TODO 平台属性值
 * @Copyright: 2019-xxx-Powered by 研发四部
 * @Author: LinHong
 * @CreateDate: 2019/12/5 10:50
 * @Version: V1.0
 */
@Data
public class BaseAttrValue implements Serializable{
    @Id
    @Column
    private String id;
    @Column
    private String valueName;
    @Column
    private String attrId;

}
