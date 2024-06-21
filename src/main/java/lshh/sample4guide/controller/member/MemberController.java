package lshh.sample4guide.controller.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lshh.sample4guide.common.library.ApiResponse;
import lshh.sample4guide.domain.member.MemberService;
import lshh.sample4guide.domain.member.dto.MemberAddItemCommand;
import lshh.sample4guide.domain.member.dto.MemberItemView;
import lshh.sample4guide.domain.member.dto.MemberJoinCommand;
import lshh.sample4guide.domain.member.dto.MemberSampleView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add/item")
    public ApiResponse<Integer> addItem(@RequestBody MemberAddItemCommand command){
        memberService.addItem(command);
        return ApiResponse.ok(1);
    }

    @GetMapping("/{id}/items")
    public ApiResponse<List<MemberItemView>> items(@PathVariable Long id){
        return ApiResponse.ok(memberService.findWithItems(id));
    }

    @GetMapping("/all")
    public ApiResponse<Iterable<MemberSampleView>> all(){
        return ApiResponse.ok(memberService.findAll());
    }
}
