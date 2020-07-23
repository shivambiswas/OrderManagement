package com.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.OrderDao;
import com.order.model.OrderTbl;

@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	public List<OrderTbl> getOrderList(){
		return orderDao.getOrderList();
	}
	
	public String saveOrUpdateOrder(OrderTbl ordertable) {
		if(orderDao.saveOrUpdateOrder(ordertable)>0)
			return "Success!";
		else
			return "Not Success";
	}

	public int deleteOrder(long id) {
		return orderDao.deleteOrder(id);
	}
	
	public OrderTbl getOrderByOrderId(long id) {
		return orderDao.getOrdersByOrderId(id);
	}
}
