package com.order.item.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.item.model.OrderItem;
import com.order.item.repo.OrderItemRepository;

@Component
public class OrderItemDao {
	@Autowired
	private OrderItemRepository orderItemRepo;
	public List<OrderItem> getOrderItemList(){
		return orderItemRepo.findAll();
	}
	
	public int addOrderItem(OrderItem orderItem) {
		if(orderItem.getOrderId()>0)
		return orderItemRepo.insert(orderItem);
		else
			return -1;
	}
	
	public int updateOrderItemByOredrId(OrderItem orderItem) {
		return orderItemRepo.updateByOrderId(orderItem);
	}
	
	public List<OrderItem> getOrderItemByOredrId(long id) {
		return orderItemRepo.findByOrderId(id);

	}
	
	public int deleteOrderItem(long orderId) {
		return this.orderItemRepo.deleteById(orderId);
	}

}
