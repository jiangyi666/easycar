package com.itcast.crm.mapper;

import com.itcast.crm.pojo.Customer;
import com.itcast.crm.pojo.FeedBack;
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
    //根据唯一的邮箱号来修改密码
    public void updatePassword(Customer customer);
    //根据用户编号来获取用户的个人信息
    //由于登录成功以后用户名保存在session中，故只要获得qq号码，手机号码，微信号码就可以，邮箱
    public Customer getPrivateInfo(long customerNo);
    //根据客户（用户）编号来修改用户的个人信息（手机号，微信号，qq号)
    public void updatePrivateInfo(Customer customer);
    //用来反馈
    public void createFeedBack(FeedBack feedBack);
}
