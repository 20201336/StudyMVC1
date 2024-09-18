package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberRepositoryTest {
    MemberRepository memberRepository=MemberRepository.getInstance();   //싱글톤이므로 new는 안됨(private)
    //여기서는 싱글톤이 자동 적용이 아니므로 그런거고, 스프링에서는 자동으로 싱글톤이 기본값

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given     주어졌을 때
        Member member=new Member("hello",20);

        //when      실행했을 때
        Member savedMember= memberRepository.save(member);

        //then      결과
        Member findMember=memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result=memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1,member2);
    }
}
