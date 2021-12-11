package io.kenxue.cicd.coreclient.dto.common.response;

import io.kenxue.cicd.coreclient.dto.common.page.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PageResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    private long totalCount = 0;

    private long pageSize = 10;

    private long pageIndex = 1;

    private Collection<T> data;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPageSize() {
        if (pageSize < 1) {
            return 1;
        }
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
    }

    public long getPageIndex() {
        if (pageIndex < 1) {
            return 1;
        }
        return pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        if (pageIndex < 1) {
            this.pageIndex = 1;
        } else {
            this.pageIndex = pageIndex;
        }
    }

    public List<T> getData() {
        return null == data ? Collections.emptyList() : new ArrayList<>(data);
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public long getTotalPages() {
        return this.totalCount % this.pageSize == 0 ? this.totalCount
            / this.pageSize : (this.totalCount / this.pageSize) + 1;
    }

    public boolean isEmpty() {
        return data == null || data.size() == 0;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public static PageResponse success() {
        PageResponse response = new PageResponse();
        return response;
    }

    public static PageResponse error(Integer code, String message) {
        PageResponse response = new PageResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
    public static <T> PageResponse<T> of(Page<T> page) {
        PageResponse<T> response = new PageResponse<>();
        response.setData(page.getRecords());
        response.setTotalCount(page.getTotal());
        response.setPageSize(page.getSize());
        response.setPageIndex(page.getCurrent());
        return response;
    }
    public static <T> PageResponse<T> of(int pageSize, int pageIndex) {
        PageResponse<T> response = new PageResponse<>();
        response.setData(Collections.emptyList());
        response.setTotalCount(0);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }

    public static <T> PageResponse<T> of(Collection<T> data, long totalCount,  long pageSize, long pageIndex) {
        PageResponse<T> response = new PageResponse<>();
        response.setData(data);
        response.setTotalCount(totalCount);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }

}
