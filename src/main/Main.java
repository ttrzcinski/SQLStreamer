package main;

import main.interfaces.IQuery;
import main.simpleQuery.SimpleQuery;
import main.simpleQuery.utils.SimpleQueryBuilder;

import java.util.Arrays;

public class Main {
    //TODO edit java classes patterns in the IDE in order to add javadoc from templates with basic description.

    //TODO ADD JUNITS TO THIS ONE - HOW TO TEST CONSOLE ?
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

	    //Prepare simple select object
        //SQLStream sqlStream = new SQLStream(Command.QUERY);//);
        //sqlStream.select();
        //sqlStream.select("*");
        //sqlStream.from("DA_TABLE");
        //sqlStream.endWithSemicolor();
        SimpleQuery query = new SimpleQueryBuilder()
                                .select("some_column")
                                .select(Arrays.asList("other_column"))
                                .select("COUNT(the_column)")
                                .from("table_1")
                                .from("table_2")
                                .from("table_N")
                                .orderBy("id")
                                .orderBy("uuid")
                                .orderBy("the_last_column")
                                .build();

        //Show query on console
        show(query);
    }
}
