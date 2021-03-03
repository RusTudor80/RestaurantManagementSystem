package Business;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

  
	private static final long serialVersionUID = 1L;
	private Integer id;
    private Date date;
    private Integer table;

    public Order(Integer id, Date date, Integer table) {
        this.id = id;
        this.date = date;
        this.table = table;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date) && Objects.equals(table, order.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, table);
    }
}
