package main.simpleQuery.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Cuts any given item into list of strings used as list of columns or list of tables or any other a-like.
 * @see LineBinder
 */
public class ListChopper {

    /**
     * Cuts given one-line string to elements by given delimiter.
     *
     * @param line given line to cut
     * @param chopBy delimiter
     * @return list of chopped pieces
     */
    public List<String> chopBy(String line, String chopBy) {
        List<String> chopped = null;
        line = line!= null ? line.trim() : null;
        if (line != null && line.length() > 0) {
            chopped = new ArrayList<>();
            //Chop
            //TODO CHANGE TO USE OF SPLIT - SHOULD BE FASTER
            StringTokenizer strTok = new StringTokenizer(line, chopBy);
            do {
                chopped.add(strTok.nextToken().trim());
            } while (strTok.hasMoreTokens());
        }
        return chopped;
    }

    /**
     * Cuts given one-line string to elements by default comma.
     *
     * @param line given line to cut
     * @return list of chopped pieces
     */
    public List<String> chopBy(String line) {
        return this.chopBy(line, ",");
    }
}
