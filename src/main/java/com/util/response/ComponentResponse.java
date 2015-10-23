package com.util.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;

/**
 * Object representing a Component Response
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ComponentResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private Map.Entry<String, T> data;

    /**
     * Get data
     *
     * @return data
     */
    public Map.Entry<String, T> getData() {
        return data;
    }

    /**
     * Set data
     *
     * @param data
     */
    public void setData(Map.Entry<String, T> data) {
        this.data = data;
    }

    /**
     * Set data
     *
     * @param dataType
     * @param t
     */
    public void setData(T t) {
        this.data = new AbstractMap.SimpleEntry<String, T>("data", t);
    }


    @Override
    public String toString() {
        return "ComponentResponse{" +
                "data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentResponse that = (ComponentResponse) o;

        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        return result;
    }
}

