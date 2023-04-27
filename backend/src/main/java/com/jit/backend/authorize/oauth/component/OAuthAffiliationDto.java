package com.jit.backend.authorize.oauth.component;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User DTO")
public class OAuthAffiliationDto {

    @Schema(description = "소속 회사", example = "CarFactory")
    private String factoryName;
}
