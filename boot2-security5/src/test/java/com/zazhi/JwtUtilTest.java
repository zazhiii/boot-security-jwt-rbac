package com.zazhi;

import com.zazhi.utils.JwtUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 *
 * @author lixh
 * @since 2025/9/9 21:15
 */
public class JwtUtilTest {

    @Test
    public void testParseJWT() {
        Map<String, Object> map = JwtUtil.parseToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsidXNlcklkIjoiMSIsInVzZXJuYW1lIjoiMTExIn0sImV4cCI6MTc1ODAyODQ5Mn0.h54R8V9ieWqbpk9YNUid_5N4_sn3-gevsOt3EnmSavA");

        System.out.println(map.toString());
    }

}
