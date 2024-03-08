package com.lppduy.blogblink.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class UserSearchRequestDTO extends PaginationAttributes{
    public static final String DEFAULT_SORT_BY = "username";
    public static final String DEFAULT_DIRECTION = "ASC";

    private String searchTerm;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;


    @Override
    protected String getDefaultSortBy() {
        return DEFAULT_SORT_BY;
    }

    @Override
    protected String getDefaultDirection() {
        return DEFAULT_DIRECTION;
    }
}
