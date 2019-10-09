package com.koutao.worker.bean;

import com.koutao.common.bean.BaseResponse;
import com.koutao.company.bean.WorkerOfList;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GetWorkerOfResponse extends BaseResponse {
    private WorkerOfList workerOfList;
}
