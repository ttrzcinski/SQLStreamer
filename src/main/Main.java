package main;

import main.enums.Command;
import main.interfaces.IQuery;
import main.someday_beans.SimpleQuery;
import main.someday_beans.SimpleQueryBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static void show(IQuery query) {
        //In the wind of tests..
        if (query != null) {
            System.out.println("your wanted query is:");
            System.out.println(query.asString());
        } else {
            System.out.println("Not implemented Yet!");
        }
    }

    /**
     * Just a playground to test'n'develop the SQLStream.
     *
     * @param args params to use as jar (someday)
     */
    public static void main(String[] args) {
        //TODO prepare parametrization and it's processing

	    //TODO Prepare simple select object
        //SQLStream sqlStream = new SQLStream(Command.QUERY);//);
        //sqlStream.select();
        //sqlStream.select("*");
        //sqlStream.from("DA_TABLE");
        //sqlStream.endWithSemicolor();
        SimpleQuery query = new SimpleQueryBuilder()
                                //.select("some_column")
                                .select(Arrays.asList("some_column"))
                                .from("table_1")
                                .orderBy("id")
                                .build();

        //Show query on console
        show(query);
    }
}
