package hello.hellospring2.repository;

import hello.hellospring2.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //0부터 숫자를 차례대로 만들어주기위해

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //멤버가 추가될때마다 sequence가 1씩 추가됨
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //스토어에서 꺼내서 id를 반환하는데 null이여도 반환 가능 클라이언트에서 추가조작 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 멤버에서 멤버.겟네임이 파라미터에서 넘어온 네임이랑 같은지 확인
                .findAny(); // 하나라도 찾는거
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //스토어에 멤버들이 전체 반환됨
    }


    public void clearStore(){
        store.clear();

//        store에 저장소, 공용데이터를 클리어함
    }
}
