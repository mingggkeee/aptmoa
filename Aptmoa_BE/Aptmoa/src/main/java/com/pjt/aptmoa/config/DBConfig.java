package com.pjt.aptmoa.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan( basePackages = "com.pjt.aptmoa.mapper" )
public class DBConfig {

}
