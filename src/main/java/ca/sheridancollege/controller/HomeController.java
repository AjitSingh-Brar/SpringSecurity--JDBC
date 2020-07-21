package ca.sheridancollege.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.database.DataAccess;

@Controller
public class HomeController {

	@Autowired
	@Lazy
	DataAccess da;


	@GetMapping("/")
	public String root()
	{
		return "index.html";
	}
	@GetMapping("/user")
	public String login(Authentication authentication, Model model)
	{
		String name= authentication.getName();
		ArrayList<String> roles = new ArrayList<String>();
		for(GrantedAuthority ga: authentication.getAuthorities())
		{
			roles.add(ga.getAuthority());
		}
		model.addAttribute("name", name);
		model.addAttribute("roles", roles);

		return "/user/index.html";
	}

	@GetMapping("/manager")
	public String manageri()
	{

		return "/Manager/index.html";
	}
	@PostMapping("/manager")
	public String doManager(@RequestParam String username, @RequestParam String password,Model model)
	{
		model.addAttribute("hi",da.findUserAccount(username).getUserId());
	     return "/Manager/index.html";	
	}

	@GetMapping("/owner")
	public String ownerg()
	{

		return "/Owner/index.html";
	}

	@PostMapping("/owner")
	public String doOwner(@RequestParam String username, @RequestParam String password,Model model)
	{
		model.addAttribute("hi",da.findUserAccount(username).getUserId());
		return "/Owner/index.html";
	}

	@GetMapping("/employee")
	public String employeeg()
	{

		return "/Employee/index.html";
	}

	@PostMapping("/employee")
	public String doEmployee(@RequestParam String username, @RequestParam String password,Model model) {

		model.addAttribute("hi",da.findUserAccount(username).getUserId());
		return "/Employee/index.html";
	}

	@GetMapping("/login")
	public String getLogin()
	{
		return "login.html";
	}



	@PostMapping("/login")
	public String loginM(@RequestParam String username, @RequestParam String password,Model model)

	{model.addAttribute("hi",da.findUserAccount(username).getUserId());
	return "/user/index.html";

	}

	@GetMapping("/access-denied")
	public String accessDenied()
	{
		return "/error/accessdenied.html";
	}





}
