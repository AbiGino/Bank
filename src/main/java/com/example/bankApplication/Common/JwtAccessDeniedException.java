package com.example.bankApplication.Common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAccessDeniedException extends RuntimeException{

    public JwtAccessDeniedException(String message)
    {
        super(message);
    }
}
