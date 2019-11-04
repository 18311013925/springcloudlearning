package com.lizhi;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lizhi
 * @Date: 2019/11/4 15:10
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    /**
     * 状态号：
     */
    int status;
    String message;
    Object data;
}
