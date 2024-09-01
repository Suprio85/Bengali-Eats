package util;

public class specifyClient implements java.io.Serializable{

    String message;
    Object data;
    public specifyClient(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
