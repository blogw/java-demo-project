package com.github.blogw.event4springboot.core;

public class MemoryData {
    public final static ThreadLocal<String> stringData = new ThreadLocal<>();
}
