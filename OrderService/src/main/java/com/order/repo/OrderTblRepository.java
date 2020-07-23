package com.order.repo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.order.mapper.OrderTblRowMapper;
import com.order.model.OrderTbl;

@Repository
public class OrderTblRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<OrderTbl> findAll() {
		return jdbcTemplate.query("select * from Orders", new OrderTblRowMapper());
	}

	public Optional<OrderTbl> findById(long id) {
		return Optional.of(jdbcTemplate.queryForObject("select * from Orders where order_id=?", new Object[] { id },
				new BeanPropertyRowMapper<OrderTbl>(OrderTbl.class)));
	}

	public int deleteById(long id) {
		return jdbcTemplate.update("delete from Orders where order_id=?", new Object[] { id });
	}

	public int insert(OrderTbl orderTbl) throws DataAccessException, ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		return jdbcTemplate.update(
				"insert into Orders (order_id, customer_name, order_date,shipping_address,total) "
						+ "values(?, ?, ?, ?, ?)",
				new Object[] { orderTbl.getOrderId(), orderTbl.getCustomerName(),sdf1.parse(orderTbl.getOrderDate()),
						orderTbl.getShippingAddr(), orderTbl.getTotal() });
	}

	public int update(OrderTbl orderTbl) throws DataAccessException, ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		return jdbcTemplate.update(
				"update Orders " + " set customer_name = ?, order_date = ?, shipping_address = ?, total = ? " + " where order_id = ?",
				new Object[] { orderTbl.getCustomerName(), sdf1.parse(orderTbl.getOrderDate()),
						orderTbl.getShippingAddr(), orderTbl.getTotal(), orderTbl.getOrderId()});
	}
	public long getCount(){
		return jdbcTemplate.queryForObject("Select count(*) from Orders", Long.class);
	}
}
