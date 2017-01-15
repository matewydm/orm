package pl.edu.agh.wp.orm.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext
                ("bean.xml");
        context.getApplicationName();
    }
}
