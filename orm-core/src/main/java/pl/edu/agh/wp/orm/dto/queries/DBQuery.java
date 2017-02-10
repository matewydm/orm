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

    public void appendEquation(String column, String value) {
        appendWithSpace(column);
        appendWithSpace("=");
        append(value);
    }

    protected Integer getArgumentNumber(){
        return queryArguments.size();
    }

    public void doStartQuery(){

    }

    public void doEndQuery(){

    }

    public String getSQLQuery(){
        return queryBuilder.toString();
    }

    public Object getArgument(int index){
        return queryArguments.get(index);
    }

    public void addArgument(String argument){
        queryArguments.add(argument);
    }

    public void addArgumentList(List<String> argumentList){
        queryArguments.addAll(argumentList);
    }

    public int countArguments() {
        return queryArguments.size();
    }
}
