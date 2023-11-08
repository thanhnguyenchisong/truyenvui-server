package com.sky.tv.comics.service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

/**
 * @author thanhncs
 */
@FeignClient(url = "http://localhost:8081", value = "employee-service")
public interface EmployeeFeignClient {
	@GetMapping("employee/get/{id}")
	Object getEmployee(@PathVariable("id") UUID id);
}
