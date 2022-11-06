package org.system.vip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.system.vip.common.RedisUtils;

/**
 * 替换cookie控制台
 * @Author lz
 * @Date 2022/11/4 18:27
 * @Version 1.0
 */
@Controller
@RequestMapping("/xiaoguai")
public class AdminController {
    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping("/system")
    public ModelAndView system(ModelAndView modelAndView) {
//        (String) redisUtils.get("qqcookie");
        modelAndView.addObject("migucookie",(String) redisUtils.get("migucookie"));
        modelAndView.addObject("migubycookie",(String) redisUtils.get("migubycookie"));
        modelAndView.addObject("qqcookie",(String) redisUtils.get("qqcookie"));
        modelAndView.setViewName("system.html");
        return modelAndView;
    }

    @RequestMapping("/setSystem")
    public String setSystem(String qqcookie,String migucookie,String migubycookie) {
        if (qqcookie != null&&(!qqcookie.equals(""))) {

            redisUtils.set("qqcookie",qqcookie,0L);
        }
        if (migucookie != null&&(!migucookie.equals(""))) {
            redisUtils.set("migucookie",migucookie,0L);
        }
        if (migubycookie != null&&(!migubycookie.equals(""))) {
            redisUtils.set("migubycookie",migubycookie,0L);
        }
        return "redirect:system";
    }

}
