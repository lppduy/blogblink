package com.lppduy.blogblink.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Setter
@Getter
public abstract class PaginationAttributes {
    private int page = 1;
    private int size = 10;
    private String direction;
    private String sortBy;
    private Pageable pageable;

    public void updatePageable() {
        String effectiveSortBy = (this.sortBy == null || this.sortBy.isEmpty()) ? getDefaultSortBy() : this.sortBy;
        String effectiveDirection = (this.direction == null || this.direction.isEmpty()) ? getDefaultDirection() : this.direction;

        this.pageable = PageRequest.of(
                this.page - 1,
                this.size,
                Sort.by(Sort.Direction.fromString(effectiveDirection), effectiveSortBy)
        );
    }

    protected abstract String getDefaultSortBy();
    protected abstract String getDefaultDirection();
}