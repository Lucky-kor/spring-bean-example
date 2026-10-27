package com.codeit.springbeanexampleV7;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
// session은 HTTP 세션마다 별도 객체를 쓰는 웹 스코프입니다.
// singleton MemberService에는 실제 세션 객체 대신 프록시(대리 객체)를 넣습니다.
// 실제 대상은 나중에 요청이 있을 때 찾습니다. 지금은 주입 구조까지만 관찰합니다.
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedBean {
}
