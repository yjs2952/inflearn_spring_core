package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();    // OrderServiceImpl 이 인터페이스(DiscountPolicy)에만 의존하는게 아니라 구체적인 클래스(FixDiscountPolicy)도 의존하고 있다 => DIP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();     // OrderServiceImpl 이 FixDiscountPolicy 에서 RateDiscountPolicy 로 의존성을 변경하려면 OrderServiceImpl 코드를 직접 수정해야 한다. => OCP 위반

    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
