package com.app.byeolbyeolsseudam.domain.banner;

import com.app.byeolbyeolsseudam.entity.banner.Banner;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class BannerDTO {
    private Long bannerId;
    private String bannerName;
    private String bannerPath;
    private String bannerUuid;

    @QueryProjection
    public BannerDTO(Long bannerId, String bannerName, String bannerPath, String bannerUuid) {
        this.bannerId = bannerId;
        this.bannerName = bannerName;
        this.bannerPath = bannerPath;
        this.bannerUuid = bannerUuid;
    }

    public Banner toEntity(){
        return Banner.builder()
                .bannerName(bannerName)
                .bannerPath(bannerPath)
                .bannerUuid(bannerUuid)
                .build();
    }
}
