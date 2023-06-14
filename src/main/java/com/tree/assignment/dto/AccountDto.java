package com.tree.assignment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountDto {
    private long Id;
    private String account_type;
    private String account_number;

}
