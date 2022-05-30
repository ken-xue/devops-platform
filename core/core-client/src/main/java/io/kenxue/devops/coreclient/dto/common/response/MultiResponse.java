package io.kenxue.devops.coreclient.dto.common.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MultiResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    private Collection<T> data;

    public List<T> getData() {
        return null == data ? Collections.emptyList() : new ArrayList<>(data);
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public boolean isEmpty() {
        return data == null || data.size() == 0;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public static MultiResponse success() {
        MultiResponse response = new MultiResponse();
        return response;
    }

    public static MultiResponse error(Integer code, String message) {
        MultiResponse response = new MultiResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static <T> MultiResponse<T> of(Collection<T> data) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setData(data);
        return response;
    }

}
