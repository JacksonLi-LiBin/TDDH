package com.tddh.restful;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tddh.dao.impl.ProxyDaoImpl;
import com.tddh.model.ProxyModel;

@Path("proxy")
public class ProxyRestful {
	private ProxyDaoImpl pdi = new ProxyDaoImpl();

	/**
	 * load proxy according to request condition
	 * 
	 * @return
	 */
	@Path("/loadSpecifiedProxies")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProxyModel> loadSpecifiedProxies(@QueryParam("requestCondition") String requestCondition,
			@QueryParam("userId") int userId, @QueryParam("productId") int productId) {
		List<ProxyModel> proxyModels = pdi.getSpecifiedProxies(requestCondition, userId, productId);
		return proxyModels;
	}
}
