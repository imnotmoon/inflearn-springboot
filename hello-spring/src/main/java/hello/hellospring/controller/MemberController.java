package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


/**
 * Controller : 외부 요청을 받음
 */
@Controller
public class MemberController {

    private final MemberService memberService;

    /**
     * 스프링 빈 : 컨테이너
     * Spring Container에 있는 MemberService를 가져와서 알아서 연결시켜줌
     * Autowired : 컨트롤러와 서비스를 연결. Dependency injection
     */

    // 생성자를 통해 주입하는게 요즘 권장되는 방식.
    // 방법 3가지 : field 주입, setter 주입(public하게 노출되고 다시바꿀필요가 굳이 없어서 잘안씀)
    // 생성자 주입 : 조립시점에 한번만 주입 -> 변경도 안되고 public하지 않음
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
