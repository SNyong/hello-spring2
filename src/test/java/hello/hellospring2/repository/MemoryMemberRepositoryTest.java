package hello.hellospring2.repository;

import hello.hellospring2.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
// 이게 있어야 assertThat 사용가능

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //테스트가 하나 끝나면 작동하는것
    public void afterEach(){
        repository.clearStore(); //repository에 clearSoter메서드 실행
    }



    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
//        member가 result 랑 같은지 비교

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
            //member1 일때 중복되면 쉬프트+f6 하면 전체 바꾸기 가능
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
        // result = spring1 과 member1의 셋네임과 같은지 비교


    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

    List<Member> result = repository.findAll();

    assertThat(result.size()).isEqualTo(2);
        //모든 값을 찾아오는데 찾은 값의 사이즈가 2가 맞는지 확인(2개)

    }




}
