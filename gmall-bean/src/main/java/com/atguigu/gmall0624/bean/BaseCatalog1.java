package com.atguigu.gmall0624.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @BelongProjecet: gmall0624
 * @BelongPackage: com.atguigu.gmall0624.bean
 * @ClassName: BaseCatalog1
 * @Description: TODO 一级分类实体类--BaseCatalog1
 * @Copyright: 2019-xxx-Powered by 研发四部
 * @Author: LinHong
 * @CreateDate: 2019/12/5 10:47
 * @Version: V1.0
 */
@Data
public class BaseCatalog1  implements Serializable{
    @Id
    @Column
    private String id;
    @Column
    private String name;
}
