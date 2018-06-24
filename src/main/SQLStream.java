package main;

import main.enums.Command;
import main.enums.Completion;
import main.interfaces.IQuery;

import java.util.ArrayList;
import java.util.List;

public class SQLStream implements IQuery {
    //Constants

    //Variables
    private StringBuilder sb;
    /**
     * Represents level of completion - last part was.
     * 0 - empty
     * 1 - select
     * 2 - function
     * 3 - from
     * 4 - where
     * 5 - order by
     * 6 - group by
     * 7 - semicolon
     */
    private Completion levelOfCompletion;

    /**
     * Keeps state of currently built SQL command.
     * 0 - Unknown
     * 1 - Query
     * 2 - Insert
     * 3 - Update
     * 4 - Delete
     * 5 - Create
     * 6 - Drop
     * 7 - Alter
     * 8 - Purge
     */
    private Command typeOfCommand;

    private List<String> columns;
    private List<String> sources;
    private List<String> sorts;

    //Constructors
    /**
     * Creates new query command.
     */
    public SQLStream() {
        //Create new empty stream
        this.sb = new StringBuilder();
        //Mark level of completion
        this.levelOfCompletion = Completion.EMPTY;
    }

    /**
     * Creates new SQL command with given command
     *
     * @param command given command
     */
    public SQLStream(Command command) {
        //Create new empty stream
        this.sb = new StringBuilder();
        //Mark level of completion

        this.typeOfCommand = command;
        this.levelOfCompletion = Completion.EMPTY;//this.typeOfCommand == Command.NONE ?  : Completion.SELECT;
        //
        if (this.typeOfCommand == Command.QUERY) {
            this.select();
        }
    }

    //Inner methods
    /**
     * Prepares instance of query with initializing all inner properties.
     *
     * @param clear marks, if clearing previous values is set
     */
    private void zeroValues(boolean clear) {
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

    //API methods
    public void select(String what) {
        //Check param column name - spaces are meaningless
        what = what == null ? "" : what.trim();
        //Processing into query
        if (this.levelOfCompletion == Completion.EMPTY) {
            //First call to select without query set
            this.sb.append("SELECT ").append(what);
            //Mark level of completion
            this.soStep(Completion.SELECT, Command.QUERY);
        } else if (this.levelOfCompletion == Completion.SELECT) {
            //First call to select with query set
            this.sb.append(", ").append(what);
            //Mark level of completion
            this.soStep(Completion.FUNCTION, Command.QUERY);
        } else if (this.levelOfCompletion == Completion.FUNCTION) {
            //Another call to select with columns given
            this.sb.append(", ").append(what);
            //Mark level of completion
            this.soStep(Completion.FUNCTION, Command.QUERY);
        }
    }

    public void select() {
        this.select(null);
        /*if (this.levelOfCompletion == Completion.EMPTY) {
            this.sb.append("SELECT ");
            //Mark level of completion
            this.levelOfCompletion = Completion.SELECT;
            this.typeOfCommand = Command.QUERY;
        }*/
    }

    //Expected :SELECT user_id, COUNT(1) from  da_dual ORDER BY user_id;
    //Actual   :SELECT user_idCOUNT(1)* FROM da_dualORDER BY;
    //FIXME
    public void countBy(String what) {
        if (this.typeOfCommand == Command.QUERY) {
            if (this.levelOfCompletion == Completion.SELECT) {
                //Add wanted function
                this.sb.append("COUNT(").append(what).append(")");
            } else if (this.levelOfCompletion == Completion.FUNCTION) {
                //Add comma, if there is already wanted column
                this.sb.append(this.levelOfCompletion == Completion.FUNCTION ? ", " : "");
                //Add wanted function
                this.sb.append("COUNT(").append(what).append(")");
            }
            //Mark level of completion
            this.levelOfCompletion = Completion.FUNCTION;
            this.typeOfCommand = Command.QUERY;
        }
    }

    //TODO ADD STATISTICAL FUNCTIONS

    public void from(String what) {
        if (this.levelOfCompletion == Completion.SELECT || this.typeOfCommand == Command.QUERY) {
            this.sb.append("* ");
            this.sb.append("FROM ").append(what);
            this.soStep(Completion.FROM);
        } else if (this.levelOfCompletion == Completion.FUNCTION) {
            this.sb.append("FROM ").append(what);
            this.sb.append(' ');
            this.soStep(Completion.FROM);
        } else if (this.levelOfCompletion == Completion.FROM) {
            this.sb.append(' ');
            this.soStep(Completion.FROM);
        }

        //Mark level of completion
        this.levelOfCompletion = Completion.FROM;
    }

    //TODO ALIAS ON TABLE OR VIEW

    public void whereEquals(String columnName, String val) {
        this.sb.append(columnName).append('=').append(val);
        //Mark level of completion
        this.levelOfCompletion = Completion.WHERE;
    }

    //TODO ADD AND
    //TODO ADD OR

    public void orderBy(String byWhat, boolean desc) {
        this.sb.append("ORDER BY ").append(desc ? "ASC": "DESC");
        //Mark level of completion
        this.levelOfCompletion = Completion.WHERE;
    }

    public void orderBy(String byWhat) {
        this.sb.append("ORDER BY");
        //Mark level of completion
        this.levelOfCompletion = Completion.ORDERBY;
    }

    //TODO ADD GROUP BY

    public void endWithSemicolor() {
        this.sb.append(';');
        this.levelOfCompletion = Completion.ENDING;
    }

    //API methods outside of SQL syntax

    /**
     * Sets level of completion and type of command.
     *
     * @param level given level
     * @param command given command
     */
    private void soStep(Completion level, Command command) {
        this.levelOfCompletion = level;
        this.typeOfCommand = command;
    }

    /**
     * Sets level of completion to given
     *
     * @param level given level
     */
    private void soStep(Completion level) {
        this.levelOfCompletion = level;
    }

    public int length() {
        return this.sb != null ? this.sb.length() : 0;
    }

    public boolean isEmpty() {
        return this.length() > 0;
    }

    //Accessors
    public StringBuilder getSb() {
        return this.sb;
    }

    @Override
    public List<String> getColumns() {
        return null;
    }

    @Override
    public List<String> getSources() {
        return null;
    }

    @Override
    public List<String> getSorts() {
        return null;
    }

    public String asString() {
        //Prepare result
        String result = this.sb != null ? this.sb.toString() : "";
        //If no column was given to select
        if (levelOfCompletion == Completion.SELECT && typeOfCommand == Command.QUERY && result.trim().endsWith("SELECT")) {
            result = "SELECT *";
        }
        //TODO SHOULD POINT TO STRING.EMPTY_STRING
        return result;
    }

    @Override
    public String toString() {
        return this.asString();
    }
}
