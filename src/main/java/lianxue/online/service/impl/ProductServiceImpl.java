package lianxue.online.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lianxue.online.mapper.ProductMapper;
import lianxue.online.model.Product;
import lianxue.online.service.ProductService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	public List<Product> selectProductList(Integer categoryId) {
		Example example = new Example(Product.class);
		example.createCriteria().andEqualTo("categoryId", categoryId);
		return productMapper.selectByExample(example);
	}

	@Override
	public List<Product> selectProductList(Product product) {
		Example example = new Example(Product.class);
		Criteria c = example.createCriteria();
		if(StringUtils.isNotEmpty(product.getProductName())){
			c.andLike("productName", "%"+product.getProductName()+"%");
		}
		if(product.getCategoryId() != null){
			c.andEqualTo("categoryId", product.getCategoryId());
		}
		return productMapper.selectByExample(example);
	}

	@Override
	public void addProduct(Product product) {
		int add = productMapper.insert(product);
		if(add != 1){
			throw new RuntimeException("增加失败");
		}
	}

	@Override
	public Product selectProduct(Long id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public void editProduct(Product product){
		int edit = productMapper.updateByPrimaryKey(product);
		if(edit != 1){
			throw new RuntimeException("修改失败");
		}
	}

	@Override
	public void deleteProduct(Long id) {
		int delete = productMapper.deleteByPrimaryKey(id);
		if(delete != 1){
			throw new RuntimeException("删除失败");
		}
	}
	
	

}
