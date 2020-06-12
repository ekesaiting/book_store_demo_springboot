package com.feng.book_store_demo.controller;

import com.feng.book_store_demo.constant.enums.ResultEnum;
import com.feng.book_store_demo.form.AddressForm;
import com.feng.book_store_demo.service.AddressService;
import com.feng.book_store_demo.vo.AddressVO;
import com.feng.book_store_demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author shf
 * @Date 2020/6/5 22:03
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public ResultVO<List<AddressVO>> list(){
        return new ResultVO<>(ResultEnum.SUCCESS,addressService.findAll());
    }
    @PostMapping("/create")
    public ResultVO<String> create(@Valid @RequestBody AddressForm addressForm){
        addressService.saveOrUpdate(addressForm);
        return new ResultVO<>(ResultEnum.SAVE,null);
    }
    @PutMapping("/update")
    public ResultVO<String> update(@Valid @RequestBody AddressForm addressForm){
        addressService.saveOrUpdate(addressForm);
        return new ResultVO<>(ResultEnum.UPDATE,null);
    }
    @DeleteMapping("/delete/{id}")
    public ResultVO<String> delete(@PathVariable("id") Integer id){
        addressService.delete(id);
        return new ResultVO<>(ResultEnum.DELETE,null);
    }
}
