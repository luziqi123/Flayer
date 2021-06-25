package com.longface.jhttp;

interface NextRequest<T> {

    public Request run(T data);
}
