package com.order.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.order.model.OrderTbl;

public class OrderTblRowMapper implements RowMapper<OrderTbl> {

	@Override
	public OrderTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderTbl orderTbl = new OrderTbl();
		orderTbl.setOrderId(rs.getLong("order_id"));
		orderTbl.setCustomerName(rs.getString("customer_name"));
		orderTbl.setOrderDate(rs.getString("order_date"));
		orderTbl.setShippingAddr(rs.getString("shipping_address"));
		orderTbl.setTotal(rs.getInt("total"));
		return orderTbl;
		
	}

	

}
