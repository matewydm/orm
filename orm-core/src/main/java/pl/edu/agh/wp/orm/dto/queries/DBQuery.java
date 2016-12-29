package pl.edu.agh.wp.orm.dto.queries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBQuery {
    private StringBuilder queryBuilder;
    private ArrayList<String> queryArguments;


    public DBQuery() {
        queryBuilder = new StringBuilder();
        queryArguments = new ArrayList<>();
    }

    public void append(String string){
        queryBuilder.append(string);
    }

    public void appendColumnWithArgument(String column, String argument){
        queryBuilder.append(column);
        queryArguments.add(argument);
    }

    public void appendWithSpace(String string){
        queryBuilder.append(string);
        queryBuilder.append(" ");
    }

    protected Integer getArgumetnNumber(){
        return queryArguments.size();
    }
    public void doStartQuery(){

    }

    public void doEndQuery(){

    }

    public String getSQLQuery(){
        return queryBuilder.toString();
    }

    public List<String> getQueryArguments(){
        return queryArguments;
    }

}
