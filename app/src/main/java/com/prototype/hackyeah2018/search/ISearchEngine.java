package com.prototype.hackyeah2018.search;

import java.util.List;

public interface ISearchEngine {

   <T> List<T> search(List<T> elements, String query);
}
