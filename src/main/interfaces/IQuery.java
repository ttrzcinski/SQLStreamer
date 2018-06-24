package main.interfaces;

public interface IQuery {
    /**
     * Builds given query based on kept inside params.
     *
     * @return query as single line
     */
    public String asString();
}
