package com.itmark.mypasswdbackend.entity.resp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: 马宽
 * @Date: 2022/7/2 14:01
 */
public class MarkAppRespEntity<T> implements Serializable {
    private static final long serialVersionUID = -769083935130498630L;
    private int status;
    private String message;
    private String stackTrace;
    private Map<String, T> data = new HashMap();

    public MarkAppRespEntity() {
        this.status = 200;
        this.message = "操作成功";
    }

    public MarkAppRespEntity(T data) {
        this.status = 200;
        this.message = "操作成功";
        this.data.put("result", data);
    }

    public MarkAppRespEntity(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public MarkAppRespEntity(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data.put("result", data);
    }

    public MarkAppRespEntity(int status, String message, String stackTrace, T data) {
        this.status = status;
        this.message = message;
        this.stackTrace = stackTrace;
        this.data.put("result", data);
    }

    public static MarkAppRespEntity<String> success() {
        MarkAppRespEntity<String> out = new MarkAppRespEntity();
        out.status = 200;
        out.message = "操作成功";
        out.data.put("result", "{}");
        out.message = "操作成功";
        return out;
    }

    public static MarkAppRespEntity<String> error() {
        MarkAppRespEntity<String> out = new MarkAppRespEntity();
        out.status = 400;
        out.message = "操作失败";
        out.data.put("result", "{}");
        return out;
    }

    public static <T> MarkAppRespEntity<T> success(T data) {
        MarkAppRespEntity<T> out = new MarkAppRespEntity();
        out.status = 200;
        out.message = "操作成功";
        out.data.put("result", data);
        return out;
    }

    public static MarkAppRespEntity<String> alert() {
        MarkAppRespEntity<String> out = new MarkAppRespEntity();
        out.status = 300;
        out.message = "操作失败";
        out.data.put("result", "{}");
        return out;
    }

    public static <T> MarkAppRespEntity<T> alert(T data) {
        MarkAppRespEntity<T> out = new MarkAppRespEntity();
        out.status = 300;
        out.message = "操作失败";
        out.data.put("result", data);
        return out;
    }

    public static <T> MarkAppRespEntity<T> error(T data) {
        MarkAppRespEntity<T> out = new MarkAppRespEntity();
        out.status = 400;
        out.message = "操作失败";
        out.data.put("result", data);
        return out;
    }

    public MarkAppRespEntity<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public MarkAppRespEntity<T> status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public MarkAppRespEntity<T> stackTrace(String stackTrace) {
        this.setStackTrace(stackTrace);
        return this;
    }

    //public T getActualData() {
    //    if (this.getStatus() != 200) {
    //        return null;
    //    } else {
    //        return this.getData() == null ? null : this.data.get("result");
    //    }
    //}

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStackTrace() {
        return this.stackTrace;
    }

    public Map<String, T> getData() {
        return this.data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public void setData(Map<String, T> data) {
        this.data = data;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MarkAppRespEntity)) {
            return false;
        } else {
            MarkAppRespEntity<?> other = (MarkAppRespEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getStatus() != other.getStatus()) {
                return false;
            } else {
                label49: {
                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message == null) {
                            break label49;
                        }
                    } else if (this$message.equals(other$message)) {
                        break label49;
                    }

                    return false;
                }

                Object this$stackTrace = this.getStackTrace();
                Object other$stackTrace = other.getStackTrace();
                if (this$stackTrace == null) {
                    if (other$stackTrace != null) {
                        return false;
                    }
                } else if (!this$stackTrace.equals(other$stackTrace)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof MarkAppRespEntity;
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + this.getStatus();
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $stackTrace = this.getStackTrace();
        result = result * 59 + ($stackTrace == null ? 43 : $stackTrace.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        int var10000 = this.getStatus();
        return "MarkAppRespEntity(status=" + var10000 + ", message=" + this.getMessage() + ", stackTrace=" + this.getStackTrace() + ", data=" + this.getData() + ")";
    }
}
