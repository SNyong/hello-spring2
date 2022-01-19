package hello.hellospring2.service;

import hello.hellospring2.domain.Member;
import hello.hellospring2.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    // 각 테스트를 실행하기전에 우선 실행
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        // 메모리멤버 리포지토리를 만들고
        memberService = new MemberService(memberRepository);
        // 멤버 서비스에 멤버리포지토리를 넣어줌
    }
    //이걸 함으로써 같은 멤버리포지토리를 사용함

    @AfterEach
    // 한개의 테스트가 끝나고 실행
    public void afterEach(){
        memberRepository.clearStore();
        //저장된 값을 날려줌
    }

    @Test
    void 회원가입() {
        //given  무언가가 주어졌을때
            Member member = new Member();
            member.setName("spring");
        
        
        //when  이것을 실행했을때
        Long saveId = memberService.join(member);
        //then 결과가 이것이 나와야한다.

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
    //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
    //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
//        try {
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e) {
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
//        }


    //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}