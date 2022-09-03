package com.alinlis.test02.controller;


import com.alinlis.test02.entity.RespPageBean;
import com.alinlis.test02.entity.Sort;
import com.alinlis.test02.service.SortService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2022-05-19
 */
@RestController
@RequestMapping("/sort")
public class SortController {
    @Autowired
    SortService sortService;
    @PostMapping("/getall")
    @ApiOperation(value = "查所有分类")
    public RespPageBean getAllbypage(@RequestParam Integer currentPage, @RequestParam Integer size){
        return sortService.getbypage(currentPage,size);
    }
}
