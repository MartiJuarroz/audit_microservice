package com.example.demo.utils.expiringMap;

public interface ExpirationListener<E> {
    void expired(E expiredObject);
}
