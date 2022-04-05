package com.spring.crud.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.crud.category.Category;
import com.spring.crud.category.CategoryRepository;


@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	
	@GetMapping("/products/new")
	public String newProductForm (Model model) {
		List<Category> listCategories = categoryRepo.findAll();
		model.addAttribute("product", new Product());
		model.addAttribute("listCategories", listCategories);
		return "product_form";
	}
	
	@PostMapping("/products/save")
	public String saveProduct(Product product, HttpServletRequest request) {
		String[] detailIDs= request.getParameterValues("detailID");
		String[] detailNames= request.getParameterValues("detailName");
		String[] detailVaules= request.getParameterValues("detailValue");
		
		for (int i = 0; i<detailNames.length; i++ ) {
			if(detailIDs!=null && detailIDs.length>0) {
				product.setDetail(Integer.valueOf(detailIDs[i]),detailNames[i], detailVaules[i]);
			}else {
				product.addDetail(detailNames[i], detailVaules[i]);
			}
			product.addDetail(detailNames[i], detailVaules[i]);
		}
		
		productRepo.save(product);
		return "redirect:/products";
	}
	
	@GetMapping("/products")
	public String listProducts(Model model) {
		List<Product> listProducts= productRepo.findAll();
		model.addAttribute("listProducts", listProducts);
		return "products";
	}
	
	@GetMapping("/products/edit/{id}")
	public String showEditProductForm(@PathVariable(value = "id") Integer id,Model model) {
		Product product = productRepo.findById(id).get();
		model.addAttribute("product",product);
		List<Category> listCategories = categoryRepo.findAll();
		model.addAttribute("listCategories",listCategories);
		return "product_form";
	}

	@GetMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable(value = "id") Integer id,Model model) {
		productRepo.deleteById(id);
		return "redirect:/products";
	}
	
}
