package com.example.spring01.model.dao;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.spring01.model.dto.MemberDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" 
})
@WebAppConfiguration
public class MemberDAOTest {

	private static final Logger logger = 
			LoggerFactory.getLogger(MemberDAOTest.class);
	
	@Inject
	MemberDAO memberDao;

	@Test
	public void testMemberList() {
		logger.info("회원목록:"+memberDao.memberList());
	}

	@Test
	public void testInsertMember() {
		MemberDTO dto=new MemberDTO();
		dto.setUserid("user02");
		dto.setPasswd("user00");
		dto.setName("user00");
		dto.setEmail("user00@aaa.com");
		
		memberDao.insertMember(dto);
	}

	@Test
	public void testViewMember() {
		logger.info("dto:"+memberDao.viewMember("user02"));
	}

	@Test
	public void testDeleteMember() {
		memberDao.deleteMember("user02");
	}

	@Test
	public void testUpdateMember() {
		MemberDTO dto=new MemberDTO();
		dto.setUserid("user02");
		dto.setPasswd("1234");
		dto.setName("홍길동");
		dto.setEmail("user00@aaa.com");
		memberDao.updateMember(dto);
	}

	@Test
	public void testCheckPw() {
		logger.info("비밀번호 체크:"+memberDao.checkPw("user02","user00"));
		logger.info("비밀번호 체크:"+memberDao.checkPw("user02","2222"));
	}

}
