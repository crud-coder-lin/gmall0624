package com.atguigu.gmall0624.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @BelongProjecet: gmall0624
 * @BelongPackage: com.atguigu.gmall0624.bean
 * @ClassName: UserInfo
 * @Description: TODO dubbo的实体类需要序列化（二进制传输）
 * @Copyright: 2019-xxx-Powered by 研发四部
 * @Author: LinHong
 * @CreateDate: 2019/12/4 18:34
 * @Version: V1.0
 */
@Data
public class UserInfo implements Serializable{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) //获取主键自增
    private String id;
    @Column
    private String loginName;
    @Column
    private String nickName;
    @Column
    private String passwd;
    @Column
    private String name;
    @Column
    private String phoneNum;
    @Column
    private String email;
    @Column
    private String headImg;
    @Column
    private String userLevel;
}
