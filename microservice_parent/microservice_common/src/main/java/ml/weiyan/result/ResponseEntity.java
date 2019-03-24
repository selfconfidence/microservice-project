package ml.weiyan.result;

/**
 * @author misterWei
 * @create 2019年03月24号:18点47分
 * @mailbox mynameisweiyan@gmail.com
 */
public class ResponseEntity {
    private boolean flag;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private Object data;// 返回数据
    public ResponseEntity(boolean flag, Integer code, String message, Object
            data) {
   
        super();        
        this.flag = flag;        
        this.code = code;        
        this.message = message;        
        this.data = data;        
    }    
 public ResponseEntity() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseEntity(boolean flag, Integer code, String message) {
        super();        
        this.flag = flag;        
        this.code = code;        
        this.message = message;

    }
}
