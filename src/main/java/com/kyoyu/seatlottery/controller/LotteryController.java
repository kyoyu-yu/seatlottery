package com.kyoyu.seatlottery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyoyu.seatlottery.service.LotteryService;

@Controller
public class LotteryController {

	private LotteryService service;
	private static final String ADMIN_PASSWORD = "1234";

	public LotteryController() {
		service = new LotteryService();
	}

	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute(
				"participants",
				service.getParticipants());
		
		model.addAttribute(
			    "seatMap",
			    service.getSeatMap()
			);

		return "index";
	}

	//配席
	@PostMapping("/draw")
	public String draw(
	        @RequestParam String name,
	        Model model) {

	    try {
	        Integer seat = service.draw(name);

	        model.addAttribute("seat", seat);
	        model.addAttribute("selectedName", name);

	    } catch (IllegalArgumentException | IllegalStateException e) {
	        model.addAttribute("errorMessage", e.getMessage());
	    }

	    model.addAttribute(
	        "participants",
	        service.getParticipants()
	    );
	    
	    model.addAttribute(
	    	    "seatMap",
	    	    service.getSeatMap()
	    	);

	    return "index";
	}
	
	//個別リセット
	@PostMapping("/reset-participant")
	public String resetParticipant(
	        @RequestParam String password,
	        @RequestParam String name,
	        Model model) {

	    if (!ADMIN_PASSWORD.equals(password)) {
	        model.addAttribute("errorMessage", "管理者パスワードが違います");
	    } else {
	        service.resetParticipant(name);
	        model.addAttribute("message", name + "さんの抽選結果をリセットしました");
	    }

	    model.addAttribute(
	        "participants",
	        service.getParticipants()
	    );

	    model.addAttribute(
	        "seatMap",
	        service.getSeatMap()
	    );

	    return "index";
	}
	
	//全員リセット
	@PostMapping("/reset-all")
	public String resetAll(
	        @RequestParam String password,
	        Model model) {

	    if (!ADMIN_PASSWORD.equals(password)) {
	        model.addAttribute("errorMessage", "管理者パスワードが違います");
	    } else {
	        service.resetAll();
	        model.addAttribute("message", "全員の抽選結果をリセットしました");
	    }

	    model.addAttribute(
	        "participants",
	        service.getParticipants()
	    );

	    model.addAttribute(
	        "seatMap",
	        service.getSeatMap()
	    );

	    return "index";
	}
}