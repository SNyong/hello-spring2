package hello.hellospring2;
import hello.hellospring2.aop.TimeTraceAop;
import hello.hellospring2.repository.*;
import hello.hellospring2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSourece;

//    @Autowired
//    public SpringConfig(DataSource dataSourece) {
//        this.dataSourece = dataSourece;
//    }
//    jdbc 에서 사용

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
//    //Jpa에서 사용

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //스프링 데이터 jpa 에서 사용

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        //스프링 데이터jpa 제외한 곳에서 사용
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return timeTraceAop();
//    }



//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSourece);
//        return new JdbcTemplateMemberRepository(dataSourece);
//        return new JpaMemberRepository(em);
//    }
    // 스프링 데이터jpa에서 사용 안함
}