package com.alinlis.test02.controller;


import com.alinlis.test02.entity.Collect;
import com.alinlis.test02.entity.RespBean;
import com.alinlis.test02.entity.TArticles;
import com.alinlis.test02.service.CollectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    CollectService collectService;
    @PostMapping("/add")
    @ApiOperation(value = "添加收藏")
    public RespBean add(@RequestParam String aid, String uid){
        Long articleid=Long.valueOf(aid);
        Long userid=Long.valueOf(uid);
        System.out.println(aid);
        System.out.println(uid);
        Collect collect =new Collect();
        collect.setArticleId(articleid);
        collect.setUserId(userid);
        collectService.add(collect);
        return RespBean.success("收藏成功");
    }
    @PostMapping("/selectbyid")
    @ApiOperation(value = "按用户id查询")
    public List<TArticles> selectbyuid(@RequestBody String id) throws UnsupportedEncodingException {
        String string = URLDecoder.decode( id ,"utf-8");
        string=string.replace("=", "");
        Long uid=Long.valueOf(string);
        return collectService.selectbyid(uid);
    }
}
