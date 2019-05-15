package com.example.commservice.model;

/**
 * @Author : BieFeNg
 * @DATE : 2018/9/6 23:00
 */
public class ResponseEntity {

    private String message;
    private boolean success;
    private Object data;
    private int totalRows;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public static class ResponseEntityUtil{

        public static ResponseEntity success(Object data, String message){
            ResponseEntity entity = new ResponseEntity();
            entity.setSuccess(true);
            entity.setData(data);
            entity.setMessage(message);
            return entity;
        }
    }
}
