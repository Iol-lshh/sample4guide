package lshh.sample4guide.controller.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lshh.sample4guide.common.library.ApiResponse;
import lshh.sample4guide.domain.member.MemberService;
import lshh.sample4guide.domain.member.dto.MemberJoinCommand;
import lshh.sample4guide.domain.member.dto.MemberSampleView;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public ApiResponse<Integer> join(@Valid @RequestBody MemberJoinCommand command){
        memberService.join(command);
        return ApiResponse.ok(1);
    }

    @GetMapping("/all")
    public ApiResponse<Iterable<MemberSampleView>> all(){
        return ApiResponse.ok(memberService.findAll());
    }
}
