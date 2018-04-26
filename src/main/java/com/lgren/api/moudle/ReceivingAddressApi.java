package com.lgren.api.moudle;

import com.lgren.pojo.dto.ReceivingAddressDTO;
import com.lgren.pojo.dto.UserDTO;
import com.lgren.pojo.po.ReceivingAddress;
import com.lgren.pojo.vo.ReceivingAddressVO;
import com.lgren.service.ReceivingAddressService;
import com.lgren.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReceivingAddressApi {//OK
    @Autowired
    private Mapper mapper;
    @Autowired
    private ReceivingAddressService receivingAddressService;
    @Autowired
    private UserService userService;
    //查询所有DTO
    public List<ReceivingAddressDTO> getAllReceivingAddressDTO() {
        List<ReceivingAddressDTO> receivingAddressDTOList = receivingAddressService.selectAll()
                .stream()
                .map(s -> mapper.map(s,ReceivingAddressDTO.class))
                .collect(Collectors.toList());
        return receivingAddressDTOList;
    }
    //查询所有VO
    public List<ReceivingAddressVO> getAllReceivingAddressVO() {
        List<ReceivingAddress> receivingAddressList = receivingAddressService.selectAll();
        List<ReceivingAddressVO> receivingAddressVOList = receivingAddressList.stream()
                .map(receivingAddress -> getReceivingAddressVO(receivingAddress))
                .collect(Collectors.toList());
        return receivingAddressVOList;
    }
    //查询根据userId查询ReceivingAddressVOList
    public List<ReceivingAddressVO> getReceivingAddressVOListByUserId(Long userId) {
        List<ReceivingAddress> receivingAddressList = receivingAddressService.getReceivingAddressByUserId(userId);
        List<ReceivingAddressVO> receivingAddressVOList = receivingAddressList.stream()
                .map(receivingAddress -> getReceivingAddressVO(receivingAddress))
                .collect(Collectors.toList());
        return receivingAddressVOList;
    }
    //根据主键ID查询
    public ReceivingAddressVO getReceivingAddressById(Long receivingAddressId) {
        ReceivingAddress receivingAddress = receivingAddressService.selectByPrimaryKey(receivingAddressId);
        return getReceivingAddressVO(receivingAddress);
    }
    //添加
    public boolean addReceivingAddress(ReceivingAddress receivingAddress) {
        return receivingAddressService.insertSelective(receivingAddress) > 0 ? true : false;
    }
    //通过主键ID删除
    public boolean deleteReceivingAddressById(Long receivingAddressId) {
        return receivingAddressService.deleteByPrimaryKey(receivingAddressId) > 0 ? true : false;
    }
    //通过传输一个ReceivingAddress对象修改
    public boolean updateReceivingAddressById(ReceivingAddress receivingAddress) {
        return receivingAddressService.updateByPrimaryKeySelective(receivingAddress) > 0 ? true : false;
    }

    private List<ReceivingAddressVO> getReceivingAddressVOList(List<ReceivingAddress> receivingAddressList) {
        List<ReceivingAddressVO> receivingAddressVOList = receivingAddressService.selectAll()
                .stream()
                .map(receivingAddress -> getReceivingAddressVO(receivingAddress))
                .collect(Collectors.toList());
        return receivingAddressVOList;
    }
    private ReceivingAddressVO getReceivingAddressVO(ReceivingAddress receivingAddress) {
        ReceivingAddressVO receivingAddressVO = mapper.map(receivingAddress,ReceivingAddressVO.class);
        UserDTO userDTO = mapper.map(userService.selectByPrimaryKey(receivingAddress.getUserId()),UserDTO.class);
        receivingAddressVO.setUserDTO(userDTO);
        return receivingAddressVO;
    }

}
