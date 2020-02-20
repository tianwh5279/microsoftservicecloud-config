package com.djtu.springcloud.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Dept implements Serializable {// 必须序列化...

	//测试下git是怎么提交上去的
	
	private Long deptno;// 主键
	private String dbname;// 部门名称
	// 来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一信息被存储到不同数据库。
	private String db_source;
	
	public static void main(String[] args) {
		Dept dept=new Dept();	
		
	}
}

