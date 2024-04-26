package com.cbt.observableservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Observableservice1Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Observableservice1Application.class, args);
    }

}
