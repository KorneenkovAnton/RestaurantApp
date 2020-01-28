package com.example.myapplication.async;

import lombok.Data;

@Data
public class AsyncTaskResult<T> {
    private T result;
    private int status;
    private Exception exception;

    public AsyncTaskResult(T result, int status) {
        this.result = result;
        this.status = status;
    }

    public AsyncTaskResult(Exception exception) {
        this.exception = exception;
    }

    public AsyncTaskResult() {
    }
}
