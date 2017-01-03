package pl.edu.agh.wp.orm.criterion.queries;

import java.util.ArrayList;

public abstract class Criterion {

    private StringBuilder queryBuilder;

    private ArrayList<Object> queryArguments;

    protected String operator;

    protected String column;

    public Criterion() {
        queryBuilder = new StringBuilder();
        queryArguments = new ArrayList<>();
    }

    public abstract void buildQuery();

    public String toSqlQuery() {
        return queryBuilder.toString();
    }

    public void appendWithSpace(String string){
        queryBuilder.append(string);
        queryBuilder.append(" ");
    }

    public void appendArgument(Object argument){
        queryBuilder.append(argument.toString());
        queryArguments.add(argument.toString());
    }

    public void append(String string) {
        queryBuilder.append(string);
    }

    public String getOperator(){
        return operator;
    }

    public ArrayList<Object> getQueryArguments() {
        return queryArguments;
    }

    protected void handleString(Object value){
        append("'");
        appendArgument(value);
        append("'");
    }


}
