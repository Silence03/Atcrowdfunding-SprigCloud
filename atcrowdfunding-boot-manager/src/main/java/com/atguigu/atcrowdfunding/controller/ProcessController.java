package com.atguigu.atcrowdfunding.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.atcrowdfunding.common.bean.BaseController;
import com.atguigu.atcrowdfunding.common.util.Page;
import com.atguigu.atcrowdfunding.service.ProcessService;


@RequestMapping("/process")
@Controller
public class ProcessController extends BaseController {
	
	@Autowired
	ProcessService processService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 跳转主页面
	 */
	@RequestMapping("/index")
	public String toindex() {
		return "process/index";
	}
	/**
	 * 跳转流程图展示页面
	 */
	@RequestMapping("/view")
	public String view() {
		return "process/view";
	}
	
	
	/**
	 * 加载主页面数据处理方法
	 */
	@ResponseBody
	@RequestMapping("/queryPageData")
	public Object queryPageData(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
			@RequestParam(value="pagesize",required=false,defaultValue="2")Integer pagesize) {
		start();
		try {
			Page page = new Page(pageno,pagesize);
			Integer startindex = page.getStartindex();
			Map<String,Object> paramMap = new HashMap<>();
			
			paramMap.put("startindex", startindex);
			paramMap.put("pagesize", pagesize);
			
			List<Map<String, Object>> list = processService.queryPageData(paramMap);
			int totalsize = processService.querycount();
			
			page.setTotalsize(totalsize);
			page.setDatas(list);
			data(page);
			success(true);
		} catch (Exception e) {
			success(false);
			e.printStackTrace();
		}
		return end();
	}
	/**
	 * 上传流程文件处理方法
	 */
	@ResponseBody
	@RequestMapping("/uploaddf")
	public Object uploaddf(HttpServletRequest req) {
		start();
		try {
			MultipartHttpServletRequest request=(MultipartHttpServletRequest) req;
			MultipartFile file = request.getFile("procDefFile");
			String filename = file.getOriginalFilename();//获取文件原始名称
			String uuid=UUID.randomUUID().toString();
			//创建临时文件
			File tempFile = File.createTempFile(uuid, filename.substring(filename.lastIndexOf(".")));
			//把文件存进临时文件中
			file.transferTo(tempFile);
			
			FileSystemResource resouce = new FileSystemResource(tempFile);
			MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
			param.add("pdfile", resouce);
			
			String s = restTemplate.postForObject("http://atcrowdfunding-activiti-service/act/uploaddf", param, String.class);
			System.out.println("s==> "+s);
			tempFile.delete();
			
			success(true);
		} catch (Exception e) {
			success(false);
			e.printStackTrace();
		}
		return end();
	}
	/**
	 * 加载图片信息处理方法
	 */
	@RequestMapping("/loadImg")
	public void loadImg(String id,HttpServletResponse response ) {
		try {
			//设置响应头信息，以图片方式返回
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			String url="http://atcrowdfunding-activiti-service/act/loadImgById/"+id;
			ResponseEntity<byte[]> resentity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<byte[]>(headers), byte[].class);
			byte[] result = resentity.getBody();
			System.out.println("result==>"+result);
				InputStream is = new ByteArrayInputStream(result);
				OutputStream os = response.getOutputStream();
				int i=-1;
				while((i=is.read())!=-1) {
					os.write(i);
					System.out.println("i==>"+i);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
