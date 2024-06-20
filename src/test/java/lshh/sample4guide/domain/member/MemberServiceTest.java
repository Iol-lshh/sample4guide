package lshh.sample4guide.domain.member;

import lshh.sample4guide.common.library.clock.ClockManager;
import lshh.sample4guide.domain.member.component.MemberRepository;
import lshh.sample4guide.domain.member.dto.MemberJoinCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository repository;
    
    @Mock
    private ClockManager clockManager;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("회원 가입 성공")
    void testSuccess_join() {
        MemberJoinCommand command = new MemberJoinCommand("joinSuccess", "testName");
        LocalDateTime now = LocalDateTime.of(2024, 6, 20, 13, 8, 15);
        when(clockManager.now()).thenReturn(now);

        memberService.join(command);

        Member member = command.toEntity(now);
        verify(repository, times(1)).save(any(Member.class));
    }

}