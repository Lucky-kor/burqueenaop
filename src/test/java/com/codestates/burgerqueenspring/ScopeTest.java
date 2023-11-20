package com.codestates.burgerqueenspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ScopeTest {

    @Test
    public void scopeTest() {

        // 컨테이너 생성
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestBean.class);

        // 컨테이너 사용
        TestBean bean = annotationConfigApplicationContext.getBean(TestBean.class);
        TestBean bean2 = annotationConfigApplicationContext.getBean(TestBean.class);

        System.out.println("bean = " + bean);
        System.out.println("bean2 = " + bean2);

        // 검증
        Assertions.assertThat(bean).isSameAs(bean2);

        // 컨테이너 종료
        annotationConfigApplicationContext.close();
    }

    static class TestBean {

        // 초기화 메서드 설정
        @PostConstruct
        public void init() {
            System.out.println("init() 초기화 메서드 실행.");
        }

        // 종료 메서드 설정
        @PreDestroy
        public void close() {
            System.out.println("close() 종료 메서드 실행.");
        }
    }
}
