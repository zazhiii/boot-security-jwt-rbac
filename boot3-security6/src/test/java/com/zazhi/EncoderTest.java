package com.zazhi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author lixh
 * @since 2025/9/16 17:12
 */
@SpringBootTest
public class EncoderTest {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void testEncoder(){
        System.out.println(encoder.encode("111"));
    }

}
