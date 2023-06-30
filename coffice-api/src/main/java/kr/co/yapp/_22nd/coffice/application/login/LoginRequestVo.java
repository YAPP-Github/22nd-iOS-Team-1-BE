package kr.co.yapp._22nd.coffice.application.login;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import lombok.Value;

@Value(staticConstructor = "of")
public class LoginRequestVo {
    AuthProviderType authProviderType;
    String authProviderUserId;
}
