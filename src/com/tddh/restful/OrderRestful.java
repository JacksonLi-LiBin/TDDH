package com.tddh.restful;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tddh.dao.impl.OrderDaoImpl;
import com.tddh.model.OrderDetailModel;

/**
 * handle order request
 * 
 * @author jackson
 *
 */
@Path("order")
public class OrderRestful {
	private OrderDaoImpl odi = new OrderDaoImpl();

	@Path("/loadOrders")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String loadOrders(@QueryParam("order_type") int orderType, @QueryParam("user_id") int userId) {
		List<OrderDetailModel> list = odi.getOrderDetail(orderType, userId);
		return list == null ? "" : list.toString();
	}
}
