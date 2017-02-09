package pl.edu.agh.wp.orm.example;

import pl.ed.agh.wp.orm.annotations.DBColumn;
import pl.ed.agh.wp.orm.annotations.DBTable;

@DBTable
public class SuperPerson extends Person{

    @DBColumn
    private Integer spId;
    @DBColumn
    private Integer isSuper;

    public Integer getSuper() {
        return isSuper;
    }

    public void setSuper(Integer aSuper) {
        isSuper = aSuper;
    }
}
