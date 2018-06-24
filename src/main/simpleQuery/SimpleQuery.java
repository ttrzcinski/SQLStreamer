package main.someday_beans;

import main.interfaces.IQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents simple query with only filtering columns from source and order.
 */
public class SimpleQuery implements IQuery {
    //Constants and statics
    //TODO ADD HERE COUNTER GIVING UNIQUE IDS TO CREATED ENTRIES

    //Variables
    private long id;

    private List<String> columns;
    private List<String> sources;
    private List<String> sorts;

    //Constructors
    /**
     * Creates new instance of simple query to set params later.
     */
    public SimpleQuery() {
        this.initEverything();
    }

    /**
     * Creates new instance of simple query with given id.
     *
     * @param id given id
     */
    public SimpleQuery(long id) {
        this.id = id;
    }

    /**
     * Creates new instance of simple query with given params.
     *
     * @param id given id
     * @param columns list of shown columns
     * @param sources list of data sources
     * @param sorts list of columns used to sorting the results
     */
    public SimpleQuery(long id, List<String> columns, List<String> sources, List<String> sorts) {
        this.id = id;
        this.columns = columns;
        this.sources = sources;
        this.sorts = sorts;
    }

    /**
     * Creates new instance of simple query with given params.
     *
     * @param columns list of shown columns
     * @param sources list of data sources
     * @param sorts list of columns used to sorting the results
     */
    public SimpleQuery(List<String> columns, List<String> sources, List<String> sorts) {
        this.columns = columns;
        this.sources = sources;
        this.sorts = sorts;
    }

    //Inner methods
    /**
     * Prepares instance of query with initializing all inner properties.
     *
     * @param clear marks, if clearing previous values is set
     */
    private void zeroValues(boolean clear) {
        //TODO SET id - mock to remove
        this.id = 1;
        //Assure NotNull on columns
        if (this.columns == null) {
            this.columns = new ArrayList<String>();
        } else if (clear) {
            this.columns.clear();
        }
        //Assure NotNull on sources
        if (this.sources == null) {
            this.sources = new ArrayList<String>();
        } else if (clear) {
            this.sources.clear();
        }
        //Assure NotNull on sorts
        if (this.sorts == null) {
            this.sorts = new ArrayList<String>();
        } else if (clear) {
            this.sorts.clear();
        }
    }

    /**
     * Initializes all properties without clearing
     */
    private void initEverything() {
        this.zeroValues(false);
    }

    /**
     * Clears all the values kept in leaving query in "zero state".
     */
    private void clearAll() {
        this.zeroValues(true);
    }

    /**
     * Builds string one-liner from given query values.
     *
     * @return SQL query one-liner
     */
    public String asString() {
        //Add select
        StringBuilder sb = new StringBuilder("SELECT ");
        //add columns
        int i = 0;
        if (this.columns != null && this.columns.size() > 0) {
            //TODO CHANGE TO JOINER
            i = 0;
            do {
                String column = this.columns.get(i);
                //add column name and comma, if there is next one
                //TODO GO WITH STRING.EMPTY_STRING
                sb.append(column).append(i < this.columns.size()-1 ? ", " : " ");
                i++;
            } while (i < this.columns.size());
        } else {
            sb.append("* ");
        }
        //add sources
        if (this.sources != null && this.sources.size() > 0) {
            sb.append("FROM ");
            //TODO CHANGE TO JOINER
            i = 0;
            do {
                String source = this.sources.get(i);
                //add sources name and comma, if there is next one
                //TODO ADD CHECK FOR NULL OR EMPTY ON source NAME
                //TODO GO WITH STRING.EMPTY_STRING
                sb.append(source).append(i < this.sources.size()-1 ? ", " : " ");
                i++;
            } while (i < this.sources.size());
        } else {
            //TODO CHANGE TO DEFAULT NAME OF TABLE
            sb.append("DUAL ");
        }
        //Add sorts
        if (this.sorts != null && this.sorts.size() > 0) {
            sb.append("ORDER BY ");
            //TODO CHANGE TO JOINER
            i = 0;
            do {
                String sort = this.sorts.get(i);
                //add sorts name and comma, if there is next one
                //TODO ADD CHECK FOR NULL OR EMPTY ON sort NAME
                //TODO GO WITH STRING.EMPTY_STRING
                sb.append(sort).append(i < this.sorts.size()-1 ? ", " : " ");
                i++;
            } while (i < this.sources.size());
        }
        sb.append(';');
        //TODO LAST MOMENT TO CHANGE SYNTAX TO DIFFERENT SQL
        //And return concatenated
        return sb.toString();
    }

    //API methods

    //Accessors
    public long getId() {
        return this.id;
    }

    /**
     * Sets id of query. (Setting of id from outside is blocked.)
     *
     * @param id given id.
     */
    private void setId(long id) {
        this.id = id;
    }

    public List<String> getColumns() {
        return this.columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<String> getSources() {
        return this.sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public List<String> getSorts() {
        return this.sorts;
    }

    public void setSorts(List<String> sorts) {
        this.sorts = sorts;
    }
}
