package main;

import main.enums.Command;
import main.enums.Completion;
import main.interfaces.IQuery;

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

    public void select(String what) {
        if (this.levelOfCompletion == Completion.EMPTY) {
            this.sb.append("SELECT ").append(what);
            //Mark level of completion
            this.soStep(Completion.SELECT, Command.QUERY);
        } else if (this.levelOfCompletion == Completion.SELECT) {
            this.sb.append(what);
            //Mark level of completion
            this.soStep(Completion.FUNCTION, Command.QUERY);
        } else if (this.levelOfCompletion == Completion.FUNCTION) {
            this.sb.append(", ").append(what);
            //Mark level of completion
            this.soStep(Completion.FUNCTION, Command.QUERY);
        }
    }

    public void select() {
        if (this.levelOfCompletion == Completion.EMPTY) {
            this.sb.append("SELECT ");
            //Mark level of completion
            this.levelOfCompletion = Completion.SELECT;
            this.typeOfCommand = Command.QUERY;
        }
    }

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

    public String asString() {
        //TODO SHOULD POINT TO STRING.EMPTY_STRING
        return this.sb != null ? this.sb.toString() : "";
    }

    @Override
    public String toString() {
        return this.asString();
    }
}
