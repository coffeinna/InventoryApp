package com.spring.crud.brand;
import java.util.List;

import org.springframework.beans. factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework. ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.crud.category.Category;
import com.spring.crud.category.CategoryRepository;


@Controller
public class BrandController {

	@Autowired
	private BrandRepository brandRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;

	@GetMapping("/brands/new")
	public String showCreateNewBrandForm(Model model) {
		List<Category> listCategories = categoryRepo.findAll();
		model.addAttribute("brand", new Brand());
		model.addAttribute("listCategories",listCategories);
		return "brand_form";

	}
	@GetMapping("/brands")
	public String listBrands(Model model) {
		List<Brand> listBrands= brandRepo.findAll();
		model.addAttribute("listBrands", listBrands);
		
		
		return "brands";
	}
	
	@PostMapping("/brands/save")
	public String saveBrand(Brand brand) {
		brandRepo.save(brand);
		return "redirect:/brands";
	}
	
	@GetMapping("/brands/edit/{id}")
	public String showEditBrandForm(@PathVariable(value = "id") Integer id,Model model) {
		List<Category> listCategories = categoryRepo.findAll();
		Brand brand = brandRepo.findById(id).get();
		model.addAttribute("brand", brand);
		model.addAttribute("listCategories",listCategories);
		return "brand_form";
	}
	
	@GetMapping("/brands/delete/{id}")
	public String deleteBrand(@PathVariable(value = "id") Integer id,Model model) {
		brandRepo.deleteById(id);
		return "redirect:/brands";
	}
}