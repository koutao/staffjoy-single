package com.koutao.worker.bean;

import com.koutao.company.bean.AdminOfList;
import com.koutao.company.bean.WorkerOfList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IAmDto {
    private boolean support;
    private String userId;
    private WorkerOfList workerOfList;
    private AdminOfList adminOfList;
}
