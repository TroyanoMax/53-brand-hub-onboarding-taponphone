package com.am53.visadirect.fundstransactions.apiext;

import java.util.List;

public interface ApiGlobalData<Q, F> {
    Q getResponse(String resource, List<F> params, List<F> query);
    Q getResponse(String resource, List<F> params, List<F> query, Object body);
}
