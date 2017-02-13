package pl.edu.agh.wp.orm.criterion;

import pl.edu.agh.wp.orm.criterion.queries.*;
import pl.edu.agh.wp.orm.criterion.queries.logical.ConjunctionCriterion;
import pl.edu.agh.wp.orm.criterion.queries.logical.DisjunctionCriterion;
import pl.edu.agh.wp.orm.criterion.queries.nullable.IsNotNullCriterion;
import pl.edu.agh.wp.orm.criterion.queries.nullable.IsNullCriterion;
import pl.edu.agh.wp.orm.criterion.queries.simple.*;

public class Restrictions {


    public static Criterion and(Criterion left, Criterion right){
        return new ConjunctionCriterion(left,right);
    }

    public static Criterion or(AbstractCriterion left, AbstractCriterion right){
        return new DisjunctionCriterion(left,right);
    }

    public static Criterion in(String column, Object[] array){
        return new ArrayCriterion(column,array);
    }

    public static Criterion gt(String column, Object value){
        return new GtCriterion(column,value);
    }

    public static Criterion ge(String column, Object value){
        return new GeCriterion(column,value);
    }

    public static Criterion lt(String column, Object value){
        return new LtCriterion(column,value);
    }

    public static Criterion le(String column, Object value){
        return new LqCriterion(column,value);
    }

    public static Criterion eq(String column, Object value){
        return new EqCriterion(column,value);
    }

    public static Criterion isNull(String column){
        return new IsNullCriterion(column);
    }

    public static Criterion isNotNull(String column){
        return new IsNotNullCriterion(column);
    }

    public static Criterion like(String column, Object value) {
        return new LikeCriterion(column,value);
    }

    public static Criterion between(String column, Object minValue, Object maxValue) {
       return new BetweenCriterion(column,minValue,maxValue);
    }

}
