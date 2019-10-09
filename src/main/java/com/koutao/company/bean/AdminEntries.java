package com.koutao.company.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminEntries {
    private String companyId;
    @Builder.Default
    private List<DirectoryEntryDto> admins = new ArrayList<DirectoryEntryDto>();
}
