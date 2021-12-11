package io.kenxue.cicd.coreclient.dto.common.page;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;
/**
 * 简单分页
 */
@Data
@Accessors(chain = true)
public class Page<T> {

    private static final long serialVersionUID = 1L;
    protected List<T> records = Collections.emptyList();
    protected long total = 0;
    protected long size = 10;
    protected long current = 1;

    public Page() {
    }

    public Page(long current, long size, long total, List<T> records) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
        this.records = records;
    }

    public boolean hasPrevious() {
        return this.current > 1;
    }

    public boolean hasNext() {
        return this.current < this.getPages();
    }

    public static <T> Page<T> of(long current, long size, long total, List<T> records) {
        return new Page<>(current, size, total, records);
    }
    
    public long getPages() {
        if (getSize() == 0) {
            return 0L;
        }
        long pages = getTotal() / getSize();
        if (getTotal() % getSize() != 0) {
            pages++;
        }
        return pages;
    }

}

