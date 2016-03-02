package com.tddh.model;

import java.io.Serializable;
/**
 * product category list model
 * @author jackson
 *
 */
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductCategoryListModel implements Serializable {
	private static final long serialVersionUID = 3618749028929703463L;
	private String categoryName;
	private List<ProductCategoryModel> categoryProducts;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ProductCategoryModel> getCategoryProducts() {
		return categoryProducts;
	}

	public void setCategoryProducts(List<ProductCategoryModel> categoryProducts) {
		this.categoryProducts = categoryProducts;
	}

	@Override
	public String toString() {
		return "{categoryName:" + categoryName + ", categoryProducts:" + categoryProducts + "}";
	}

}
