package kr.co.yapp._22nd.coffice.domain.search;

import java.util.List;

public interface SearchWordService {
    SearchWord create(Long memberId, String text);

    void delete(Long memberId, Long searchWordId);

    void deleteAll(Long memberId);

    List<SearchWord> findByMemberId(Long memberId);
}
