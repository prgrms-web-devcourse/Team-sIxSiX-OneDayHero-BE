package com.sixheroes.onedayheroapplication.user.response;

import com.sixheroes.onedayheroapplication.region.response.RegionResponse;
import com.sixheroes.onedayherodomain.region.Region;
import com.sixheroes.onedayherodomain.user.User;
import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(
    UserBasicInfoResponse basicInfo,
    UserImageResponse image,
    UserFavoriteWorkingDayResponse favoriteWorkingDay,
    List<RegionResponse> favoriteRegions,
    Integer heroScore,
    Boolean isHeroMode
) {

    public static UserResponse from(
        User user,
        List<Region> regions
    ) {
        var userBasicInfoResponse = com.sixheroes.onedayheroapplication.user.response.UserBasicInfoResponse.from(user.getUserBasicInfo());
        var userImageResponse = user.getUserImages().stream()
            .map(UserImageResponse::from)
            .findFirst()
            .orElseGet(UserImageResponse::empty);
        var userFavoriteWorkingDayResponse = UserFavoriteWorkingDayResponse.from(user.getUserFavoriteWorkingDay());
        var favoriteRegions = regions.stream()
            .map(RegionResponse::from)
            .toList();

        return UserResponse.builder()
            .basicInfo(userBasicInfoResponse)
            .image(userImageResponse)
            .favoriteWorkingDay(userFavoriteWorkingDayResponse)
            .favoriteRegions(favoriteRegions)
            .heroScore(user.getHeroScore())
            .isHeroMode(user.getIsHeroMode())
            .build();
    }
}
