package com.itcast.crm.mapper;

import com.itcast.crm.pojo.Customer;
import com.itcast.crm.pojo.QueryVo;

import java.util.List;

public interface CustomerMapper {
    //根据queryVo分页查询数据(按时间降序)
    List<Customer> queryCustomerByQueryVo(QueryVo queryVo);
    //根据queryVo查询数据条数
    public int queryCountByQueryVo(QueryVo queryVo);
    //根据订单编号来查询该订单得具体信息
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
