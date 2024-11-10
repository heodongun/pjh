package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindText {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이사있으면, 중복 오류가 발생한다")
    void find(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상있으면, 빈이름을 지정하면 됩니다.")
    void find2(){
        MemberRepository bean = ac.getBean("discountPolicy1", MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정타입을 조회하기")
    void find3(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String s : beansOfType.keySet()) {
            System.out.println("s = " + s);
            System.out.println("beansOfType = " + beansOfType.get(s));
            System.out.println("beansOfType = " + beansOfType);
            org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
        }

    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository discountPolicy1() {
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository discountPolicy2() {
            return new MemoryMemberRepository();
        }
    }
}
