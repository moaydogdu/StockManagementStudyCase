package com.study.stockmanagementstudycase.auth.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConfigurationParameter {

    ISSUER("SMSC"),
    AUTH_ACCESS_TOKEN_EXPIRE_MINUTE("30"),
    AUTH_REFRESH_TOKEN_EXPIRE_DAY("1"),
    AUTH_PUBLIC_KEY("""
            -----BEGIN PUBLIC KEY-----
            MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnGPO2vvNZxgcZjWf7TjC
            MXxV0RXywbVQWeicfWcCzbhnpqMdtxgfovIfumYdRvlwUmewtLoFuMRv5y7O2GHz
            uLHwwedj2vS2Ic/6SA1VfU0hZPb/IW8LurAfMBG/42SwVkp59JmQGYpBiRGODtDw
            lWVoxRGr5/s/0CJxuyb6eff5hJAVCPIgiNQDjwC97YwrUy0mUCM2/wrLn+3Vsxfg
            n2TW3BdwZarbYXL2QmRnQTEwDTdJrinH4vo9SHwumTGtwR6A66Ycr5qNWcwlduhC
            bl/qb9For5PH3f5RREb5ljo/yh+KCYUmUD3QkvN3BbnMKhrvbyAtjR+LQwfQZLrZ
            aQIDAQAB
            -----END PUBLIC KEY-----
            """),
    AUTH_PRIVATE_KEY("""
            -----BEGIN PRIVATE KEY-----
            MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCcY87a+81nGBxm
            NZ/tOMIxfFXRFfLBtVBZ6Jx9ZwLNuGemox23GB+i8h+6Zh1G+XBSZ7C0ugW4xG/n
            Ls7YYfO4sfDB52Pa9LYhz/pIDVV9TSFk9v8hbwu6sB8wEb/jZLBWSnn0mZAZikGJ
            EY4O0PCVZWjFEavn+z/QInG7Jvp59/mEkBUI8iCI1AOPAL3tjCtTLSZQIzb/Csuf
            7dWzF+CfZNbcF3BlqtthcvZCZGdBMTANN0muKcfi+j1IfC6ZMa3BHoDrphyvmo1Z
            zCV26EJuX+pv0Wivk8fd/lFERvmWOj/KH4oJhSZQPdCS83cFucwqGu9vIC2NH4tD
            B9BkutlpAgMBAAECggEAShqJE/6oz6b/Hfde9nskmWo/B7oMs3kEv/WW4V0EWRJ+
            GtnD5NRLeVydaCemy9C6RnHXq2tR9sEt/S+S17liUSwP1vGbwU6fVtpD0Axxa9Q2
            dK8NKXyC5E08GfzvHgLCA8F6lEqHm3KFpztoQuECfuf0gxzihiPpkugfV/0aRtxa
            AUiIRDy7a6voE8d536ArUm/094TWNNDQVsUvbO2z5McPku7u18WxD88o+eBKtcou
            j+ihXcgsbKIZwwhMTgiV7HpdLpDtkNMmUN5NAs21rqpx1TLpde2cHqJFwUSF1B0C
            dmVci/XyoIBpiQiR2oaqbNMJjYk6sYle7yNtPkkiAQKBgQDI9fCMuNd0xvm+043W
            gFDQhh0G/ev3XbKwHCIFXIYKJfAZB1jgrhaBVFhkyfh1v9PDLV2XJGgoo428Wjdh
            fw9Mxp4g7ODUf2dlzwzc8a0WOttKFIi1qby/JO0h7GHn7NeHfP03gK54RRUSCB+6
            oSHSfs/YYh7kcQBumxeSe5ru4QKBgQDHONnrCH265+UFNXWYJWmPo19Mv4/aqgqG
            Ut2NexR6LIjNxj9tx7uVvV5sh7Htdxo4PvCe8pqDIUwJYz8V5CnlvivyHY09fW/7
            zP0QBoT7l+A58JJCvJPdLXtDAAf2u4POL1v4me6U7ffGwfGSIG4wNrLkQ81/Km6c
            P3RrEnBjiQKBgBkcShN7YUixODG1A4si+3Yi2+usvNhq99HzofijiAgql2a5OUzR
            0/QTEvx9FDO6ZX6E46XoIa+6SBKZ/0OKeeHxC383uhwzUzswdP4CoMsfTZljGXYB
            oJkNxyeu/sjZGlFOt+FAjgxvLWPUagqK6cwAln4GPK/0wsYPURG3qjNhAoGBAI3F
            qV6W5lKSH+q7BnpFdX+gdXs6f3bOtzqr0bqTMhlXg+tb/1tyXc3Lu1xknHwu29vW
            /ok+gDJ9CY1MsWI0wpQZ51AfM5nkoEKCuDwlR5F7nTfeOVeAt4EYkrU1WnV9L+kU
            ztB1StF1H3S0I+MnbQKmilvi3GR4n8pVZdWkg5JxAoGAdh5hXcBdnnuDSgQkmcZK
            bzUVy1Dk1RpI7FHehGmJ6tP+obyfVMf5O4Oy1Y182Q9ouSUW7sv2MG1gZ7/wHImT
            3kZ1feVxkx6YdPeq2dVmDyjFAy7/1tBXnDQceVD/EdFU0xZ2oqZg8olk7lY24No+
            zaL5XS9/sytsa94UkP9bjzI=
            -----END PRIVATE KEY-----
            """);

    private final String defaultValue;
}
