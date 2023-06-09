package kr.co.yapp._22nd.coffice.infrastructure.security;

import kr.co.yapp._22nd.coffice.domain.AdminMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@Slf4j
@RequiredArgsConstructor
public class CofficeAdminOidcUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {
    private final OidcUserService oidcUserService = new OidcUserService();
    private final AdminMemberRepository adminMemberRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        var loadedUser = oidcUserService.loadUser(userRequest);
        if (!isAdminMember(loadedUser)) {
            String email = getEmail(loadedUser);
            log.warn("접근 권한이 없습니다. email: {}", email);
            throw new OAuth2AuthenticationException("접근 권한이 없습니다. email: " + email);
        }
        return loadedUser;
    }

    private boolean isAdminMember(OidcUser oidcUser) {
        String email = getEmail(oidcUser);
        if (email == null) {
            return false;
        }
        return adminMemberRepository.findByEmail(email).isPresent();
    }

    private String getEmail(OidcUser oidcUser) {
        Object email = oidcUser.getAttribute("email");
        return email == null ? "null" : email.toString();
    }
}
