package com.alinlis.test02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Alinlis
 * @Data: 2022/5/7 17:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {
    private  Long total;
    private List<?> data;
}
