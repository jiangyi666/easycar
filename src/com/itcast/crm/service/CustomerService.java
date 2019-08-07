package com.itcast.crm.service;

import com.itcast.common.utils.Page;
import com.itcast.crm.pojo.Customer;
import com.itcast.crm.pojo.QueryVo;

import java.util.List;

public interface CustomerService {
    //根据条件分页查询用用户(按时间降序)
    public Page<Customer> queryCustomerByQueryVo(QueryVo queryVo);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    public Customer queryCustomerById(String id);
    //创建拼车信息
    public void createInfo(Customer customer);
    //注册一个新用户
    public void createNewUser(Customer customer);
    //用来登录的时候用来做登录校验
    public Customer checkLogin(Customer customer);
    //用来检验邮箱是否已经注册过
    public String checkEmailIsExisted(String email);
    //用来查看个人已发布的信息(按时间降序)
    List<Customer> queryPersonalInfoByCno(Long customerNo);
    //修改发布的拼车信息(根据orderno来)
    public void updateInfo(Customer customer);
    //根据订单编号来删除发布的拼车信息
    public void deleteInfo(String orderNo);
}
