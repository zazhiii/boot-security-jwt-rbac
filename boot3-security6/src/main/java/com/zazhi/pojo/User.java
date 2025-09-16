package com.zazhi.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *
 * @author lixh
 * @since 2025/9/9 11:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    private String id;
    private String username;
    private String password;
//    private LocalDateTime createTime;
}
