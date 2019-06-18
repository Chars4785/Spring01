package com.example.spring01.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.model.dto.ProductDTO;

@Controller
public class MainController{
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping("/") //get,post 방식 중요하지 않다.,메소드 이름 문제 없읍
	public String main(Model model) {
		//model 은
		model.addAttribute("message","환영");
		
		return "main";
	}
	
	
	@RequestMapping(value="gugu.do",method = RequestMethod.GET)
	public String gugu(int dan, Model model) {
		
		String result="";
		for(int i =0;i<=9;i++) {
			result += dan +"x"+i+"="+dan*i+"<br>";
		}
		model.addAttribute("result",result);
		// request.setAttribute("list",list); 와 같은 뜻이다.
		return "test/gugu"; //servelet-context에서 따로 정의해 준다.
	}
	
	@RequestMapping(value="test.do")
	public void test() {
		
	}
	
	@RequestMapping(value="test/doA") //doA를 누르게 되면 doB를 부르게 된다.하지만 url은 변하시 않는다.
	public String doA(Model model) {
		logger.info("doA called");
		return "test/doB";
	}
	
	@RequestMapping(value="test/doB")
	public String doB(Model model) {
		logger.info("doB called");
		return "test";
	}
	
	@RequestMapping("test/doC")
	public ModelAndView doC() {
		Map<String,Object> map = new HashMap<>();
		map.put("product",new ProductDTO("샤프",1000));
		return new ModelAndView("test/doC","map",map);	
	}
	
	@RequestMapping("test/doD")
	public String doD() {
		// 포워드는 데이터를 보낼수 있고
		// jsp 페이지로 보내는 것이고, 주소는 안바뀐다.
		// 리다이렉트는 데이터를 보내는 것이 아니고 페이지를 바꾼다.
		return "redirect:/test/doE";
	}
	
	@RequestMapping("test/doE")
	public void doE(){
		// 아무것도 없으면 doE.jsp가 발동 된다.
	}
	
}
