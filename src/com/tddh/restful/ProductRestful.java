package com.tddh.restful;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tddh.dao.impl.ProductDaoImpl;
import com.tddh.model.ProductCategoryListModel;
import com.tddh.model.ProductModel;
import com.tddh.model.ProxyProductModel;

@Path("product")
public class ProductRestful {
	private ProductDaoImpl pdi = new ProductDaoImpl();

	/**
	 * load all products
	 * 
	 * @return
	 */
	@Path("/loadAllProducts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductModel> loadAllProducts() {
		List<ProductModel> products = pdi.getAllProductService();
		return products;
	}

	/**
	 * load product information by proxy id and product id
	 * 
	 * @param proxy_id
	 * @param product_id
	 * @return
	 */
	@Path("/loadProductByPPId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ProxyProductModel loadProductByProxyAndId(@QueryParam("proxy_id") int proxy_id,
			@QueryParam("product_id") int product_id) {
		ProxyProductModel proxyProductModel = pdi.getProductByProxyAndId(proxy_id, product_id);
		return proxyProductModel;
	}

	/**
	 * get product price by proxy type
	 * 
	 * @param product_id
	 * @param proxy_id
	 * @return
	 */
	@Path("/getProductPriceByProxy")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductPriceByProxy(@QueryParam("product_id") int product_id,
			@QueryParam("proxy_id") int proxy_id) {
		String price = "" + pdi.getProductOrderPrice(product_id, proxy_id);
		return price;
	}

	@Path("/loadProductByCatory")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductCategoryListModel> loadProductByCatory(@QueryParam("categoryId") int categoryId) {
		List<ProductCategoryListModel> result = pdi.getProductCategoryByCategoryId(categoryId);
		return result;
	}
}
