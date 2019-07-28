package com.itcast.crm.service;

import com.itcast.common.utils.Page;
import com.itcast.crm.pojo.Customer;
import com.itcast.crm.pojo.QueryVo;

public interface CustomerService {
    //根据条件分页查询用用户
    public Page<Customer> queryCustomerByQueryVo(QueryVo queryVo);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    public Customer queryCustomerById(Long id);
    //创建拼车信息
    public void createInfo(Customer customer);
    //注册一个新用户
    public void createNewUser(Customer customer);
    //用来登录的时候用来做登录校验
    public Customer checkLogin(Customer customer);
}
