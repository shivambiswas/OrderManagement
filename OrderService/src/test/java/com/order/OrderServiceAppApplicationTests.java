package com.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.order.dao.OrderItemDao;
import com.order.model.OrderItem;
import com.order.model.OrderTbl;
import com.order.repo.OrderTblRepository;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class OrderServiceAppApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private OrderTblRepository orderRepo;

	@MockBean
	private OrderItemDao orderItemDemo;

	@Test
	public void testGetOrders() throws Exception {

		Mockito.when(orderRepo.findAll()).thenReturn(getOrderTable());
		Mockito.when(orderItemDemo.getOrderItemListByOrderId(Mockito.any(Long.class)))
				.thenReturn(getOrderTable().get(0).getOrderItemsList());

		this.mvc.perform(get("/order/getorderlist")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testSaveOrUpdateOrders() throws Exception {
		
		List<OrderTbl> orderTblList = this.getOrderTable();
		orderTblList.get(0).setOrderId(null);
		orderTblList.get(0).getOrderItemsList().get(0).setOrderItemId(0);
		Mockito.when(orderRepo.insert(Mockito.any(OrderTbl.class))).thenReturn(1);
		Mockito.when(orderItemDemo.saveOrderItem(Mockito.any(OrderItem.class))).thenReturn(1);
		
		 Gson gson = new Gson();
		    String json = gson.toJson(orderTblList.get(0));

		this.mvc.perform(post("/order/saveorupdateorder")
				.contentType(MediaType.APPLICATION_JSON).
				content(json))
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	public void testDeleteOrders() throws Exception {
		Mockito.when(orderItemDemo.getOrderItemListByOrderId(Mockito.any(Long.class))).thenReturn(this.getOrderTable().get(0).getOrderItemsList());
		Mockito.when(orderRepo.deleteById(Mockito.any(Long.class))).thenReturn(1);
		Mockito.when(orderItemDemo.deleteOrderItem(Mockito.any(Long.class))).thenReturn(1);

		this.mvc.perform(delete("/order/deleteorder/{orderid}","1"))
				.andExpect(status().is2xxSuccessful());

	}

	private List<OrderTbl> getOrderTable() {

		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		List<OrderTbl> orderList = new ArrayList<OrderTbl>();
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(1);
		orderItem.setOrderItemId(100);
		orderItem.setProductCode("IP6");
		orderItem.setProductName("Iphone 6");
		orderItem.setQuantity(1);
		orderItemList.add(orderItem);

		OrderTbl orderTbl = new OrderTbl();
		orderTbl.setCustomerName("ABC");
		orderTbl.setOrderDate("03-03-2019");
		orderTbl.setOrderId(1L);
		orderTbl.setOrderItemsList(orderItemList);
		orderTbl.setShippingAddr("3-7-368/A,Hyderabad,500038");
		orderTbl.setTotal(230);
		orderList.add(orderTbl);

		return orderList;
	}

}
