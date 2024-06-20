package lshh.sample4guide.domain.member;

import lombok.extern.slf4j.Slf4j;
import lshh.sample4guide.domain.member.dto.MemberJoinCommand;
import lshh.sample4guide.domain.member.dto.MemberWithLevelView;
import lshh.sample4guide.domain.member.dto.MemberSampleView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class MemberServiceDataTest {
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원 가입 성공")
    public void testSuccess_joinWithDb(){
        // given
        MemberJoinCommand memberJoinCommand = new MemberJoinCommand("joinWithDb", "testName");

        // when
        memberService.join(memberJoinCommand);

        // then
        List<MemberSampleView> all = memberService.findAll();
        assertTrue(all.stream().anyMatch(v -> v.loginId().equals("joinWithDb")));
    }

    @Test
    @DisplayName("회원 가입 실패 - 중복된 로그인 아이디")
    public void testFailed_joinWithDbByDuplicatedLoginId(){
        // given
        MemberJoinCommand memberJoinCommand = new MemberJoinCommand("joinWithDbByDuplicatedLoginId", "testName");

        // when
        memberService.join(memberJoinCommand);
        List<MemberSampleView> all = memberService.findAll();
        assertTrue(all.stream().anyMatch(v -> v.loginId().equals("joinWithDbByDuplicatedLoginId")));

        // then
        assertThrows(IllegalArgumentException.class, () -> memberService.join(memberJoinCommand));
    }

    @Test
    @DisplayName("레벨 조회 성공")
    public void testSuccess_findWithLevel(){
        // given
        MemberJoinCommand memberJoinCommand = new MemberJoinCommand("findWithLevel", "testName");
        memberService.join(memberJoinCommand);
        List<MemberSampleView> all = memberService.findAll();
        Long id = all.stream().filter(v -> v.loginId().equals("findWithLevel")).findFirst().get().id();

        // when
        Optional<MemberWithLevelView> view = memberService.findWithLevel(id);

        // then
        assertFalse(view.isEmpty());
        assertEquals("BRONZE_1", view.get().grade());
    }

}
