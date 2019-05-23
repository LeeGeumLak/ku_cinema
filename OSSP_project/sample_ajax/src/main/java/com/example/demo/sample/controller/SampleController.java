package com.example.demo.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	@GetMapping(value="/sample")
	public String sample() {
		System.out.println("sample");
		return "test";
	}
	@RequestMapping(value="/saveSample", method={RequestMethod.GET, RequestMethod.POST})
	public Map<String,Object> ajax_sample(@RequestParam(required=false) String[] seats,@RequestParam(required=false) Long price) {
		System.out.println("===================");
		if(seats != null && seats.length > 0) {
			System.out.println("선택좌석목록:");
			for(String seat : seats) {
				System.out.println(seat);
			}
		}
		System.out.println("금액:"+price);
		System.out.println("===================");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("data", "test");
		return result;
	}
}
