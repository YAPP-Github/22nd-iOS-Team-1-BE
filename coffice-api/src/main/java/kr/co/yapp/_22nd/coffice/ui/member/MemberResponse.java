package kr.co.yapp._22nd.coffice.ui.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MemberResponse {
    @NotNull
    private Long memberId;
    @NotBlank
    private String name;
    @NotNull
    private List<AuthProviderResponse> authProviders;
}
