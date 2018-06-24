package main.someday_beans;

import java.util.Arrays;
import java.util.List;

public class SimpleQueryBuilder {
    //
    private long id;

    private List<String> columns;
    private List<String> sources;
    private List<String> sorts;

    //
    public SimpleQueryBuilder() {
        this. id = 1;
    }

    public SimpleQueryBuilder(long id, List<String> columns, List<String> sources, List<String> sorts) {
        this.id = id;
        this.columns = columns;
        this.sources = sources;
        this.sorts = sorts;
    }

    public SimpleQueryBuilder select(List<String> columns) {
        this.columns = columns;
        return this;
    }
    //TODO Add setting with array of strings

    public SimpleQueryBuilder select(String column) {
        this.columns = Arrays.asList(column);
        return this;
    }

    public SimpleQueryBuilder from(List<String> sources) {
        this.sources = sources;
        return this;
    }
    //TODO Add setting with array of strings
    public SimpleQueryBuilder from(String source) {
        this.sources = Arrays.asList(source);
        return this;
    }

    public SimpleQueryBuilder orderBy(List<String> sorts) {
        this.sorts = sorts;
        return this;
    }
    //TODO Add setting with array of strings
    public SimpleQueryBuilder orderBy(String sort) {
        this.sorts = Arrays.asList(sort);
        return this;
    }

    public SimpleQuery build() {
        return new SimpleQuery(id, columns, sources, sorts);
    }
}
