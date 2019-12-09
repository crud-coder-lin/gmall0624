package com.atguigu.gmall0624.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall0624.ManageService;
import com.atguigu.gmall0624.bean.*;
import com.atguigu.gmall0624.manage.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @BelongProjecet: gmall0624
 * @BelongPackage: com.atguigu.gmall0624.manage.service.impl
 * @ClassName: ManageServiceImpl
 * @Description: TODO
 * @Copyright: 2019-xxx-Powered by 研发四部
 * @Author: LinHong
 * @CreateDate: 2019/12/5 11:16
 * @Version: V1.0
 */
@Service
public class ManageServiceImpl implements ManageService {
    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;
    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;
    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;
    @Autowired
    private SpuInfoMapper spuInfoMapper;
    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    /**
     * 功能描述
     *
     * @MethodName: getCatalog1
     * @MethodParam: []
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseCatalog1>
     * @Description: TODO 查询一级分类所有（实现类）
     * @Author: LinHong
     * @CreateDate: 2019/12/5 11:17
     */
    @Override
    public List<BaseCatalog1> getCatalog1() {
        return baseCatalog1Mapper.selectAll();
    }

    /**
     * 功能描述
     *
     * @MethodName: getCatalog2
     * @MethodParam: [catalog1Id]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseCatalog2>
     * @Description: TODO 查询二级分类所有（实现类）--据一级分类id
     * TODO select * from baseCatalog2 where  catalog1Id=catalog1Id;
     * @Author: LinHong
     * @CreateDate: 2019/12/5 14:49
     */
    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        return baseCatalog2Mapper.select(baseCatalog2);
    }

    /**
     * 功能描述
     *
     * @MethodName: getCatalog3
     * @MethodParam: [catalog2Id]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseCatalog3>
     * @Description: TODO 查询三级分类所有（实现类）--据二级分类id
     * TODO select * from baseCatalog2 where  catalog2Id=catalog2Id;
     * @Author: LinHong
     * @CreateDate: 2019/12/5 14:52
     */
    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
      /*  BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setId(catalog2Id);
        return baseCatalog3Mapper.select(baseCatalog3);*/
        //第一个参数表示查询条件的实体类属性名

        Example example = new Example(BaseCatalog3.class);
        example.createCriteria().andEqualTo("catalog2Id", catalog2Id);
        return baseCatalog3Mapper.selectByExample(example);

    }

    /**
     * 功能描述
     *
     * @MethodName: getAttrInfoList
     * @MethodParam: [catalog3Id]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseAttrInfo>
     * @Description: TODO 获取平台属性(实现类)
     * TODO select * from baseatrrIndo where catalog3Id=?
     * @Author: LinHong
     * @CreateDate: 2019/12/5 15:32
     */
    @Override
    public List<BaseAttrInfo> getAttrInfoList(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        return baseAttrInfoMapper.select(baseAttrInfo);

    }

    /**
     * 功能描述
     *
     * @MethodName: saveBaseAttrInfo
     * @MethodParam: [baseAttrInfo]
     * @Return: void
     * @Description: TODO 添加平台属性-平台属性值(实现类)
     * TODO 添加与修改 最后的保存都是走同一个控制器
     * @Author: LinHong
     * @CreateDate: 2019/12/5 16:05
     */
    @Override
    @Transactional
    public void saveBaseAttrInfo(BaseAttrInfo baseAttrInfo) {

        //判断请求是修改还是保存发出类(根据id判断)
      /*  if(id){
            update
        }else {
            insert
        }*/
        //测试事务异常
    /*    int i=1/0;*/

        if (baseAttrInfo.getId() != null && baseAttrInfo.getId().length() > 0) {
            baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);

        } else {
            //1.保存baseAttrInfo
            baseAttrInfoMapper.insertSelective(baseAttrInfo);

        }
        //【1】先删除
        //2.保存baseAttrValue【如果是新增，可以将baseAttrValue中的数据先删除再新增】
        BaseAttrValue baseAttrValueDel = new BaseAttrValue();
        // delete from baseAttrValue where attrId = baseAttrInfo.id
        baseAttrValueDel.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValueDel);

        //【2】后新增
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (attrValueList != null && attrValueList.size() > 0) {
            //循环遍历
            for (BaseAttrValue baseAttrValue : attrValueList) {

                //获取id,则在baseAttrInfo实体类中必须添加获取主键自增的注解
                baseAttrValue.setAttrId(baseAttrInfo.getId());

                baseAttrValueMapper.insertSelective(baseAttrValue);
            }
        }
    }

    /**
     * 功能描述
     *
     * @MethodName: getAttrValueList
     * @MethodParam: [attrId]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseAttrValue>
     * @Description: TODO [1]根据平台属性 --查询平台属性值集合
     * @Author: LinHong
     * @CreateDate: 2019/12/6 15:46
     */
    @Override
    public List<BaseAttrValue> getAttrValueList(String attrId) {
        //根据平台属性值id(attrId=baseAtrrInfo.id) 查询平台属性值集合
        //select * from baseAttrInfoValue where attrId=?;
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrId);
        return baseAttrValueMapper.select(baseAttrValue);
    }

    /**
     * 功能描述
     *
     * @MethodName: getBaseAttrInfo
     * @MethodParam: [attrId]
     * @Return: com.atguigu.gmall0624.bean.BaseAttrInfo
     * @Description: TODO [2] 通过平台属性attrId回显平台属性对象
     * @Author: LinHong
     * @CreateDate: 2019/12/6 19:04
     */
    @Override
    public BaseAttrInfo getBaseAttrInfo(String attrId) {
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectByPrimaryKey(attrId);

        //将平台属性值集合放入平台对象中

       /* BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrId);
        List<BaseAttrValue> baseAttrValueList = baseAttrValueMapper.select(baseAttrValue);*/

        baseAttrInfo.setAttrValueList(getAttrValueList(attrId));
        return baseAttrInfo;
    }


    /**
     * 功能描述
     *
     * @MethodName: getSpuInfoList
     * @MethodParam: [spuInfo]
     * @Return: java.util.List<com.atguigu.gmall0624.bean.SpuInfo>
     * @Description: TODO
     * @Author: LinHong
     * @CreateDate: 2019/12/6 20:29
     */
    @Override
    public List<SpuInfo> getSpuList(String cataloh3Id) {
        return null;
    }

    @Override
    public List<SpuInfo> getSpuList(SpuInfo spuInfo) {
        return spuInfoMapper.select(spuInfo);
    }

    /**
     * 功能描述
     *
     * @MethodName: getBaseSaleAttrList
     * @MethodParam: []
     * @Return: java.util.List<com.atguigu.gmall0624.bean.BaseSaleAttr>
     * @Description: TODO [添加spu信息 ：1.查询销售属性字典表]
     * @Author: LinHong
     * @CreateDate: 2019/12/9 1:24
     */
    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    /**
     * 功能描述
     *
     * @MethodName: saveSpuInfo
     * @MethodParam: [spuInfo]
     * @Return: void
     * @Description: TODO [添加spu信息 2.保存spu信息]
     * TODO  spuInfo 表示从前台页面传递过来的数据
     * @Author: LinHong
     * @CreateDate: 2019/12/9 15:17
     */
    @Override
    @Transactional
    public void saveSpuInfo(SpuInfo spuInfo) {
        //spuInfo 商品表
        spuInfoMapper.insertSelective(spuInfo);
        //spuImage 商品图片表
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (spuImageList != null && spuImageList.size() > 0) {
            for (SpuImage spuImage : spuImageList) {
                spuImage.setSpuId(spuInfo.getId());
                spuImageMapper.insertSelective(spuImage);
            }
        }
        // spuSaleAttr 商品销售属性表
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if (spuSaleAttrList != null && spuSaleAttrList.size() > 0) {
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                spuSaleAttr.setSpuId(spuInfo.getId());
                spuSaleAttrMapper.insertSelective(spuSaleAttr);
                //spuSaleAttrValue 销售属性值表
                List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
//                if (spuSaleAttrValueList!=null && spuSaleAttrValueList.size()>0){
//                    for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
//                        spuSaleAttrValue.setSpuId(spuInfo.getId());
//                        spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
//                    }
//                }
                if (checkListIsEmpty(spuSaleAttrValueList)) {
                    for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                        spuSaleAttrValue.setSpuId(spuInfo.getId());
                        spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
                    }
                }
            }
        }

    }

    /**
     * 功能描述
     *
     * @MethodName: checkListIsEmpty
     * @MethodParam: [list]
     * @Return: boolean
     * @Description: TODO 判断集合是否为空的公共方法（泛型方法）
     * @Author: LinHong
     * @CreateDate: 2019/12/9 15:21
     */
    public <T> boolean checkListIsEmpty(List<T> list) {
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }


}
