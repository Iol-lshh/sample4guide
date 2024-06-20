package lshh.sample4guide.domain.member.dto;

import lshh.sample4guide.domain.member.Member;

import java.util.List;

public record MemberSampleView(
        Long id,
        String loginId,
        String name
){
    public static List<MemberSampleView> of(List<Member> list) {
        return list.stream()
                .map(MemberSampleView::of)
                .toList();
    }
    public static MemberSampleView of(Member member) {
        return new MemberSampleView(
                member.id(),
                member.loginId(),
                member.name()
        );
    }
}
