package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ExtraInfo;
import com.example.demo.repositories.ExtraInfoRepo;
import com.example.demo.security.TokenService;
import com.example.demo.services.dto.ListExtraInfoDTO;

@Service
public class ExtraInfoService {

    @Autowired
    ExtraInfoRepo extraInfoRepo;
    @Autowired
    TokenService tokenService;

    @Transactional
    public List<ListExtraInfoDTO> findModuleExtraInfo(String moduleId){
        List<ExtraInfo> info = extraInfoRepo.findAllByModule(moduleId);
        return info.stream().map(inf -> ListExtraInfoDTO.builder()
                .clave(inf.getClave())
                .valor(inf.getValor())
                .build()).collect(Collectors.toList());
    }
    
}