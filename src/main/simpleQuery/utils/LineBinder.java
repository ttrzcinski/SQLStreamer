package main.simpleQuery.utils;

import java.util.List;

/**
 * Binds list of parameters into one line.
 * @see ListChopper
 */
public class LineBinder {

    /**
     * Binds list of elements into one line with wanted delimiter in between.
     *
     * @param list list to bind
     * @param delimiter pointed delimiter
     * @return line with delimiters
     */
    public String bindList(List<String> list, String delimiter) {
        //Check, if delimiter has value
        if (delimiter == null || delimiter.length() == 0) {
            delimiter = "";
        }
        //Process list
        StringBuilder line = null;
        if (list != null) {
            if (list.size() > 0) {
                line = new StringBuilder();
                for (int i = 0; i < list.size() - 1; i++) {
                    line.append(list.get(i) != null ? list.get(i).trim() : "")
                        .append(delimiter);
                }
                line.append(list.get(list.size() - 1).trim());
            }
        }
        String result = (line != null) ? (line.toString().trim()) : "";
        return result.length() > 0 ? result : null;
    }

    /**
     * Binds list of elements into one line with delimiter in between.
     *
     * @param list list to bind
     * @return line with delimiters
     */
    public String bindList(List<String> list) {
        return this.bindList(list, ", ");
    }
}
