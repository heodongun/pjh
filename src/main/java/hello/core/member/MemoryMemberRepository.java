package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static final MemoryMemberRepository instance = new MemoryMemberRepository();

    // private 생성자를 사용하여 외부에서 인스턴스를 생성하지 못하게 합니다.
    public MemoryMemberRepository() {}

    public static MemoryMemberRepository getInstance() {
        return instance;
    }

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
