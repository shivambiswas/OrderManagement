package com.order.item.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.order.item.mapper.OrderItemMapper;
import com.order.item.model.OrderItem;


@Repository
public class OrderItemRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<OrderItem> findAll() {
		return jdbcTemplate.query("select * from OrderItems", new OrderItemMapper());
	}

	public List<OrderItem> findByOrderId(long id) {
		return  jdbcTemplate.query("select * from OrderItems where order_id=?",new Object[] { id }, new OrderItemMapper());
	}

	public int deleteById(long orderId) {
		return jdbcTemplate.update("delete from OrderItems where order_id=?", new Object[] { orderId });
	}

	public int insert(OrderItem orderItem) {
		return jdbcTemplate.update(
				"insert into OrderItems (product_code, product_name, quantity,order_id) "
						+ "values(?, ?, ?, ?)",
				new Object[] {orderItem.getProductCode(), orderItem.getProductName(),
						orderItem.getQuantity(), orderItem.getOrderId() });
	}

	public int updateByOrderId(OrderItem orderItem) {
		return jdbcTemplate.update(
				"update OrderItems " + " set product_code = ?, product_name = ?, quantity = ? where ORDER_ITEM_ID = ? ",
				new Object[] { orderItem.getProductCode(), orderItem.getProductName(),
						orderItem.getQuantity(),orderItem.getOrderItemId() });
	}
	
	public long getCount(){
		return jdbcTemplate.queryForObject("Select count(*) from OrderItems", Long.class);
	}

}
