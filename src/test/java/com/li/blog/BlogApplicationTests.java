package com.li.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
        String s = "127.0.0.1";
        String ss = "127.0.0.2";
        System.out.println(s.hashCode());
        System.out.println(ss.hashCode());
    }

}
