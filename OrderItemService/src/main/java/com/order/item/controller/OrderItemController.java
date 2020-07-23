package com.order.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.item.model.OrderItem;
import com.order.item.service.OrderItemService;


@RestController
@RequestMapping("/orderitem")
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;

	@GetMapping("/getorderitemlist")
	public List<OrderItem> getOrderItemList() {
		return this.orderItemService.getOrderItemList();
	}

	@PostMapping("/addorderitem")
	public int addOrderItem(@RequestBody OrderItem orderItem) {
		return orderItemService.addOrderItem(orderItem);
	}

	@GetMapping("/getorderitembyorderid/{orderid}")
	public List<OrderItem> getOrderItemByOrderId(@PathVariable(value = "orderid") long id) {
		List<OrderItem> orderItemList = orderItemService.getOrderItemList(id);
		System.out.println(orderItemList);
		return orderItemList;
	}

	@PostMapping("/updateorderitem")
	public int updateOrderItem(@RequestBody OrderItem orderItem) {
		return orderItemService.updateOrderItem(orderItem);
	}

	@DeleteMapping("/deleteorderitem/{orderid}")
	public int deleteOrderItem(@PathVariable(value = "orderid") long orderId) {
		return orderItemService.deleteOrderItem(orderId);
	}

}
