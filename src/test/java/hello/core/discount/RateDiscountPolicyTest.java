package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip == 10%")
    void vip_o(){
        Member member = new Member(1L, "memberA", Grade.VIP);
        int discount= rateDiscountPolicy.discount(member,10000);
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("basic == 0%")
    void vip_x(){
        Member member = new Member(1L, "memberA", Grade.BASIC);
        int discount= rateDiscountPolicy.discount(member,10000);
        assertThat(discount).isEqualTo(0);
    }

}