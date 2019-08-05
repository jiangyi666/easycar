package com.itcast.crm.controller;

import com.itcast.common.utils.Page;
import com.itcast.crm.pojo.Customer;
import com.itcast.crm.pojo.QueryVo;
import com.itcast.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.java2d.pipe.SolidTextRenderer;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //显示用户列表
    @RequestMapping("list")
    public String queryCustomerList(Model model, QueryVo queryVo) {
        //分页查询数据
        Page<Customer> page = customerService.queryCustomerByQueryVo(queryVo);
        //把分页查询的结果放在模型中
        model.addAttribute("page", page);//用来分页的
        //用来数据回显
        model.addAttribute("startAddress", queryVo.getStartAddress());
        model.addAttribute("endAddress", queryVo.getEndAddress());
        model.addAttribute("orderDate", queryVo.getOrderDate());
        return "customer";
    }

    /**
     * 根据orderno订单编号，放回JSON格式的数据
     *
     * @param id
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Customer queryCustomerById(String id) {
        return customerService.queryCustomerById(id);
    }

    /**
     * 用来跳转到创建发布信息界面
     * @return
     */
    @RequestMapping("toCreate")
    public String toCreateInfo() {
        return "createInfo";
    }

    /**
     * 用来跳转到查看个人的发布信息界面
     * @return
     */

    @RequestMapping("toPersonInfo")
    public String toPersonInfo(Model model,HttpServletRequest request){
        //从session中拿取之前登陆成功存储的客户编号
        Long customerno = (Long)request.getSession().getAttribute("customerNo");
//        System.out.println(customerno);
        List<Customer> list = customerService.queryPersonalInfoByCno(customerno);
        //将已发布的信息回显给前端
        model.addAttribute("list",list);
        return "personalInfo";
    }

    /**
     * 用来跳转到主界面，即查看和查询所有发布信息界面
     * @return
     */
    @RequestMapping("toCustomer")
    public String toCustomer(){
        return "customer";
    }


    @ResponseBody
    @RequestMapping(value = "createInfo")
    public String createInfo(Customer customer, HttpServletRequest request) {
//        利用时间+随机数来生成唯一的订单编号
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        String orderno=newDate+result;
        customer.setOrderno(orderno);
        long customerNo = (long)request.getSession().getAttribute("customerNo");
        customer.setCustomerno(customerNo);
        customerService.createInfo(customer);
        return "OK";
    }

    /**
     * 修改已经发布出的一条拼车信息
     * 根据订单编号orderno
     * @param customer
     * @return
     */
    @RequestMapping("updateInfo")
    @ResponseBody
    public String updateInfo(Customer customer){
        customerService.updateInfo(customer);
        return "OK";
    }

    /**
     * 用来根据订单编号来删除已经发布的拼车信息
     * @param orderNo
     * @return
     */

    @ResponseBody
    @RequestMapping("deleteInfo")
    public String deleteInfo(String orderNo){
        customerService.deleteInfo(orderNo);
        return "OK";
    }
    /*
    @ResponseBody
    @RequestMapping(value = "delete")
    public String deleteCustomerById(Long id){
        customerService.deleteCustomerById(id);
        return "ok";
    }*/
}
