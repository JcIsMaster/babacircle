package com.example.babacircle;

import com.example.babacircle.common.utils.SHA1Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BabacircleApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(SHA1Util.encode("123456"));
    }

}
