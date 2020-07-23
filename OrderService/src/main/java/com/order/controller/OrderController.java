package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.model.OrderTbl;
import com.order.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@GetMapping("/getorderlist")
	public List<OrderTbl> getOrderList() {
		return orderService.getOrderList();	
	}
	
	@PostMapping("/saveorupdateorder")
	public String saveOrders(@RequestBody OrderTbl orderTable) {
		return orderService.saveOrUpdateOrder(orderTable);
	}
	
	@DeleteMapping("/deleteorder/{orderid}")
	public int deleteOrder(@PathVariable(value="orderid") long id) {
		return orderService.deleteOrder(id);
	}
	@GetMapping("/getorderbyorderid/{orderid}")
	public OrderTbl getOrderByOrderId(@PathVariable(value="orderid") long id) {
		return orderService.getOrderByOrderId(id);
	}

}
