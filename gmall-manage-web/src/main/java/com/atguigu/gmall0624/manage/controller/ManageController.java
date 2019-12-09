package com.atguigu.gmall0624.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall0624.ManageService;
import com.atguigu.gmall0624.bean.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongProjecet: gmall0624
 * @BelongPackage: com.atguigu.gmall0624.manage.controller
 * @ClassName: ManageController
 * @Description: TODO 注意跨域请求处理【403状态码】---@CrossOrigin
 * @Copyright: 2019-xxx-Powered by 研发四部
 * @Author: LinHong
 * @CreateDate: 2019/12/5 10:46
 * @Version: V1.0
 */
@RestController
@CrossOrigin
public class ManageController {
    @Reference
    private ManageService manageService;

    /**
     * 功能描述
     * @MethodName: getCatalog1
     * @MethodParam: []
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseCatalog1>
     * @Description: TODO  据后台管理页面，查询所有一级分类(控制器)
     *               TODO  http://localhost:8082/getCatalog1
     * @Author: LinHong
     * @CreateDate: 2019/12/5 11:19
     */
    @RequestMapping("getCatalog1")
    public List<BaseCatalog1> getCatalog1(){
        return manageService.getCatalog1();

    }
    /**
     * 功能描述
     * @MethodName: getCatalog2
     * @MethodParam: [catalog1Id]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseCatalog2>
     * @Description: TODO  据后台管理页面，查询所有二级分类(控制器)
     *               TODO  http://localhost:8082/getCatalog2
     * @Author: LinHong
     * @CreateDate: 2019/12/5 15:02
     */
    @RequestMapping("getCatalog2")
    public List<BaseCatalog2> getCatalog2(String catalog1Id){
        return manageService.getCatalog2(catalog1Id);

    }
    /**
     * 功能描述
     * @MethodName: getCatalog3
     * @MethodParam: [catalog2Id]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseCatalog3>
     * @Description: TODO  据后台管理页面，查询所有三级分类(控制器)
     *               TODO[1] http://localhost:8082/getCatalog3
     *               TODO[2] http://localhost:8082/getCatalog3?catalog2Id=1
     * @Author: LinHong
     * @CreateDate: 2019/12/5 15:02
     */
   @RequestMapping("getCatalog3")
    public List<BaseCatalog3> getCatalog3(String catalog2Id){
        return manageService.getCatalog3(catalog2Id);

    }
    /**
     * 功能描述
     * @MethodName: attrInfoList
     * @MethodParam: [catalog3Id]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseAttrInfo>
     * @Description: TODO 获取平台属性
     *               TODO http://localhost:8082/attrInfoList?catalog3Id=101
     * @Author: LinHong
     * @CreateDate: 2019/12/5 15:43
     */

    @RequestMapping("attrInfoList")
    public List<BaseAttrInfo> attrInfoList(String catalog3Id){

        return manageService.getAttrInfoList(catalog3Id);

    }
    /**
     * 功能描述
     * @MethodName: saveBaseAttrInfo
     * @MethodParam: [baseAttrInfo] ---从前台页面中获取数据
     * @Return: void
     * @Description: TODO 添加平台属性
     *               TODO  //http://localhost:8082/saveAttrInfo
     *               TODO:RequestBody注解，页面以json格式传递到后台（将前台传递的json数据转换为java对象形式）
     * @Author: LinHong
     * @CreateDate: 2019/12/5 16:21
     */
    @RequestMapping("saveAttrInfo")
    public String saveBaseAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        manageService.saveBaseAttrInfo(baseAttrInfo);
        return "ok";

    }

    /**
     * 功能描述
     * @MethodName: getAttrValueList
     * @MethodParam: [attrId]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseAttrValue>
     * @Description: TODO 修改平台属性值--通过attrId回显平台属性值集合数据
     *               TODO  http://localhost:8082/getAttrValueList?attrId=70
     * @Author: LinHong
     * @CreateDate: 2019/12/5 17:00
     */
/*    @RequestMapping("getAttrValueList")
    public List<BaseAttrValue> getAttrValueList(String attrId){
        return manageService.getAttrValueList(attrId);


    }*/

    //【方式二】从业务中进行修改

    @RequestMapping("getAttrValueList")
    public List<BaseAttrValue> getAttrValueList(String attrId){
        //attrId=baseAttrInfo.id平台属性id
        //根据平台属性id查询是否有平台属性对象
        BaseAttrInfo baseAttrInfo = manageService.getBaseAttrInfo(attrId);
        //返回该对象下的平台属性值集合

        return  baseAttrInfo.getAttrValueList();


    }










}
