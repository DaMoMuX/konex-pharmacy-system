package com.konex.pharmacy.sales_service.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.konex.pharmacy.sales_service.infrastructure.adapter.out")
public class FeignConfig {
}