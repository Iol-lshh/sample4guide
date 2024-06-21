package lshh.sample4guide.domain.member;

import lombok.extern.slf4j.Slf4j;
import lshh.sample4guide.domain.item.ItemService;
import lshh.sample4guide.domain.item.dto.ItemCreateCommand;
import lshh.sample4guide.domain.member.dto.*;
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
    @Autowired
    private ItemService itemService;

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
        log.info("view: {}", view);
        assertFalse(view.isEmpty());
        assertEquals("BRONZE_1", view.get().grade());
    }

    @Test
    @DisplayName("아이템 추가 성공")
    public void testSuccess_addItem(){
        // given
        MemberJoinCommand memberJoinCommand = new MemberJoinCommand("addItem", "testName");
        memberService.join(memberJoinCommand);
        List<MemberSampleView> all = memberService.findAll();
        Long id = all.stream().filter(v -> v.loginId().equals("addItem")).findFirst().get().id();
        ItemCreateCommand itemCreateCommand = new ItemCreateCommand("addItem");
        itemService.create(itemCreateCommand);

        // when
        MemberAddItemCommand memberAddItemCommand = new MemberAddItemCommand(id, 1L, 1);
        memberService.addItem(memberAddItemCommand);

        // then
        List<MemberItemView> items = memberService.findWithItems(id);
        log.info("items: {}", items);
        assertTrue(items.stream().anyMatch(v -> v.itemName().equals("addItem")));
    }

}
