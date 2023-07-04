package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import kr.co.yapp._22nd.coffice.domain.member.name.NameGenerationService;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderCreateVo;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final PlaceFolderService placeFolderService;
    private final NameGenerationService nameGenerationService;

    @Override
    @Transactional
    public Member join(String authProviderUserId) {
        String name;
        do {
            name = nameGenerationService.generateRandonName();
        } while (memberRepository.existsByName(name));
        Member newMember = Member.from(MemberCreateVo.of(name, AuthProviderCreateVo.of(AuthProviderType.ANONYMOUS, authProviderUserId)));
        memberRepository.save(newMember);
        placeFolderService.create(newMember.getMemberId(), PlaceFolderCreateVo.defaultFolder());
        return newMember;
    }
}
