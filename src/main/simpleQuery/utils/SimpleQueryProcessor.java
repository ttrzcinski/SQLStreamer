package main.simpleQuery.utils;

import main.interfaces.IQuery;

/**
 * Processes given Simple Query(@see SimpleQuery) into wanted SQL Syntax.
 */
public class SimpleQueryProcessor {
    //Variables
    private IQuery query;

    //Constructor
    /**
     * Creates new instance of query processor..
     */
    SimpleQueryProcessor(){
        ;
    }

    /**
     * Creates new instance of query processor with given query.
     *
     * @param query given query
     */
    SimpleQueryProcessor(IQuery query){
        this.query = query;
    }

    //Convert to SQL syntax
    /**
     * Breaks query into the lines making it more readable.
     *
     * @return query in more readable form
     */
    public String asPrettyLinedSQL() {
        if (this.query != null) {
            //Add select
            StringBuilder stBd = new StringBuilder("SELECT ").append(System.getProperty("line.separator"));
            //add columns
            int i = 0;
            if (this.query.getColumns() != null && this.query.getColumns().size() > 0) {
                //TODO CHANGE TO JOINER
                i = 0;
                do {
                    String column = this.query.getColumns().get(i);
                    //add column name and comma, if there is next one
                    //TODO GO WITH STRING.EMPTY_STRING
                    stBd.append(column)
                        .append(i < this.query.getColumns().size()-1 ? ", " : " ")
                        .append(System.getProperty("line.separator"));
                    i++;
                } while (i < this.query.getColumns().size());
            } else {
                stBd.append("* ").append(System.getProperty("line.separator"));
            }
            //add sources
            if (this.query.getSources() != null && this.query.getSources().size() > 0) {
                stBd.append("FROM ").append(System.getProperty("line.separator"));
                //TODO CHANGE TO JOINER
                i = 0;
                //TODO WHOLE DO TWO IFS CAN BE OPTIMIZED TO ON DO-WHILE AND APPEND OF LSST ELEMENT AFTER THE LOOP
                do {
                    String source = this.query.getSources().get(i);
                    //add sources name and comma, if there is next one
                    //TODO ADD CHECK FOR NULL OR EMPTY ON source NAME
                    //TODO GO WITH STRING.EMPTY_STRING
                    stBd.append(source)
                        .append(i < this.query.getSources().size()-1 ? ", " : " ")
                        .append(System.getProperty("line.separator"));
                    i++;
                } while (i < this.query.getSources().size());
            } else {
                //TODO CHANGE TO DEFAULT NAME OF TABLE
                stBd.append("DUAL ")
                    .append(System.getProperty("line.separator"));
            }
            //Add sorts
            if (this.query.getSorts() != null && this.query.getSorts().size() > 0) {
                stBd.append("ORDER BY ")
                    .append(System.getProperty("line.separator"));
                //TODO CHANGE TO JOINER
                i = 0;
                do {
                    String sort = this.query.getSorts().get(i);
                    //add sorts name and comma, if there is next one
                    //TODO ADD CHECK FOR NULL OR EMPTY ON sort NAME
                    //TODO GO WITH STRING.EMPTY_STRING
                    stBd.append(sort)
                            .append(i < this.query.getSorts().size()-1 ? ", " : " ")
                            .append(System.getProperty("line.separator"));
                    i++;
                } while (i < this.query.getSorts().size());
            }
            stBd.append(';')
                .append(System.getProperty("line.separator"));
            //TODO LAST MOMENT TO CHANGE SYNTAX TO DIFFERENT SQL
            //And return concatenated
            return stBd.toString();
        } else {
            return "--Query was not given..";
        }
    }

    public String asMySQL() {
        //TODO Implement this syntax
        return "Not Implemented Yet";
    }

    public String asOracleSQL() {
        //TODO Implement this syntax
        return "Not Implemented Yet";
    }

    public String asMSSQL() {
        //TODO Implement this syntax
        return "Not Implemented Yet";
    }

    public String asPostgreSQL() {
        //TODO Implement this syntax
        return "Not Implemented Yet";
    }

    //Accessors
    public IQuery getQuery() {
        return this.query;
    }

    public void setQuery(IQuery query) {
        this.query = query;
    }
}
