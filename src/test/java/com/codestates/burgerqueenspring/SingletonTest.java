package com.codestates.burgerqueenspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    void singletonTest() {

        //given
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfigurer.class);

        //when
        Cart cart = applicationContext.getBean("cart", Cart.class);
        Cart cart2 = applicationContext.getBean("cart", Cart.class);

        System.out.println("cart2 = " + cart2);
        System.out.println("cart = " + cart);

        //then
        Assertions.assertThat(cart).isSameAs(cart2);
    }
}
