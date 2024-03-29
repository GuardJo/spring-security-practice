> Spring Security In Action 스터디 회고

# 1. Spring Security란?
Spring Security는 Spring 프레임워크를 통해 개발된 애플리케이션 레이어에서의 인증 및 인가 관련 처리 등을 보다 간편하고 용이하게 구성할 수 있도록 해주는 오픈소스 프레임워크이다.

기존 구식 방식의 xml 형태의 설정이 아닌 어노테이션 기반으로 Spring 기반 애플리케이션 위에서 동작하며 Spring의 dispatcher servlet에 앞, 뒷단에 추가적인 인증/인가 절차 구현이나, 비즈니스 로직 호출 전, 후 후킹 등의 작업을 보다 간단하게 구성할 수 있다.

# 2. 보안을 적용해야 하는 이유
현대 대부분의 웹, 앱 등은 웹 애플리케이션 서버에 여러 요청 및 수신 데이터를 통해 비즈니스를 제공한다.

이러한 상황에서 송수신되는 데이터 내 민감한 정보들이 포함 될 수 있으며, 이러한 민감한 데이터들을 공격자로부터 안전하게 보호하는 것은 필수적인 요소라고 볼 수 있다.

이러한 환경에서 인증 혹은 인가 등이 올바르게 적용되지 않았을 경우, 사용자는 공격자에게 자신의 민감한 데이터들을 탈취당하거나 애플리케이션 자체가 오염될 가능성이 존재한다.

> [!NOTE]
> 보안의 중요성은 끝이 없으며, 대체적으로 심각한 사태가 발생한 후 후처리하는 비용보다, 보안을 신경써서 구성하여 예방하는 것이 처리 비용이 더 적게 든다.

# 3. 웹 애플리케이션에서의 보안
웹 애플리케이션에서는 공격자로부터 다양한 취약점이 존재할 수 있으며, 이러한 취약점들을 해결하는 것을 Spring Security를 통해서 대체적으로 보완할 수 있다.
## 3-1. 보안 취약점 종류
웹 애플리케이션에서의 취약점은 크게 아래의 종류들로 이루어져 있다.
### 3-1-1. 인증과 권한 부여에 대한 취약성
웹 애플리케이션에서 인증에 따라 부여되는 권한이 사용자별로 명확하게 분리되어 있지 않을 경우, 인증만 되면 다른 사용자들의 정보에 접근할 수 있는 취약점이 발생한다.
- *그렇기에 인증 및 권한 부여 등을 명확하게 구성해놔야 함*
### 3-1-2. 세션 고정
웹 애플리케이션에서 인증 정보 등을 세션을 통해 관리할 때, 해당 세션이 고정되어 있을 경우, 세션 키를 탈취 당할 경우 다른 클라이언트에서도 해당 세션을 통한 접근이 가능해지는 취약점이 발생한다.
### 3-1-3. XSS
공격자가 웹 애플리케이션에 악의적인 스크립트가 담긴 데이터를 업로드하고, 이를 웹 애플리케이션에서 수용할 경우 해당 스크립트가 담긴 데이터에 접근하는 일반적인 사용자들이 해당 스크립트에 의해 공격받을 수 있는 취약점이 존재한다.
### 3-1-4. CSRF
공격자가 웹 애플리케이션에서 제공하는 API 등을 기존 클라이언트가 아닌 별도의 악의적인 클라이언트로 API를 호출하여 민감한 데이터를 조회하는 등의 취약점이 존재한다.
