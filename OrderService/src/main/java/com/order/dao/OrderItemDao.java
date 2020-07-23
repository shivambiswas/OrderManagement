package com.order.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.order.feignproxy.OrderItemServiceProxy;
import com.order.model.OrderItem;

@Component
public class OrderItemDao {

	@Autowired
	private OrderItemServiceProxy orderItemServiceProxy;
	@HystrixCommand(fallbackMethod = "saveOrderItemFallback")
	public int saveOrderItem(OrderItem orderItem) {
		return orderItemServiceProxy.saveOrderItem(orderItem);
	}
	
	@SuppressWarnings("unused")
	private int saveOrderItemFallback(OrderItem orderItem) {
		//Move item into the queue
		System.out.println("Remote dependent server is done not able to get the response as of now! ");
		return 1;
	}
	
	public int updateOrderItem(OrderItem orderItem) {
		return orderItemServiceProxy.updateOrderItem(orderItem);
	}
	public List<OrderItem> getOrderItemListByOrderId(long orderId){
		return this.orderItemServiceProxy.findByOrderId(orderId);
	}
	
	public int deleteOrderItem(long id) {
		return orderItemServiceProxy.deleteOrderItem(id);
	}
	
}
