package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    /*
    @Controller / @Service / @Repository 를 선언해놓으면
    스프링이 뜰때 해당 클래스들을 쫙 가져온다. >> 이 방법이 컴포넌트 스캔 방법
     */

    //@Autowired 는
    //해당 서비스 객체에 스프링 빈에 등록되어있는 알맞는 서비스객체를 넣어준다.
    //이런게 DI (Dependency Injection ) 이다
    //구조는 Controller -> Service -> Repository 순서이다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
