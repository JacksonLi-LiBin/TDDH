package com.tddh.restful;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.tddh.dao.impl.OrderDaoImpl;
import com.tddh.model.PurchaseUserProductModel;
import com.tddh.model.UncheckUserOrderModel;
import com.tddh.model.UserSubordinateRecommendOrderModel;

/**
 * handle order request
 * 
 * @author jackson
 *
 */
@Path("order")
public class OrderRestful {
	private OrderDaoImpl odi = new OrderDaoImpl();

	/**
	 * get order type 0 my order 1 my proxy order
	 * 
	 * @param orderType
	 * @param userId
	 * @return
	 */
	@Path("/loadOrders")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String loadOrders(@QueryParam("orderType") int orderType, @QueryParam("userId") int userId) {
		String userOrder = odi.getOrderDetail(orderType, userId);
		return userOrder;
	}

	/**
	 * get user subordinate or recommend product proxy orders
	 * 
	 * @param reqType
	 *            0 subordinate 1 recommend
	 * @param userId
	 * @param productId
	 * @return
	 */
	@Path("/loadUserSROrders")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String loadUserSROrders(@QueryParam("reqType") int reqType, @QueryParam("userId") int userId) {
		List<List<List<UserSubordinateRecommendOrderModel>>> models = odi.getMySubordinateRecommendOrder(reqType,
				userId);
		return JSON.toJSONString(models);
	}

	/**
	 * user purchase products
	 * 
	 * @param purchaseUserProductModel
	 * @return
	 */
	@Path("/purchaseProduct")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String purchaseProduct(PurchaseUserProductModel purchaseUserProductModel) {
		return odi.purchaseOrder(purchaseUserProductModel.getUserId(), purchaseUserProductModel.getPurchaseProducts(),
				purchaseUserProductModel.getAddressId(), purchaseUserProductModel.getPayType()) ? "true" : "false";
	}

	/**
	 * administrator get unchecked orders
	 * 
	 * @param orderType
	 * @return
	 */
	@Path("/loadUncheckOrder")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UncheckUserOrderModel> loadUncheckOrder(@QueryParam("orderType") int orderType) {
		List<UncheckUserOrderModel> orders = odi.getUncheckUserOrder(orderType);
		return orders;
	}

	/**
	 * 
	 * @param orderId
	 * @param orderType
	 * @param handleType
	 *            0 pass 1 reject
	 * @return
	 */
	@Path("/handleUncheckOrder")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String handleUncheckOrder(@QueryParam("orderId") int orderId, @QueryParam("orderType") int orderType,
			@QueryParam("handleType") int handleType) {
		return odi.handleUncheckOrder(orderId, orderType, handleType) ? "true" : "false";
	}
}
