package com.djtu.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.djtu.springcloud.entities.Dept;
import com.djtu.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeptController {

	@Autowired
	private DeptService service;

	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id) {
		Dept dept = service.get(id);
		if (dept == null) {
			throw new RuntimeException("该ID:" + id + "没有对应的信息。");
		}
		return service.get(id);
	}

	public Dept processHystrix_Get(@PathVariable("id") Long id) {

		return new Dept().setDeptno(id).setDbname("该ID:" + id + "没有对应的信息，null--HystrixCommand")
				.setDb_source("no this database in mySql");
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return service.list();
	}

	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery() {
		List<String> list = client.getServices();
		List<ServiceInstance> srvList = client.getInstances("MICROSOFTSERVICECLOUD-DEPT");
		for (ServiceInstance ele : srvList) {
			System.out.println(ele.getUri());
		}
		return this.client;
	}

}
