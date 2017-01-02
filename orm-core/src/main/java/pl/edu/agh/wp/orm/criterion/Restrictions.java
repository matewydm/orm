package pl.edu.agh.wp.orm.criterion;

import pl.edu.agh.wp.orm.criterion.queries.*;
import pl.edu.agh.wp.orm.postres.DatabaseStatement;

public class Restrictions {


    public static Criterion and(Criterion left, Criterion right){
        return new LogicalCriterion(left,right, DatabaseStatement.AND);
    }

    public static Criterion or(Criterion left, Criterion right){
        return new LogicalCriterion(left,right, DatabaseStatement.OR);
    }

    public static Criterion in(String column, Object[] array){
        return new ArrayCriterion(column,array);
    }

    public static Criterion gt(String column, Object value){
        return new SimpleCriterion(column,value,DatabaseStatement.GREATER);
    }

    public static Criterion ge(String column, Object value){
        return new SimpleCriterion(column,value,DatabaseStatement.GREATER_EQUAL);
    }

    public static Criterion lt(String column, Object value){
        return new SimpleCriterion(column,value,DatabaseStatement.LESS);
    }

    public static Criterion le(String column, Object value){
        return new SimpleCriterion(column,value,DatabaseStatement.LESS_EQUAL);
    }

    public static Criterion eq(String column, Object value){
        return new SimpleCriterion(column,value,DatabaseStatement.EQUAL);
    }

    public static Criterion isNull(String column){
        return new NullCriterion(column,DatabaseStatement.NULL);
    }

    public static Criterion isNotNull(String column){
        return new NullCriterion(column,DatabaseStatement.NOT_NULL);
    }

    public static Criterion like(String column, Object value) {
        return new SimpleCriterion(column,value,DatabaseStatement.LIKE);
    }

    public static Criterion between(String column, Object minValue, Object maxValue) {
       return new BetweenCriterion(column,minValue,maxValue);
    }

}
