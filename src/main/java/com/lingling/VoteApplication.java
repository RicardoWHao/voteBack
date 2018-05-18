package com.lingling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.lingling.dao.testmapper")
public class VoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteApplication.class, args);
	}

//	@Bean(name = "multipartResolver")
//	public MultipartResolver multipartResolver(){
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		resolver.setDefaultEncoding("UTF-8");
//		resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
//		resolver.setMaxInMemorySize(40960);
//		resolver.setMaxUploadSize(50*1024*1024);//上传文件大小 50M 50*1024*1024
//		return resolver;
//	}
}
