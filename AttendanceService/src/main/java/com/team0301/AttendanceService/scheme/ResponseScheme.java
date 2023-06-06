package com.team0301.AttendanceService.scheme;

public class ResponseScheme {
    private boolean success;
    private String message;
    public ResponseScheme(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResponseScheme getInstance(boolean success) {
        return new ResponseScheme(success);
    }
    public static ResponseScheme getInstance(boolean success, String message) {
        ResponseScheme res = new ResponseScheme(success);
        res.setMessage(message);
        return res;
    }
}
