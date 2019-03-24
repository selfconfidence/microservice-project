package ml.weiyan.result;

import java.util.List;

/**
 * @author misterWei
 * @create 2019年03月24号:18点49分
 * @mailbox mynameisweiyan@gmail.com
 */
public class ResponsePageEntity<T> {
    private Long total;
    private List<T> rows;

    public ResponsePageEntity(Long total, List<T> rows) {
        super();
        this.total = total;
        this.rows = rows;

    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
