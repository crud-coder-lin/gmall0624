package com.atguigu.gmall0624;

import com.atguigu.gmall0624.bean.*;

import java.util.List;

/**
 * @BelongProjecet: gmall0624
 * @BelongPackage: com.atguigu.gmall0624
 * @ClassName: ManageService
 * @Description: TODO
 * @Copyright: 2019-xxx-Powered by 研发四部
 * @Author: LinHong
 * @CreateDate: 2019/12/5 11:13
 * @Version: V1.0
 */
public interface ManageService {
    /**
     * 功能描述
     * @MethodName: getCatalog1
     * @MethodParam: []
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseCatalog1>
     * @Description: TODO 查询一级分类(接口)
     * @Author: LinHong
     * @CreateDate: 2019/12/5 14:48
     */
    List<BaseCatalog1> getCatalog1();
    /**
     * 功能描述
     * @MethodName: getCatalog2
     * @MethodParam: [catalog1Id]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseCatalog2>
     * @Description: TODO  查询二级分类(接口)
     * @Author: LinHong
     * @CreateDate: 2019/12/5 14:51
     */
    List<BaseCatalog2> getCatalog2(String catalog1Id);
    /**
     * 功能描述
     * @MethodName: getCatalog3
     * @MethodParam: [catalog2Id]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseCatalog3>
     * @Description: TODO 查询三级分类(接口)
     * @Author: LinHong
     * @CreateDate: 2019/12/5 14:52
     */
    List<BaseCatalog3> getCatalog3(String catalog2Id);


    /**
     * 功能描述
     * @MethodName: getAttrInfoList
     * @MethodParam: [catalog3Id]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseAttrInfo>
     * @Description: TODO （选择1、2、3级分类后）查询平台属性（接口）
     *               TODO http://localhost:8082/attrInfoList?catalog3Id=101
     * @Author: LinHong
     * @CreateDate: 2019/12/5 15:29
     */
    List<BaseAttrInfo> getAttrInfoList(String catalog3Id);
    /**
     * 功能描述
     * @MethodName: saveBaseAttrInfo
     * @MethodParam: [baseAttrInfo]
     * @Return: void
     * @Description: TODO 添加平台属性-平台属性值（接口）
     *               TODO 括号中的参数，实际上就是从页面传递过来的数据（传递对象接收前台的数据）
     * @Author: LinHong
     * @CreateDate: 2019/12/5 16:02
     */
    void saveBaseAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     * 功能描述
     * @MethodName: getAttrValueList
     * @MethodParam: [attrId]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseAttrValue>
     * @Description: TODO 修改平台属性值[1]--通过attrId回显平台属性值集合数据(接口)
     * @Author: LinHong
     * @CreateDate: 2019/12/5 17:04
     */

    List<BaseAttrValue> getAttrValueList(String attrId);

    /**
     * 功能描述
     * @MethodName: getBaseAttrInfo
     * @MethodParam: [attrId]
     * @Return: com.atguigu.gmall0624.bean.BaseAttrInfo
     * @Description: TODO 修改平台属性值[2]--通过平台属性attrId回显平台属性对象
     * @Author: LinHong
     * @CreateDate: 2019/12/6 19:02
     */
    BaseAttrInfo getBaseAttrInfo(String attrId);
    /**
     * 功能描述
     * @MethodName: getSpuInfoList
     * @MethodParam: [cataloh3Id]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.SpuInfo>
     * @Description: TODO 根据spuInfo的属性去查询spuInfo列表
     * @Author: LinHong
     * @CreateDate: 2019/12/6 20:24
     */
    List<SpuInfo> getSpuList(String cataloh3Id);

    List<SpuInfo> getSpuList(SpuInfo spuInfo);

    /**
     * 功能描述
     * @MethodName: getBaseSaleAttrList
     * @MethodParam: []
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseSaleAttr>
     * @Description: TODO  查询基本销售属性表
     * @Author: LinHong
     * @CreateDate: 2019/12/9 1:18
     */
    List<BaseSaleAttr> getBaseSaleAttrList();


    void saveSpuInfo(SpuInfo spuInfo);

}
