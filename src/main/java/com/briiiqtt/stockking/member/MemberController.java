package com.briiiqtt.stockking.member;

import com.briiiqtt.stockking.kisapi.KisApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;
    private final KisApiService kisApiService;

    @GetMapping
    public ResponseEntity<Member> getMember(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String email
    ) {
        if (id == null && email == null) {
            return ResponseEntity.badRequest().build();
        }

        Member member = null;
        if (id != null) {
            member = memberService.getMemberById(id);
        } else {
            member = memberService.getMemberByEmail(email);
        }

        if (member == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(member);
    }

    @PostMapping
    public ResponseEntity<Member> register(@RequestBody Member memberEntity) {
        Member member = memberService.register(memberEntity);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

}