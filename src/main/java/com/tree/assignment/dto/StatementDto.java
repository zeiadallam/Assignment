package com.tree.assignment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StatementDto {

    private long Id;
    private String dateField;
    private String amount;
    private String accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatementDto that)) return false;
        return getId() == that.getId() && getAccountId() == that.getAccountId();
    }

}
