package com.menu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class MenuProjectException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;


    public MenuProjectException(String message)
    {
        super(message);
    }

}
