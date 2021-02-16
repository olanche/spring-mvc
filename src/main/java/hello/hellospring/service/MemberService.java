package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }
    /*
    * 회원가입
    * */
    public Long join(Member member) {

        validateDuplicateMember(member); //중복된 이름의 회원은 받지 않는다 - 예외처리... 이 줄을 실행하고 메소드가 종료될듯

        memberRepository.save(member);
        return member.getId();

    }



    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m ->
                {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                }
        );
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
