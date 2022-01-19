package hello.hellospring2.service;

import hello.hellospring2.domain.Member;
import hello.hellospring2.repository.MemberRepository;
import hello.hellospring2.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;

    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복회원 X

            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member); //통과되면 저장
            return member.getId(); //저장된 아이디값을 출력
        }

    private void validateDuplicateMember(Member member) { //중복회원 검증하는 메서드
        memberRepository.findByName(member.getName())
                        .ifPresent(member1 -> {
                             throw new IllegalStateException("이미 존재하는 이름입니다.");
                       });
    }


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);

    }



}



