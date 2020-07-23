package com.order.item.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.order.item.model.OrderItem;

public class OrderItemMapper implements RowMapper<OrderItem>{

	@Override
	public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setOrderItemId(rs.getInt("order_item_id"));
		orderItem.setProductCode(rs.getString("product_code"));
		orderItem.setProductName(rs.getString("product_name"));
		orderItem.setQuantity(rs.getInt("quantity"));
		return orderItem;
	}
	
	

}
