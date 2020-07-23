package com.order.feignproxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.model.OrderItem;

@Component
@FeignClient(value="OrderItemService")
public interface OrderItemServiceProxy {
	
	@GetMapping(value="/orderitem/getorderitembyorderid/{orderid}",produces = MediaType.APPLICATION_JSON_VALUE)
	List<OrderItem> findByOrderId(@PathVariable(value="orderid") long id);
	
	
	@PostMapping(value="/orderitem/addorderitem")
	int saveOrderItem(@RequestBody OrderItem orderItem);
	
	@PostMapping(value="/orderitem/updateorderitem")
	int updateOrderItem(@RequestBody OrderItem orderItem);
	
	@DeleteMapping("/orderitem/deleteorderitem/{orderid}")
	int deleteOrderItem(@PathVariable(value="orderid") long id);
	
	
	

}
