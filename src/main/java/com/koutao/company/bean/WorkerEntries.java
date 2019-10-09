package com.koutao.company.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerEntries {
    private String companyId;
    private String teamId;
    @Builder.Default
    List<DirectoryEntryDto> workers = new ArrayList<>();
}
