package org.obaro.portfolio.musicshow.dto;

import java.util.List;

public class ApiPageResponse<T> {

    private List<T> data;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private String sort;

    public ApiPageResponse(
            List<T> data,
            int page,
            int size,
            long totalElements,
            int totalPages,
            String sort
    ) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.sort = sort;
    }

    public List<T> getData() {
        return data;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public String getSort() {
        return sort;
    }
}
