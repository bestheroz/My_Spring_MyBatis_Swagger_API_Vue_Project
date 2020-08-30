package com.github.bestheroz.sample.api.member;

import com.github.bestheroz.sample.api.entity.member.TableMemberEntity;
import com.github.bestheroz.sample.api.entity.member.TableMemberRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("api/members")
@Slf4j
public class MemberController {
    @Resource private TableMemberRepository tableMemberRepository;

    @Resource private MemberRepository memberRepository;

    @GetMapping(value = "lists/codes")
    ResponseEntity<ApiResult> getItems() {
        return Result.ok(this.memberRepository.getCodeItems());
    }

    @PatchMapping(value = "{id}")
    @CacheEvict(value = "memberCache", allEntries = true)
    public ResponseEntity<ApiResult> editMe(@PathVariable(value = "id") final String id, @RequestBody final TableMemberEntity tableMemberEntity) {
        this.tableMemberRepository.updateMap(TableMemberEntity.class,
                Map.of("name", tableMemberEntity.getName()), Map.of("id", id));
        return Result.ok();
    }

    @PostMapping(value = "{id}/changePassword")
    public ResponseEntity<ApiResult> changePassword(@PathVariable(value = "id") final String id, @RequestBody final Map<String, String> payload) {
        return this.tableMemberRepository.getItem(TableMemberEntity.class, Map.of("id", id)).map(tableMemberEntity -> {
            final Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
            // 패스워드가 틀리면
            if (!pbkdf2PasswordEncoder.matches(tableMemberEntity.getPassword(), pbkdf2PasswordEncoder.encode(payload.get("oldPassword")))) {
                log.warn(ExceptionCode.FAIL_MATCH_OLD_PASSWORD.toString());
                throw new BusinessException(ExceptionCode.FAIL_MATCH_OLD_PASSWORD);
            }
            this.tableMemberRepository.updateMap(TableMemberEntity.class,
                    Map.of("password", payload.get("newPassword")), Map.of("id", id));
            return Result.ok();
        }).orElseThrow(() -> {
            // 1. 유저가 없으면
            log.warn(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER.toString());
            return new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER);
        });
    }
}
