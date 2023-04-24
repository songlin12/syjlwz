package com.response;

/**
 * 接口返回参数
 *
 * @param <T>
 */
public class Response<T> {
    private int code;
    private String msg;
    private T resdata;
    private int count;
    private int totalPage;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResdata() {
        return resdata;
    }

    public void setResdata(T resdata) {
        this.resdata = resdata;
    }

    public Response() {
    }

    public Response(T resdata) {
        this.resdata = resdata;
    }

    public static Response success() {
        Response response = new Response();
        response.setCode(200);
        response.setMsg("操作成功");
        return response;
    }

    public static <T> Response<T> success(T resdata, int count, int totalPage) {
        Response<T> response = new Response<>(resdata);
        response.setCode(200);
        response.setMsg("操作成功");
        response.setCount(count);
        response.setTotalPage(totalPage);
        return response;
    }

    public static <T> Response<T> success(T resdata) {
        Response<T> response = new Response<>(resdata);
        response.setCode(200);
        response.setMsg("操作成功");
        return response;
    }

    public static Response error(int code, String msg) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static Response error() {
        Response response = new Response();
        response.setCode(500);
        response.setMsg("服务器错误");
        return response;
    }

}
