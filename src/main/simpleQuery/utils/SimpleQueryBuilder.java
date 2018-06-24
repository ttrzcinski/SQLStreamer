package main.simpleQuery.utils;

import main.simpleQuery.SimpleQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleQueryBuilder {
    //TODO ADD HERE STATIC COUNTER TO ADD NEW IDs

    //Variables
    private long id;
    private List<String> columns;
    private List<String> sources;
    private List<String> sorts;

    /**
     * Cuts line with elements into list of elements.
     */
    private ListChopper listChopper;

    //Constructors
    public SimpleQueryBuilder() {
        this.id = 1;
        //Prepare list chopper
        this.prepareListChopper();
    }

    /**
     * Creates new instance of builder based on given parames.
     *
     * @param id instance's id
     * @param columns wanted columns
     * @param sources data sources
     * @param sorts sorting columns
     */
    public SimpleQueryBuilder(long id, List<String> columns, List<String> sources, List<String> sorts) {
        this.id = id;
        this.columns = columns;
        this.sources = sources;
        this.sorts = sorts;
        //Prepare list chopper
        this.prepareListChopper();
    }

    //Inner methods
    /**
     * Assures, that list chopper won't be null, when needed.
     */
    private void prepareListChopper() {
        if (this.listChopper == null) {
            this.listChopper = new ListChopper();
        }
    }

    /**
     * Sets instance to it's "zero state".
     */
    private void zeroValues() {
        //Prepare listChopper
        this.prepareListChopper();
        //Prepare lists to fill up
        if (this.columns == null) {
            this.columns = new ArrayList<String>();
        } else {
            this.columns.clear();
        }

        if (this.sources == null) {
            this.sources = new ArrayList<String>();
        } else {
            this.sources.clear();
        }

        if (this.sorts == null) {
            this.sorts = new ArrayList<String>();
        } else {
            this.sorts.clear();
        }
    }

    //Commands
    //TODO CHANGE THOSE TO ADDITIVE MODE, NOT SET
    public SimpleQueryBuilder select(List<String> columns) {
        this.columns = columns;
        return this;
    }

    public SimpleQueryBuilder select(String[] columns) {
        List<String> converted = new ArrayList<String>();
        Collections.addAll(converted, columns);
        this.columns = converted;
        return this;
    }

    public SimpleQueryBuilder select(String column) {
        this.columns = listChopper.chopBy(column, ", ");
        return this;
    }

    public SimpleQueryBuilder from(List<String> sources) {
        this.sources = sources;
        return this;
    }

    public SimpleQueryBuilder from(String[] sources) {
        List<String> converted = new ArrayList<String>();
        Collections.addAll(converted, sources);
        this.sources = converted;
        return this;
    }

    public SimpleQueryBuilder from(String source) {
        this.sources = listChopper.chopBy(source, ", ");
        return this;
    }

    //TODO ADD VARIATION WITH sorts... as many args

    public SimpleQueryBuilder orderBy(List<String> sorts) {
        this.sorts = sorts;
        return this;
    }

    public SimpleQueryBuilder orderBy(String[] sorts) {
        List<String> converted = new ArrayList<String>();
        Collections.addAll(converted, sorts);
        this.sorts = converted;
        return this;
    }

    public SimpleQueryBuilder orderBy(String sort) {
        this.sorts = listChopper.chopBy(sort, ", ");
        return this;
    }

    /**
     * Returns object with collected given params in the process of building.
     *
     * @return object with all the params
     */
    public SimpleQuery build() {
        return new SimpleQuery(id, columns, sources, sorts);
    }
}
