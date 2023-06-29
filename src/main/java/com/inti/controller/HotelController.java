package com.inti.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.inti.model.Hotel;
import com.inti.repository.IHotelRepository;


@Controller
public class HotelController {

	@Autowired
	IHotelRepository ihr;
	
	@GetMapping("creerHotel")
	public String creerHotel()
	{
		return "creerHotel";
	}
	
	@PostMapping("creerHotel")
	public String creerHotel(@ModelAttribute("hotel") Hotel h)
	{		
		ihr.save(h);
		
		return "redirect:/listeHotel";
	}
	
	@GetMapping("listeHotel")
	public String listeHotel(Model m)
	{
		m.addAttribute("listeH", ihr.findAll());
		
		return "listeHotel";
	}
	
	@GetMapping("modifierHotel/{idHotel}")
	public String modifierHotel(Model m, @PathVariable("idHotel") int idHotel)
	{
		m.addAttribute("hotel", ihr.getReferenceById(idHotel));
		
		return "modifierHotel";
	}
	
	@PostMapping("modifierHotel/{idHotel}")
	public String modifierHotel(@ModelAttribute("hotel") Hotel h, @PathVariable("idHotel") int idHotel)
	{
		ihr.save(h);
		
		return "redirect:/listeHotel";
	}
	
	@GetMapping("deleteHotel/{idHotel}")
	public String deleteHotel(@PathVariable("idHotel") int idHotel)
	{
		ihr.delete(ihr.getReferenceById(idHotel));
		
		return "redirect:/listeHotel";
	}
}
