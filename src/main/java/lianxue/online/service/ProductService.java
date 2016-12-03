package lianxue.online.service;

import java.util.List;

import lianxue.online.model.Product;



public interface ProductService {

	List<Product> selectProductList(Integer categoryId);

	List<Product> selectProductList(Product product);

	void addProduct(Product product);


}
