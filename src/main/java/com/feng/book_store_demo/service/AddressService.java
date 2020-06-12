package com.feng.book_store_demo.service;

import com.feng.book_store_demo.form.AddressForm;
import com.feng.book_store_demo.vo.AddressVO;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/6/5 15:59
 */
public interface AddressService {
    List<AddressVO> findAll();
    void saveOrUpdate(AddressForm addressForm);
    void delete(Integer id);
}
