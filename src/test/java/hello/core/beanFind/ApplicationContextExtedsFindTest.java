package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtedsFindTest {
    ApplicationContext ac=new AnnotationConfigApplicationContext(Config.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 지삭이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByparent(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()-> ac.getBean(DiscountPolicy.class));

    }

    @Test
    @DisplayName("부모 타입으로 조회시, 지삭이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByparent2(){
        DiscountPolicy bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);

    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanByparent3(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);

    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findBeanByparent4(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key);
            System.out.println("beansOfType = " + beansOfType);
            System.out.println("beansOfType.get(key) = " + beansOfType.get(key));
        }

    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기-object")
    void findBeanByparent5(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key);
            System.out.println("beansOfType = " + beansOfType);
            System.out.println("beansOfType.get(key) = " + beansOfType.get(key));
        }

    }

    @Configuration
    static class Config {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

}
