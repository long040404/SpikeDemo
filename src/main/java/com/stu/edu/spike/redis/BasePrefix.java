/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.stu.edu.spike.redis;

public abstract class BasePrefix implements KeyPrefix{

    private int expireSeconds;
    private String prefix;

    public int expireSeconds() {// 0 代表永远不过期
        return expireSeconds;
    }

    public BasePrefix(String prefix){
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public String getPrefix() {
        String className = getClass().getSimpleName();
        StringBuffer buffer = new StringBuffer();
        buffer.append(className).append(":").append(prefix);
        return buffer.toString();
    }
}
