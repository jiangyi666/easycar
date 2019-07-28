package com.itcast.crm.mapper;

import com.itcast.crm.pojo.Customer;
import com.itcast.crm.pojo.QueryVo;

import java.util.List;

public interface CustomerMapper {
    //根据queryVo分页查询数据
    List<Customer> queryCustomerByQueryVo(QueryVo queryVo);
    //根据queryVo查询数据条数
    public int queryCountByQueryVo(QueryVo queryVo);
    //根据id查询客户
    public Customer queryCustomerById(Long id);
    //创建拼车信息
    public void createInfo(Customer customer);
    //注册一个新用户
    public void createNewUser(Customer customer);
    //用来登录的时候用来做登录校验
    public Customer checkLogin(Customer customer);

}
