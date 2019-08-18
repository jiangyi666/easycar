package com.itcast.crm.controller;

import com.itcast.crm.pojo.FeedBack;
import com.itcast.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("feedBack")
public class FeedBackController {
    @Autowired
    CustomerService customerService;

    /**
     * 去反馈界面
     * @return
     */
    @RequestMapping("toFeedBack")
    public String toFeedBack(){
        return "feedBack";
    }

    /**
     * 提交反馈
     * @param feedBack
     * @return
     */
    @RequestMapping("createFeedBack")
    @ResponseBody
    public String createFeedBack(FeedBack feedBack){
        customerService.createFeedBack(feedBack);
        return "OK";
    }

}
