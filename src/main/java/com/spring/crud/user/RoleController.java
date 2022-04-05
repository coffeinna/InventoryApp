package com.spring.crud.user;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.crud.product.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class RoleController {

	@Autowired
	private RoleRepository repo;
	
	@GetMapping("/roles")
	public String listRoles(Model model) {
		List<Role> listRoles= repo.findAll();
		model.addAttribute("listRoles", listRoles);
		
		
		return "roles";
	}
	@GetMapping("/roles/new")
	public String newRoleForm (Model model) {
		model.addAttribute("role", new Role());
		return "role_form";
	}
	
	@PostMapping("/roles/save")
	public String saveRole(Role role) {
		repo.save(role);
		return "redirect:/roles";
	}
	
	@GetMapping("/roles/edit/{id}")
	public String showEditRoleForm(@PathVariable(value = "id") Integer id,Model model) {
		Role role = repo.findById(id).get();
		model.addAttribute("role",role);
		return "role_form";
	}
	
	@GetMapping("/roles/delete/{id}")
	public String deleteRole(@PathVariable(value = "id") Integer id,Model model) {
		repo.deleteById(id);
		return "redirect:/roles";
	}
	
}
