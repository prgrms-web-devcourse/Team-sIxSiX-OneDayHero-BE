package com.sixheroes.onedayheroapi.mission.request;

import com.sixheroes.onedayheroapplication.mission.request.MissionUpdateServiceRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MissionUpdateRequest(
        @NotNull(message = "미션의 카테고리 아이디는 필수 값 입니다.")
        Long missionCategoryId,

        @NotNull(message = "시민의 아이디는 필수 값 입니다.")
        Long citizenId,

        @NotNull(message = "지역 아이디는 필수 값 입니다.")
        Long regionId,

        @NotNull(message = "위도는 필수 값 입니다.")
        Double latitude,

        @NotNull(message = "경도는 필수 값 입니다.")
        Double longitude,

        @Valid
        MissionInfoRequest missionInfo
) {

    public MissionUpdateServiceRequest toService() {
        return MissionUpdateServiceRequest.builder()
                .missionCategoryId(missionCategoryId)
                .citizenId(citizenId)
                .regionId(regionId)
                .latitude(latitude)
                .longitude(longitude)
                .missionInfo(missionInfo.toService())
                .build();
    }
}
