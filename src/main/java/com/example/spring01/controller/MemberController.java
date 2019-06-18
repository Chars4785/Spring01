package com.example.spring01.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring01.model.dto.MemberDTO;
import com.example.spring01.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	MemberService memberService;

	@RequestMapping("member/list.do") // 사용자가 요청하는 주소
	public String memberList(Model model) {
		List<MemberDTO> list = memberService.memberList();
		logger.info("회원 목록:" + list);
		model.addAttribute("list", list); // 모델에 저장
		return "member/member_list"; // 출력 페이지로 포워딩
	}

	@RequestMapping("member/write.do")
	public String write() {
		return "member/write";
		// 귀찮아도 컨트롤러를 돌아서 가라
		// 이렇게 하는 이유는 보안 때문에 그렇다 .
		// web-inf 안에 들어가기 위해서는 controller를 꼭 거쳐야 한다.
		// 그리고 log 남기기가 편하다.
	}

	@RequestMapping("member/insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		// String name = request.getParameter("name");
		// HttpServletRequest request 이런식으로 날아 왔는데
		// @ModelAttribute 폼의 전체 데이터 저장시
		// @RequestParam 폼의 개별값을 저장시
		memberService.insertMember(dto);
		return "redirect:/member/list.do";
		// redirect 를 안붙이면 포워드 된다.
		// 그 페이지로 보낸다.
	}

	@RequestMapping("member/view.do")
	public String view(@RequestParam String userid, Model model) {
		model.addAttribute("dto", memberService.viewMember(userid));
		return "member/view";
	}

	@RequestMapping("member/update.do")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		boolean result = memberService.checkPw(dto.getUserid(), dto.getPasswd());
		logger.info("비밀번호 확인:" + result);

		if (result) { // 비밀번호가 맞으면
			memberService.updateMember(dto); // 레코드 수정
			return "redirect:/member/list.do"; // 목록으로 이동
		} else { // 비밀번호가 틀리면
			MemberDTO dto2 = memberService.viewMember(dto.getUserid());
			dto.setJoin_date(dto2.getJoin_date()); // 날짜가 지워지지 않도록
			model.addAttribute("dto", dto);
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "member/view"; // 수정 페이지로 되돌아감
		}
	}

}
