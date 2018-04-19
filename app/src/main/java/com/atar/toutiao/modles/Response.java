package com.atar.toutiao.modles;


import com.atar.toutiao.net.retrofitutils.Constant;

/**
 * Created by Sunflower on 2016/1/11.
 */
public class Response<T> {

    public String code;
    public String message;
    public T object;


    public boolean isSuccess() {
        return code.equals(Constant.OK);
    }
}
