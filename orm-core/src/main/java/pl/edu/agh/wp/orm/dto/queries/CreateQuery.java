package pl.edu.agh.wp.orm.dto.queries;

import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class CreateQuery extends DBQuery{
    private String tableName;

    public CreateQuery(String table){
        super();
        tableName = table;
    }

    @Override
    public void doStartQuery(){
        super.doStartQuery();
        appendWithSpace(DatabaseStatement.CREATE);
        appendWithSpace(DatabaseStatement.TABLE);
        appendWithSpace(tableName);
        append("(");
    }

    @Override
    public void doEndQuery(){
        super.doEndQuery();
        append(");");
    }

}
