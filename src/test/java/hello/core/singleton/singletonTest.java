package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class singletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void pureContainer(){
        AppConfig appconfig=new AppConfig();
        MemberService memberService1=appconfig.memberService();
        MemberService memberService2=appconfig.memberService();
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singleton(){
        SingletonService singletonService1=SingletonService.getInstance();
        SingletonService singletonService2=SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }
}
