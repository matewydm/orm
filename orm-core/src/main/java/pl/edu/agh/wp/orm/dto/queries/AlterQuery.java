package pl.edu.agh.wp.orm.dto.queries;


import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class AlterQuery extends DBQuery {
    private String tableName;

    public AlterQuery(String table) {
        super();
        tableName = table;
    }

    @Override
    public void doStartQuery() {
        super.doStartQuery();
        appendWithSpace(DatabaseStatement.ALTER);
        appendWithSpace(DatabaseStatement.TABLE);
        appendWithSpace(tableName);
    }

    @Override
    public void doEndQuery() {
        super.doEndQuery();
        append(";");
    }
}