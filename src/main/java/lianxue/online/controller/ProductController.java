package lianxue.online.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import lianxue.online.code.Result;
import lianxue.online.model.Product;
import lianxue.online.service.ProductService;
import lianxue.online.util.Config;
import lianxue.online.util.FileUtil;
@Controller
@RequestMapping("/product")
public class ProductController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/{categoryId}",method=RequestMethod.GET)
	public String selectProductList(@PathVariable Integer categoryId,Model map){
		List<Product> productList = productService.selectProductList(categoryId);
		map.addAttribute("productList", productList);
		map.addAttribute("product_cate", categoryId);
		return "product";
	}
	
	@RequestMapping(value="/manager",method=RequestMethod.GET)
	public String manager(){
		return "product/product";
	}
	
	@RequestMapping(value="/dataGrid")
	@ResponseBody
	public List<Product> dataGrid(Product product,Integer page,Integer rows){
		PageHelper.startPage(page, rows);
		List<Product> productList = productService.selectProductList(product);
		return productList;
	}
	
	 /**
     * 添加产品页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "product/productAdd";
    }
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Result add(HttpServletRequest request,Product product){
    	Result result = new Result();
    	try {
    		product.setImageAddress(FileUtil.upload(Config.PRODUCTIMAGEPATH,request));
    		productService.addProduct(product);
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;
        } catch (Exception e) {
            LOGGER.error("添加用户失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }
    @RequestMapping(value="/editPage",method=RequestMethod.GET)
    public String editPage(Long id,Model model){
    	Product product = productService.selectProduct(id);
    	model.addAttribute("product", product);
    	return "product/productEdit";
    }
    
    
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(Product product){
    	Result result = new Result();
    	try {
			productService.editProduct(product);
			result.setSuccess(true);
            result.setMsg("修改成功");
            return result;
		} catch (Exception e) {
			LOGGER.error("修改失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
		}
    }
	
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id){
    	Result result = new Result();
    	try {
    		productService.deleteProduct(id);
    		result.setSuccess(true);
    		result.setMsg("删除成功");
    		return result;
    	} catch (Exception e) {
    		LOGGER.error("删除失败：{}", e);
    		result.setMsg(e.getMessage());
    		return result;
    	}
    }
    

}
