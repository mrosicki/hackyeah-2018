package com.prototype.hackyeah2018.search;

import java.util.ArrayList;
import java.util.List;

public class SimpleSearchEngine implements ISearchEngine {
    @Override
    public <T> List<T> search(List<T> elements, String query) {
        List<T> matches = new ArrayList<>();

        for (T element : elements) {
            if(element.toString().toLowerCase().contains(query.toLowerCase())) {
                matches.add(element);
            }
        }

        return matches;
    }
}
