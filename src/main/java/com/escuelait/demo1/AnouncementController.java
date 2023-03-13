package com.escuelait.demo1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnouncementController {
	private Map<Integer, Anouncement> anouncements = new ConcurrentHashMap<>();
	private AtomicInteger id = new AtomicInteger();
	
	@RequestMapping("/")
	public String anouncements(Model model) {
		model.addAttribute("anouncements", anouncements.values());
		return "anouncements_template";
	}
	
	@RequestMapping("/save")
	public ModelAndView save(Model model, Anouncement anouncement) {
		int anouncementId = id.getAndIncrement();  
		
		anouncement.setId(anouncementId);
		
		anouncements.put(anouncementId, anouncement);
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping("/anouncement")
	public String goToAnouncement(Model model) {	
		model.addAttribute("anouncement",new Anouncement());
		System.out.print(new Anouncement().toString());
		return "anouncement_template";
	}
	
	
	@RequestMapping("/anouncement/{id}")
	public String goToAnouncement(Model model, @PathVariable int id) {
		Anouncement anouncement = anouncements.get(id);
		
		model.addAttribute("anouncement",anouncement);
		
		return "anouncement_template";
	}
}