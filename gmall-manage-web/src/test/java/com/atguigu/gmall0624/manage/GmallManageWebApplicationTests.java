package com.atguigu.gmall0624.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

	@Test
	public void contextLoads() {
	}
	/**
	 * 功能描述
	 * @MethodName: textFileUpload
	 * @MethodParam: []
	 * @Return: void
	 * @Description: TODO s = group1
	 *               TODO    s = M00/00/00/wKgrcV3tKs2AUiamAAAl_GXv6Z4371.jpg
	 *            TODO 测试地址   http://192.168.43.113/group1/M00/00/00/wKgrcV3tKs2AUiamAAAl_GXv6Z4371.jpg
	 * @Author: LinHong
	 * @CreateDate: 2019/12/9 0:55
	 */
	@Test
	public void textFileUpload() throws IOException, MyException {
		String file = this.getClass().getResource("/tracker.conf").getFile();
		//初始化
		ClientGlobal.init(file);
		TrackerClient trackerClient=new TrackerClient();
		TrackerServer trackerServer=trackerClient.getConnection();
		//存储数据
		StorageClient storageClient=new StorageClient(trackerServer,null);

		String orginalFilename="E://(000)DevelopTools/windows-nginx-1.12.0/test-picture-idea-fastDFS/666.jpg";
		String[] upload_file = storageClient.upload_file(orginalFilename, "jpg", null);
		for (int i = 0; i < upload_file.length; i++) {
			String s = upload_file[i];
			System.out.println("s = " + s);
		}
	}

}
