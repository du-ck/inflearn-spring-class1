package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    //서비스 클래스는 비즈니스 용어에 가깝게 네이밍 해야함
    //repository 클래스는 기계적으로, 개발스럽게 네이밍

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        checkDulplicate(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void checkDulplicate(Member member) {
        //동명이인 X
        //Optional<Member> result = memberRepository.findByName(member.getName());
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("중복 회원입니다.");
                        });
    }

    /**
     * 전체회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
