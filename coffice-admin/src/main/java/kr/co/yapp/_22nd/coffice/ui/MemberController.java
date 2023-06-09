package kr.co.yapp._22nd.coffice.ui;

import kr.co.yapp._22nd.coffice.domain.member.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberQueryService memberQueryService;

    @GetMapping
    public String list(
            @PageableDefault Pageable pageable,
            Model model
    ) {
        model.addAttribute("memberPage", memberQueryService.findAll(pageable));
        return "member/list";
    }

    @GetMapping("/{memberId}")
    public String detail(
            @PathVariable Long memberId,
            Model model
    ) {
        model.addAttribute("member", memberQueryService.getMember(memberId));
        return "member/detail";
    }

}
