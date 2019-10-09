package com.koutao.company.bean;

import com.koutao.common.bean.BaseResponse;
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
