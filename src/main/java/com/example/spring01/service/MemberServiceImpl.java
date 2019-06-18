package com.example.spring01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring01.model.dao.MemberDAO;
import com.example.spring01.model.dto.MemberDTO;

// service
// 
@Service
public class MemberServiceImpl implements MemberService {

	@Inject // 스프링 컨테이너가 만든 dao 객체가 연결됨(의존관계 주입) 
	MemberDAO memberDao;
	
	@Override
	public List<MemberDTO> memberList() {
		return memberDao.memberList();
	}

	@Override
	public void insertMember(MemberDTO dto) {
		memberDao.insertMember(dto);
	}

	@Override
	public MemberDTO viewMember(String userid) {
		return memberDao.viewMember(userid); 
	}

	@Override
	public void deleteMember(String userid) {
		memberDao.deleteMember(userid);
	}

	@Override
	public void updateMember(MemberDTO dto) {
		memberDao.updateMember(dto); 
		//controller에서 보낸 값
	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		return memberDao.checkPw(userid, passwd);
	}

}
