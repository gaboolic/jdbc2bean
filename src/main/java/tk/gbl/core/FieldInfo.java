package tk.gbl.core;

/**
 * Date: 2018/5/31
 * Time: 上午9:29
 *
 * @author dongtian
 */
public class FieldInfo {
    String columnName;
    String columnType;
    String comment;

    boolean isPk;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPk() {
        return isPk;
    }

    public void setPk(boolean pk) {
        isPk = pk;
    }
}
