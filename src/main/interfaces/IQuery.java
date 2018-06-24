package main.interfaces;

import java.util.List;

public interface IQuery {
    /**
     * Builds given query based on kept inside params.
     *
     * @return query as single line
     */
    String asString();

    /**
     * Returns list of columns used in query.
     *
     * @return list of columns
     */
    List<String> getColumns();

    /**
     * Returns list of sources used in query.
     *
     * @return list of sources
     */
    List<String> getSources();

    /**
     * returns list of sorts used in query.
     *
     * @return list of sorts
     */
    List<String> getSorts();
}
