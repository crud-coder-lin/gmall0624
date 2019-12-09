package com.atguigu.gmall0624.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall0624.ManageService;
import com.atguigu.gmall0624.bean.BaseSaleAttr;
import com.atguigu.gmall0624.bean.SpuInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongProjecet: gmall0624
 * @BelongPackage: com.atguigu.gmall0624.manage.controller
 * @ClassName: SpuManageController
 * @Description: TODO 一组易检索、可复用的标准化信息集合
 * @Copyright: 2019-xxx-Powered by 研发四部
 * @Author: LinHong
 * @CreateDate: 2019/12/9 0:15
 * @Version: V1.0
 */
@RestController
@CrossOrigin
public class SpuManageController {
    @Reference
    private ManageService manageService;
    /**
     * 功能描述
     * @MethodName: spuList
     * @MethodParam: [spuInfo]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.SpuInfo>
     * @Description: TODO
     *               TODO http://localhost:8082/spuList?catalog3Id=61
     *               TODO spuInfo字段是通过路径(url)传递过来的，不需要注解@requestBody，与实体类一致，springmvc进行了封装
     * @Author: LinHong
     * @CreateDate: 2019/12/9 0:17
     */
    @RequestMapping("spuList")
    public List<SpuInfo> spuList(SpuInfo spuInfo){
        return manageService.getSpuList(spuInfo);

    }
    /**
     * 功能描述
     * @MethodName: getBaseSaleAttrList
     * @MethodParam: []
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseSaleAttr>
     * @Description: TODO【步骤1：添加Spu信息】
     *               TODO  http://localhost:8082/baseSaleAttrList(查询销售属性字典表)
     *               TODO  颜色、版本、尺码
     * @Author: LinHong
     * @CreateDate: 2019/12/9 1:48
     */
    @RequestMapping("baseSaleAttrList")
    public List<BaseSaleAttr> getBaseSaleAttrList(){

        return manageService.getBaseSaleAttrList();
    }
    /**
     * 功能描述
     * @MethodName: saveSpuInfo
     * @MethodParam: [spuInfo]
     * @Return: void
     * @Description: TODO【步骤2：保存Spu信息】
     *               TODO   // http://localhost:8082/saveSpuInfo
     *
     * @Author: LinHong
     * @CreateDate: 2019/12/9 1:48
     */

    @RequestMapping("saveSpuInfo")
    public void saveSpuInfo(@RequestBody SpuInfo spuInfo){
        manageService.saveSpuInfo(spuInfo);
    }

}
