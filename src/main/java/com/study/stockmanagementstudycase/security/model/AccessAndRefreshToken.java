package com.study.stockmanagementstudycase.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessAndRefreshToken {
    private String accessToken;
    private String refreshToken;
}
