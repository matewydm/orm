package pl.edu.agh.wp.orm.dto.queries;


import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class AlterQuery extends DBQuery {
    private  String alterTable;
    private String tableName;
    private String columnName;
    public AlterQuery(String table,String column,String alterTable) {
        super();
        tableName = table;
        this.columnName = column;
        this.alterTable = alterTable;
    }

    @Override
    public void doStartQuery() {
        super.doStartQuery();
        appendWithSpace(DatabaseStatement.ALTER);
        appendWithSpace(DatabaseStatement.TABLE);
        appendWithSpace(tableName);
        append("ADD FOREIGN KEY(");
        append(columnName);
        append(")");
        appendWithSpace("REFERENCES");
        append(alterTable);
    }

    @Override
    public void doEndQuery() {
        super.doEndQuery();
        append(";");
    }
}