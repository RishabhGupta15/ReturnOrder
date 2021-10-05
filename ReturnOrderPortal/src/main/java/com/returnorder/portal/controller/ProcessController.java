package com.returnorder.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import com.returnorder.portal.client.AuthClient;

import com.returnorder.portal.client.ComponentClient;
import com.returnorder.portal.dto.PaymentStatusDTO;
import com.returnorder.portal.model.ProcessRequest;
import com.returnorder.portal.model.ProcessResponse;

import com.returnorder.portal.service.ProcessRequestServiceImpl;

@Controller
@Slf4j
public class ProcessController {

	@Autowired
	private ProcessRequestServiceImpl processRequestServiceImplObj;
	@Autowired
	ComponentClient componentClient;
	@Autowired
	ProcessResponse processResponse;
	@Autowired
	AuthClient authClient;

	PaymentStatusDTO paymentStatusDTO = new PaymentStatusDTO();

	@GetMapping("/order")
	public ModelAndView showProcessing() {
		ModelAndView mv = new ModelAndView("orderDetails");
		mv.addObject("model", new ProcessRequest());
		return mv;
	}

	@PostMapping("/order")
	public ModelAndView performLogin(@Valid @ModelAttribute("model") ProcessRequest model, BindingResult result,
			HttpServletRequest request) throws FeignException {
		ModelAndView mv = new ModelAndView("orderDetails");
		if (result.hasErrors()) {
			return mv;
		}
		try {
			processResponse = processRequestServiceImplObj.processRequestSaveService(model,
					(String) request.getSession().getAttribute("token"));

			mv.addObject("response", processResponse);

			paymentStatusDTO = processRequestServiceImplObj.statusDetails(model,
					(String) request.getSession().getAttribute("token"));
			mv.addObject("payment", paymentStatusDTO);
			log.info(model.toString());

			mv.setViewName("cart");
			return mv;

		} catch (Exception e) {
			mv.setViewName("cart");
			return mv;

		}

	}

	@GetMapping("/track")
	public String track(@RequestParam(name = "id", required = true) String id, Model model) {

		String d = componentClient.trackRequest(id);

		log.info("date:{}", d);
		model.addAttribute("dateofdelivery", d);
		return "track";
	}

	@GetMapping("/home")
	public String home(Model model) {

		return "home";
	}

	@GetMapping("/trackPage")
	public String trackPage(Model model) {

		return "trackrequest";
	}

	@PostMapping("/cancel")
	public String cancel(@RequestParam(name = "id", required = true) String id,
			@RequestParam(name = "creditCardNumber", required = true) String creditCardNumber, Model model) {

		String d = componentClient.cancelRequest(id, creditCardNumber);

		log.info("Response:{}", d);
		model.addAttribute("RESPONSE", d);
		return "cancel";
	}

	@GetMapping("/cancelPage")
	public String cancelRequest(Model model) {

		return "cancelrequest";
	}

}
