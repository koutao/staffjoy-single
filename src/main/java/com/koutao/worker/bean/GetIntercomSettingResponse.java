package com.koutao.worker.bean;

import com.koutao.common.bean.BaseResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GetIntercomSettingResponse extends BaseResponse {
    private IntercomSettingsDto intercomSettings;
}
