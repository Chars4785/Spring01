package com.example.spring01.controller;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//JUnit4로 테스트
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration // 웹 설정파일의 경로 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" }) // xml 모든 것 참조
public class MainControllerTest {
	// 로깅 처리를 위한 객체 선언
	private static final Logger logger = LoggerFactory.getLogger(MainControllerTest.class);

	@Inject // 의존관계 주입
	WebApplicationContext wac;
	MockMvc mockMvc; // 가상으로 컨트롤러를 테스트하기 위한 객체

	@Before // 테스트 전에 호출되는 코드
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		System.out.println("setup...");
	}

	@Test // JUnit이 테스트하는 코드
	public void testDoA() throws Exception {
		// 뷰가 완성되지 않은 상태에서도 테스트가 가능함
		mockMvc.perform(MockMvcRequestBuilders.get("/test/doA"));
		// mockMvc.perform(MockMvcRequestBuilders.get("/doA"));
		// 없다고 나옴
		logger.info("doA...");
	}

}
