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
 * 빈 등록
 *
 *  @ 가 들어가면 Componet 방식이다 @Component 애노테이션이 있으면 스프링 빈으로 자동 등록된다.
 *  @Controller 컨트롤러가 스프링 빈으로 자동 등록된 이유는 컴포넌트 스캔 때문
 *
 *  실무에서 주로 정형화된 컨트롤러, 서비스 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
 *  그리고 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈을 등록 한다.
 *
 *  예를 들자면 레파지토리 구현체를 db 레파지토리 구현체로 바꾸게 된다면 스프링 빈을 등록 하는 방식으로 가는게 좋다.
 *
 */


@Controller
public class MemberController { // 스프링이 생성 할 때 MemberController 객체를 생성 해서 spring에 넣어준다.
    /**
     * 1. 필드 주입
    @Autowired
    private  MemberService memberService;  
    */

    /**
     * 2. setter 주입
     *
     * 단점 : 아무 개발자나 호출을 할 수 있음
    private  MemberService memberService;
    @Autowired
    public void setMemberService(MemberService memberService) {
    this.memberService = memberService;
    }
     */

    /**
     * 3. 생성자 주입
     * 
     * 생성자 주입이 제일 권장
     */
    
    private  final MemberService memberService;

    // @Autowired 생성자에 관련된 객체를 자동 주입 해준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // url로 들어가게 되면 GetMapping을 통해 createMemberForm.html로 들어가게 된다.
    @GetMapping("/members/new")
    public String creatForm(){
        return "members/createMemberForm";
    }

    // createMemberForm.html 에서 post로 데이터를 넘겨 줄 때 들어온다.
    @PostMapping("/members/new")
    public String creat(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model){

        List<Member> mebers = memberService.findMembers();

        model.addAttribute("members", mebers);

        return "members/memberList";
    }

}

