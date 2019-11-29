package com.bw.movie.model.core;

public interface DataCall<T> {
    void onSuccess(T t);
    void onFalse(String msg);
}
