package com.inhatc.auction.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;
}