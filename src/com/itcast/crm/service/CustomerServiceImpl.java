package com.itcast.crm.service;

import com.itcast.common.utils.Page;
import com.itcast.crm.mapper.CustomerMapper;
import com.itcast.crm.pojo.Customer;
import com.itcast.crm.pojo.FeedBack;
import com.itcast.crm.pojo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements  CustomerService{
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Page<Customer> queryCustomerByQueryVo(QueryVo queryVo) {
        // 设置查询条件,从哪一条数据开始查
        queryVo.setStart((queryVo.getPage() - 1) * queryVo.getRows());

        // 查询数据结果集
        List<Customer> list = this.customerMapper.queryCustomerByQueryVo(queryVo);
        // 查询到的数据总条数
        int total = this.customerMapper.queryCountByQueryVo(queryVo);

        // 封装返回的page对象
        Page<Customer> page = new Page<>(total, queryVo.getPage(), queryVo.getRows(), list);

        return page;
    }

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    @Override
    public Customer queryCustomerById(String id) {
        return customerMapper.queryCustomerById(id);
    }

    @Override
    public void createInfo(Customer customer) {
        customerMapper.createInfo(customer);
    }

    @Override
    public void createNewUser(Customer customer) {
        customerMapper.createNewUser(customer);
    }

    @Override
    public Customer checkLogin(Customer customer) {
        return customerMapper.checkLogin(customer);
    }

    @Override
    public String checkEmailIsExisted(String email) {
        return customerMapper.checkEmailIsExisted(email);

    }

    @Override
    public List<Customer> queryPersonalInfoByCno(Long customerNo) {
        return customerMapper.queryPersonalInfoByCno(customerNo);
    }

    @Override
    public void updateInfo(Customer customer) {
        customerMapper.updateInfo(customer);
    }

    @Override
    public void deleteInfo(String orderNo) {
        customerMapper.deleteInfo(orderNo);
    }

    @Override
    public void updatePassword(Customer customer) {
        customerMapper.updatePassword(customer);
    }

    @Override
    public void updatePrivateInfo(Customer customer) {
        customerMapper.updatePrivateInfo(customer);
    }

    @Override
    public Customer getPrivateInfo(long customerNo) {
        return customerMapper.getPrivateInfo(customerNo);
    }

    @Override
    public void createFeedBack(FeedBack feedBack) {
        customerMapper.createFeedBack(feedBack);
    }


}
