package com.example.spring01.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.spring01.model.dto.MemberDTO;

// Repos => 서버에서 올라올때 이 클래스를 미리 올려둔다. 
// 그러면 우리가 외부에서 이 클래스를 호출할때 
// new 하지 않아도 inject를 이용해서 사용 가능
// model에 많이 쓰는 어노테이션
@Repository
public class MemberDAOImpl implements MemberDAO {

	private static final Logger logger=
			LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// 우리가 만들 필요 없다. 의존 관계를 주입한다.
	// 우리가 직접 짜야 하는 부분에 대한 부담을 덜다.
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<MemberDTO> memberList() {
		logger.info("memberList called..");
		// sql mapper에 작성된 sql 코드가 실행됨 
		// 전에는 
		// List<MemberDTO> list = sqlSession.selectList("member.memberList");
		//sqlSession.close();
		// 지금 close를 하게 되면 error 가 나온다.
		// 또한 sqlSession.commit()도 해줬어야 했는데 
		// 모든 것이 자동으로 된다.
		return sqlSession.selectList("member.memberList");
	}

	@Override
	public void insertMember(MemberDTO vo) {
		sqlSession.insert("member.insertMember",vo);
	}

	@Override
	public MemberDTO viewMember(String userid) {
		return sqlSession.selectOne("member.viewMember",userid);
		// 두개 이상일 경우 selectList
	}

	@Override
	public void deleteMember(String userid) {
		sqlSession.delete("member.deleteMember", userid);
	}

	@Override
	public void updateMember(MemberDTO vo) {
		sqlSession.update("member.updateMember",vo);
		//service에서 보낸 값에 대해서 DB에 저장한다.
	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		boolean result =false;
		Map<String,String> map = new HashMap<>();
		map.put("userid",userid);
		map.put("passwd",passwd); //map에 담아서 넘기던 DTO에 담아서 넘기던
		int count = sqlSession.selectOne("member.checkPw",map);
		// 리턴값이 1이면 true , 0이면 false;
		if(count ==1)
			result =true;
		
		return result;
	}

}
