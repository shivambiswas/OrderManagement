package com.order.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.item.dao.OrderItemDao;
import com.order.item.model.OrderItem;



@Service
public class OrderItemService {

	@Autowired
	private OrderItemDao orderItemDao;
	
	public List<OrderItem> getOrderItemList(){
		return orderItemDao.getOrderItemList();
	}
	
	public int addOrderItem(OrderItem orderItem)
	{
		return orderItemDao.addOrderItem(orderItem);
	}
	
	public List<OrderItem> getOrderItemList(long id){
		return orderItemDao.getOrderItemByOredrId(id);
	}
	
	public int updateOrderItem(OrderItem orderItem) {
		return orderItemDao.updateOrderItemByOredrId(orderItem);
	}
	
	public int deleteOrderItem(long orderId) {
		return orderItemDao.deleteOrderItem(orderId);
	}
}
