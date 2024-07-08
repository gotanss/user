package com.demo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.demo.clients")
public class UserApplication {

   public static void main(String[] args) {
      SpringApplication.run(UserApplication.class, args);
   }

}
