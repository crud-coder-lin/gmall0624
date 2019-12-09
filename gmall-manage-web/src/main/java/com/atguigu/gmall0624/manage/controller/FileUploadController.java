package com.atguigu.gmall0624.manage.controller;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @BelongProjecet: gmall0624
 * @BelongPackage: com.atguigu.gmall0624.manage.controller
 * @ClassName: FileUploadController
 * @Description: TODO
 * @Copyright: 2019-xxx-Powered by 研发四部
 * @Author: LinHong
 * @CreateDate: 2019/12/9 0:58
 * @Version: V1.0
 */
@RestController
@CrossOrigin
public class FileUploadController {
    // fileUrl = http://192.168.43.113

    @Value("${fileServer.url}")
    private String fileUrl;
    /**
     * 功能描述
     * @MethodName: fileUpload
     * @MethodParam: [file]
     * @Return: java.lang.String
     * @Description: TODO http://192.168.43.113/group1/M00/00/00/wKgrcV3tKs2AUiamAAAl_GXv6Z4371.jpg
     *               TODO springMVC 文件上传技术
     * @Author: LinHong
     * @CreateDate: 2019/12/9 1:00
     */

    @RequestMapping("fileUpload")
    public String fileUpload(MultipartFile file)throws IOException, MyException {
        //  imgUrl = http://192.168.43.113

        String imgUrl = fileUrl;

        if (file!=null){
            // 上传谁回显谁！
            // 表示读取配置文件中的tracker.conf
            String configFile  = this.getClass().getResource("/tracker.conf").getFile();
            // 初始化
            ClientGlobal.init(configFile );
            TrackerClient trackerClient=new TrackerClient();
            TrackerServer trackerServer=trackerClient.getConnection();
            // 存储数据
            StorageClient storageClient=new StorageClient(trackerServer,null);
            // String orginalFilename="e://img//zly.jpg";
            // 获取上传的文件名称 // zly.jpg
            String originalFilename = file.getOriginalFilename();
            //  originalFilename  vs  orginalFilename 一样？
            // 获取文件后缀名
            String extName = StringUtils.substringAfterLast(originalFilename, ".");

            // 保存数据
            String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);
            for (int i = 0; i < upload_file.length; i++) {
                String path = upload_file[i];
                // System.out.println("s = " + s);

                imgUrl+="/"+path;
                // imgUrl=imgUrl+path  http://192.168.43.113/group1/M00/00/00/wKgrcV3tKs2AUiamAAAl_GXv6Z4371.jpg

			/*
			  s = group1
	          s = M00/00/00/wKgrcV3tKs2AUiamAAAl_GXv6Z4371.jpg
			 */
            }
        }
        //  return "http://192.168.43.113/group1/M00/00/00/wKgrcV3tKs2AUiamAAAl_GXv6Z4371.jpg";
        return imgUrl;
    }
}
