package com.codestates.burgerqueenspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {

    // 빈 조회 테스트케이스
    @Test
    void findBean() {

        //given => 초기화 또는 테스트에 필요한 입력 데이터
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfigurer.class);

        //when => 테스트 할 동작
        Menu menu = applicationContext.getBean("menu", Menu.class);

        //then => 검증
        Assertions.assertThat(menu).isInstanceOf(Menu.class);
    }
}
