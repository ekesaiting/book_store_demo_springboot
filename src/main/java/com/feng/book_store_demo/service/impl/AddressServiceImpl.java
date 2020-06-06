package com.feng.book_store_demo.service.impl;

import com.feng.book_store_demo.constant.enums.AddressEnum;
import com.feng.book_store_demo.entity.BuyerAddress;
import com.feng.book_store_demo.exception.AddressException;
import com.feng.book_store_demo.form.AddressForm;
import com.feng.book_store_demo.repository.BuyerAddressRepository;
import com.feng.book_store_demo.service.AddressService;
import com.feng.book_store_demo.vo.AddressVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author shf
 * @Date 2020/6/5 16:00
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Autowired
    private BuyerAddressRepository addressRepository;
    @Override
    public List<AddressVO> findAll() {
        List<BuyerAddress> addresses = addressRepository.findAll();
        return addresses.stream().map(a -> new AddressVO(
                a.getAddressId(),
                a.getAreaCode(),
                a.getBuyerName(),
                a.getBuyerPhone(),
                a.getBuyerAddress())).collect(Collectors.toList());
    }

    @Override
    public void saveOrUpdate(AddressForm addressForm) {
        BuyerAddress buyerAddress;
        if(addressForm.getId() == null){
            buyerAddress = new BuyerAddress();
        } else {
            Optional<BuyerAddress> addressOpt = addressRepository.findById(addressForm.getId());
            if (!addressOpt.isPresent()){
                log.error("【地址异常】地址不存在");
                throw new AddressException(AddressEnum.ADDRESS_NOT_EXIST);
            }
            buyerAddress = addressOpt.get();
        }
        buyerAddress.setBuyerName(addressForm.getName());
        buyerAddress.setBuyerPhone(addressForm.getTel());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(addressForm.getProvince())
                .append(addressForm.getCity())
                .append(addressForm.getCounty())
                .append(addressForm.getAddressDetail());
        buyerAddress.setBuyerAddress(stringBuilder.toString());
        buyerAddress.setAreaCode(addressForm.getAreaCode());

        addressRepository.save(buyerAddress);
    }
}
