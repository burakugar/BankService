package com.example.bankservice.model.converter;

import com.example.bankservice.model.dto.UserDto;
import com.example.bankservice.model.entity.UserEntity;
import com.example.bankservice.model.request.UserAddRequest;
import com.example.bankservice.model.request.WithdrawRequest;

public final class UserConverter {
    private UserConverter() {
        throw new IllegalStateException("Converter Class");
    }


    public static UserDto toUserDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .balance(entity.getBalance())
                .name(entity.getName())
                .build();
    }

    public static UserEntity toUserEntity(UserAddRequest userAddRequest) {
        return UserEntity.builder()
                .balance(userAddRequest.getBalance())
                .name(userAddRequest.getName())
                .build();
    }

}
