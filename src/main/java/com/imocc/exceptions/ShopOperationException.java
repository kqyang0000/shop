package com.imocc.exceptions;

public class ShopOperationException extends RuntimeException {

    private static final long serialVersionUID = 3142323989063376323L;

    public ShopOperationException(String msg) {
        super(msg);
    }
}
