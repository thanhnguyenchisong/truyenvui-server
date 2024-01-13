package com.sky.tv.comics.service.feignclient;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author thanhncs
 */

/**
 * There is 2 instance of employee-service (name defined in employee service spring boot project)
 * with port 8081 and 8082 => we just need define the name of service clearly on that.
 */
//@FeignClient(url = "http://localhost:8081;http://localhost:8082", value = "employee-service")
@FeignClient(name = "employee-service")
public interface EmployeeFeignClient {

  @GetMapping("employee/get/{id}")
  Object getEmployee(@PathVariable("id") UUID id);
}
