package com.order.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.order.exception.GenericException;
import com.order.exception.OrderNotFoundException;
import com.order.model.OrderItem;
import com.order.model.OrderTbl;
import com.order.repo.OrderTblRepository;

@Component
public class OrderDao {

	private static final Logger LOG = LoggerFactory.getLogger(OrderDao.class);

	@Autowired
	private OrderTblRepository orederRepo;

	@Autowired
	private OrderItemDao orderItemDemo;

	public List<OrderTbl> getOrderList() {
		List<OrderTbl> orderTableList = orederRepo.findAll();
		LOG.info("Order Table response from DB..", orderTableList.size());
		if(null!=orderTableList)
		orderTableList.forEach(order -> {
			order.setOrderItemsList(orderItemDemo.getOrderItemListByOrderId(order.getOrderId()));
		});
		return orderTableList;
	}

	public int saveOrUpdateOrder(OrderTbl ordertbl) {
		int count = 0;
		if (ordertbl.getOrderId() == null) {
			long orderid = orederRepo.getCount() + 1;
			ordertbl.setOrderId(orderid);
			try {
				count=orederRepo.insert(ordertbl);
				ordertbl.getOrderItemsList().forEach(orderItem -> {
					orderItem.setOrderId(ordertbl.getOrderId());
					if (orderItem.getOrderItemId() > 0) {
						orderItemDemo.updateOrderItem(orderItem);
					} else {
						orderItemDemo.saveOrderItem(orderItem);
					}
				});
			} catch (Exception e) {
				LOG.error(e.getMessage());
				throw new GenericException("ODR-00G", "Somthing Went Wrong!", "Somthing Went Wrong!");
			} 
			if(count>0)
				return 1;
			else 
				return 0;
		} else {
			try {
				count = orederRepo.update(ordertbl);
				ordertbl.getOrderItemsList().forEach(orderItem -> {
					orderItem.setOrderId(ordertbl.getOrderId());
					if (orderItem.getOrderItemId() > 0) {
						orderItemDemo.updateOrderItem(orderItem);
					} else {
						orderItemDemo.saveOrderItem(orderItem);
					}
				});
				if(count>0)
					return 1;
				else 
					return 0;
			} catch (DataAccessException e) {
				LOG.error(e.getMessage());
				throw new GenericException("ODR-00G", "Somthing Went Wrong!", "Somthing Went Wrong!");
			} catch (Exception e) {
				LOG.error(e.getMessage());
				throw new GenericException("ODR-00G", "Somthing Went Wrong!", "Somthing Went Wrong!");
			}

		}
		
	}

	public OrderTbl getOrdersByOrderId(long id) {
		try {
			OrderTbl ordertbl = orederRepo.findById(id).get();
			if (null != ordertbl) {
				ordertbl.setOrderItemsList(orderItemDemo.getOrderItemListByOrderId(id));
			}
			return ordertbl;
		} catch (EmptyResultDataAccessException ex) {
			throw new OrderNotFoundException("No Order data available for the given order id!","ODR-001","Provide valid order id!");
		}

	}

	public int deleteOrder(long id) {
			List<OrderItem> orderItemList = orderItemDemo.getOrderItemListByOrderId(id);
			if(null!=orderItemList && orderItemList.size()>0) {
			int orderCount = orederRepo.deleteById(id);
			orderItemList.forEach(orderItem -> {
				orderItemDemo.deleteOrderItem(orderItem.getOrderId());
			});
			return orderCount;
			}
			else {
				throw new OrderNotFoundException("No Order data available for the given order id!","ODR-002","Provide valid order id for delete!");
			}
			
		
	}

}
