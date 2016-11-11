package org.codehaus.jackson.map.ser.impl;

import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.map.ser.BeanPropertyFilter;
import org.codehaus.jackson.map.ser.FilterProvider;

public class SimpleFilterProvider extends FilterProvider {
    protected BeanPropertyFilter _defaultFilter;
    protected final Map<String, BeanPropertyFilter> _filtersById;

    public SimpleFilterProvider() {
        this._filtersById = new HashMap();
    }

    public SimpleFilterProvider(Map<String, BeanPropertyFilter> map) {
        this._filtersById = new HashMap();
    }

    public SimpleFilterProvider addFilter(String str, BeanPropertyFilter beanPropertyFilter) {
        this._filtersById.put(str, beanPropertyFilter);
        return this;
    }

    public BeanPropertyFilter findFilter(Object obj) {
        BeanPropertyFilter beanPropertyFilter = (BeanPropertyFilter) this._filtersById.get(obj);
        return beanPropertyFilter == null ? this._defaultFilter : beanPropertyFilter;
    }

    public BeanPropertyFilter removeFilter(String str) {
        return (BeanPropertyFilter) this._filtersById.remove(str);
    }

    public SimpleFilterProvider setDefaultFilter(BeanPropertyFilter beanPropertyFilter) {
        this._defaultFilter = beanPropertyFilter;
        return this;
    }
}
