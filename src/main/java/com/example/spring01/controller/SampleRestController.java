package com.example.spring01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring01.model.dto.ProductDTO;

@RestController //스프링 4.0 부터 사용 가능
public class SampleRestController {
	//RestController는 쓰임새가 조금 다랃.

	//responseBody 는  json 형식 리턴 이라는 것
	@ResponseBody //그냥 Controller라고 써있으면 적어줘야 한다.
	@RequestMapping("test/doF")
	public ProductDTO doF() {
		return new ProductDTO("냉장고",500000);
		// 뷰가 아니고 데이터를 넘긴다.
	}
}
