package spring.hello;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.hello.repository.JpaMemberRepository;
import spring.hello.repository.MemberRepository;
import spring.hello.service.MemberService;

@Configuration
public class SpringConfig {

    /* jdbc나 jdbcTemplate 할때 사용.
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        // 다형성 활용
        //return new JdbcMemberRepository(dataSource);
        //return new MemoryMemberRepository();
        // return new JdbcTemplateMemberRespository(dataSource);
        return new JpaMemberRepository(em);
    }
}
