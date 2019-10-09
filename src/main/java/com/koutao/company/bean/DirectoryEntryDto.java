package com.koutao.company.bean;

import com.koutao.common.validation.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

// directory
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectoryEntryDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String internalId;
    @NotBlank
    private String companyId;
    // coming from account
    @NotBlank
    @Builder.Default
    private String name = "";
    @NotBlank
    @Email
    private String email;
    private boolean confirmedAndActive;
    @NotBlank
    @PhoneNumber
    private String phoneNumber;
    private String photoUrl;
}
