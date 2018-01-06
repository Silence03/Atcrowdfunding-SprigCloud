package com.atguigu.atcrowdfunding.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.atcrowdfunding.common.bean.BaseController;

@RestController
public class ActivitiController extends BaseController {
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	
//	@RequestMapping("/act/queryPageData/{startindex}/{pagesize}")
//	public List<Map<String, Object>> queryPageData(@PathVariable("startindex")Integer startindex,@PathVariable("pagesize")Integer pagesize){
//		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
//		List<ProcessDefinition> listPage = processDefinitionQuery.listPage(startindex, pagesize);
//		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//		for (ProcessDefinition pd : listPage) {
//			Map<String,Object> map = new HashMap<>();
//			map.put("id", pd.getId());
//			map.put("key", pd.getKey());
//			map.put("name", pd.getName());
//			map.put("version", pd.getVersion());
//			map.put("deployid", pd.getDeploymentId());
//			list.add(map);
//		}
//		return list;
//	}
	
	@RequestMapping("/act/queryPageData")
	public List<Map<String, Object>> queryPageData(@RequestBody Map<String,Object> paramMap){
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		
		Integer startindex=(Integer) paramMap.get("startindex");
		Integer pagesize=(Integer) paramMap.get("pagesize");
		
		List<ProcessDefinition> listPage = processDefinitionQuery.listPage(startindex, pagesize);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (ProcessDefinition pd : listPage) {
			Map<String,Object> map = new HashMap<>();
			map.put("id", pd.getId());
			map.put("key", pd.getKey());
			map.put("name", pd.getName());
			map.put("version", pd.getVersion());
			map.put("deployid", pd.getDeploymentId());
			list.add(map);
		}
		return list;
	}
	/**
	 * 查询流程定义总数
	 */
	@RequestMapping("/act/querycount")
	public int querycount() {
		long count = repositoryService.createProcessDefinitionQuery().count();
		return (int)count;
	}
	/**
	 * 部署流程定义
	 */
	@RequestMapping("/act/uploaddf")
	public Object uploaddf(@RequestParam("pdfile")MultipartFile file) {
		start();
		try {
			Deployment deploy = repositoryService.createDeployment().addInputStream(file.getOriginalFilename(),file.getInputStream()).deploy();
			if(deploy!=null) {
				success(true);
			}else{
				success(false);
			}
		} catch (Exception e) {
			success(false);
			e.printStackTrace();
		}
		return end();
	}
	/**
	 * 加载流程图片
	 */
	@RequestMapping("/act/loadImgById/{id}")
	public byte[] loadImgById(@PathVariable("id")String id) {
		ProcessDefinitionQuery pdq = repositoryService.createProcessDefinitionQuery();
		ProcessDefinition singleResult = pdq.processDefinitionId(id).singleResult();
		String resourceName = singleResult.getDiagramResourceName();
		String deploymentId = singleResult.getDeploymentId();
		
		InputStream is = repositoryService.getResourceAsStream(deploymentId, resourceName);
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream(); 
		byte[] data=new byte[100];
		int i = -1;
		try {
			while((i=is.read(data, 0, 100))>0) {
				swapStream.write(data, 0, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] byteArray = swapStream.toByteArray();
		return byteArray;
	}
	/**
	 * 开启流程实例
	 */
	@RequestMapping("/act/startprocess/{loginacct}")
	public String startProcess(@PathVariable("loginacct")String loginacct) {
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		ProcessDefinition singleResult = processDefinitionQuery.processDefinitionKey("auth").latestVersion().singleResult();
		Map<String,Object> parammap = new HashMap<>();
		parammap.put("loginacct", loginacct);
		System.out.println();
		ProcessInstance startProcessInstanceById = runtimeService.startProcessInstanceById(singleResult.getId(),parammap);
		return startProcessInstanceById.getId();
	}
	/**
	 * 完成任务
	 */
	@RequestMapping("/act/completetask")
	public void completetask(@RequestBody Map<String, Object> map) {
		TaskQuery taskQuery = taskService.createTaskQuery();
		Task task = taskQuery.processInstanceId((String)map.get("piid")).taskAssignee((String)map.get("loginacct")).singleResult();
		taskService.complete(task.getId(),map);
	}
	
	
	
	
	
	
	
	
	
}
