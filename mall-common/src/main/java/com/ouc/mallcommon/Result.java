package com.ouc.mallcommon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_AUTH_ERROR = 401;
    public static final int CODE_SYS_ERROR = 500;

    private int code;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(CODE_SUCCESS,"请求成功",null);
    }

    public static Result success(Object data){
        return new Result(CODE_SUCCESS,"请求成功",data);
    }

    public static Result authError(int code, String msg){
        return new Result(CODE_AUTH_ERROR,"请重新登录",null);
    }

    public static Result result(int code, String msg, Object data){
        return new Result(code,msg,data);
    }

}
