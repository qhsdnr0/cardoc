![header](https://capsule-render.vercel.app/api?type=Slice&color=auto&height=350&section=header&text=%20%20Cardoc&fontSize=90&textBg=true)

<br/>
<br>


# 원티드x위코드 백엔드 프리온보딩 과제 7.

<br>

<br>
<br>
<br>


> ## 사용 기술
> <br>

<div align="center">

  ![Java](https://img.shields.io/badge/Java-3670A0?style=for-the-badge&logo=java&logoColor=ffdd54)ㅤ![Springboot](https://img.shields.io/badge/springboot-%23092E20.svg?style=for-the-badge&logo=springboot&logoColor=white)ㅤ![MySQL](https://img.shields.io/badge/MySQL-%2307405e.svg?style=for-the-badge&logo=MySQL&logoColor=white)ㅤ![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)ㅤ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)ㅤ![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

</div>

<br>
<br>
<br>

> ## 과제 내용

<br>

ㅤ아래 요구사항에 맞춰 차량관련 Restfull API를 개발합니다.

<br>

### ▶︎ 개발 내용

✔️ API 목록
- 사용자 생성 API
- 사용자가 소유한 타이어 정보 저장 API
- 사용자가 소유한 타이어 정보 조회 API

<br>

**✔️ 주요 고려 사항은 다음과 같습니다.**
- 데이터베이스 관련처리는 raw query가 아닌 ORM을 이용하여 구현
- Response Codes API를 성공적으로 호출할 경우 200번 코드를 반환하고, 그 외의 경우에는 아래의 코드로 반환

| Response Code             | Description                          |
| ------------------------- | ------------------------------------ |
| 200 OK                    | 성공                                 |
| 400 Bad Request           | Parameter가 잘못된 경우(범위, 값 등) |
| 401 Unauthorized          | 인증을 위한 Header가 잘못된 경우     |
| 500 Internal Server Error | 기타 서버 에러                       |

<br>

**✔️  사용자 생성 API**

- ID/Password로 사용자를 생성하는 API
- 인증 토큰을 발급하고 이후의 API는 인증된 사용자만 호출할 수 있다.

<br>

**✔️  사용자가 소유한 타이어 정보를 저장하는 API**

- 자동차 차종을 이용하여 사용자가 소유한 자동차 정보 저장
- 타이어 정보는 {타이어 폭}/{편평비}R{휠 사이즈}의 포맷으로 입력되며 이 데이터를 서로다른 Column에 저장

<br>

**✔️  사용자가 소유한 타이어 정보를 조회하는 API**

- 사용자 ID를 통해서 저장한 타이어 정보를 조회할 수 있어야 한다.

<br/>
<br/>
<br/>

> ## 모델링

<br/>

<div align="center">

![image-20211220144118678](/home/kbw/.config/Typora/typora-user-images/image-20211220144118678.png)

</div>

<br/>
<br/>
<br>


> ## 구현 기능

<br>

### ▶︎ 회원가입/로그인
- 계정 account, 비밀번호 password로 신규 회원가입을 할 수 있도록 설계했습니다.
- 회원가입 시 비밀번호는 Bcrypt를 통해 암호화되어 저장되며 
- 로그인 성공 시 인증 토큰을 발급한다.

<br>

### ▶︎ 사용자의 타이어 정보 저장

- 차종을 통해 사용자가 소유한 자동차 정보를 저장한다.
- 추가로 타이어정보를 입력받아 사용자가 소유한 타이어 정보를 저장한다.

<br>

### ▶︎ 사용자의 타이어 정보 조회
- 사용자 ID를 통해서 저장한 타이어 정보를 조회한다.

<br>

<br>
<br>

### ▶︎ UnitTest(업데이트 중)
- 각각의 메소드를 테스트하는 UnitTest코드는 현재 작성중입니다.




> ## API Document & Test

<br>

1. [Postman API 문서 링크](https://documenter.getpostman.com/view/18218753/UVRAK7oK)로  접속해 우측 상단의 `Run in Postman` 버튼을 클릭합니다.
2. 개인 Workspace로 Import 합니다.
3. hostname 환경변수를 localhost로 선택합니다.
5. API 문서 예시를 참고해 Request를 보냅니다.

<br/>
<br>
<br>

> ## 폴더 구조

<br>

```bash
.
├── cardoc
│   ├── build
│   │   ├── classes
│   │   │   └── java
│   │   │       ├── main
│   │   │       │   └── Cardoc
│   │   │       │       └── cardoc
│   │   │       │           ├── CardocApplication.class
│   │   │       │           ├── controller
│   │   │       │           │   ├── CarController.class
│   │   │       │           │   ├── CarForm.class
│   │   │       │           │   ├── TireController.class
│   │   │       │           │   ├── TireForm.class
│   │   │       │           │   ├── TrimController.class
│   │   │       │           │   ├── TrimForm.class
│   │   │       │           │   ├── TrimTireForm.class
│   │   │       │           │   ├── UserController.class
│   │   │       │           │   └── UserForm.class
│   │   │       │           ├── exception
│   │   │       │           │   ├── BadRequestException.class
│   │   │       │           │   └── UnauthorizedException.class
│   │   │       │           ├── models
│   │   │       │           │   ├── Car.class
│   │   │       │           │   ├── Tire.class
│   │   │       │           │   ├── TireInfo.class
│   │   │       │           │   ├── Trim.class
│   │   │       │           │   ├── User.class
│   │   │       │           │   └── UserTrimTire.class
│   │   │       │           ├── repository
│   │   │       │           │   ├── CarRepository.class
│   │   │       │           │   ├── TireRepository.class
│   │   │       │           │   ├── TrimRepository.class
│   │   │       │           │   └── UserRepository.class
│   │   │       │           ├── service
│   │   │       │           │   ├── CarService.class
│   │   │       │           │   ├── TireService.class
│   │   │       │           │   ├── TrimService.class
│   │   │       │           │   └── UserService.class
│   │   │       │           └── util
│   │   │       │               ├── Encryption.class
│   │   │       │               ├── Token.class
│   │   │       │               └── Validation.class
│   │   │       └── test
│   │   │           └── Cardoc
│   │   │               └── cardoc
│   │   │                   ├── CardocApplicationTests.class
│   │   │                   └── service
│   │   │                       └── UserServiceTest.class
│   │   ├── generated
│   │   │   └── sources
│   │   │       ├── annotationProcessor
│   │   │       │   └── java
│   │   │       │       ├── main
│   │   │       │       └── test
│   │   │       └── headers
│   │   │           └── java
│   │   │               ├── main
│   │   │               └── test
│   │   ├── reports
│   │   │   └── tests
│   │   │       └── test
│   │   │           ├── css
│   │   │           │   ├── base-style.css
│   │   │           │   └── style.css
│   │   │           ├── index.html
│   │   │           └── js
│   │   │               └── report.js
│   │   ├── resources
│   │   │   ├── main
│   │   │   │   ├── application.properties
│   │   │   │   ├── static
│   │   │   │   └── templates
│   │   │   └── test
│   │   │       ├── application.properties
│   │   │       ├── static
│   │   │       └── templates
│   │   ├── test-results
│   │   │   └── test
│   │   │       └── binary
│   │   │           ├── output.bin
│   │   │           ├── output.bin.idx
│   │   │           └── results.bin
│   │   └── tmp
│   │       ├── compileJava
│   │       │   └── previous-compilation-data.bin
│   │       ├── compileTestJava
│   │       │   └── previous-compilation-data.bin
│   │       └── test
│   ├── build.gradle
│   ├── gradle
│   │   └── wrapper
│   │       ├── gradle-wrapper.jar
│   │       └── gradle-wrapper.properties
│   ├── gradlew
│   ├── gradlew.bat
│   ├── HELP.md
│   ├── settings.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── Cardoc
│       │   │       └── cardoc
│       │   │           ├── CardocApplication.java
│       │   │           ├── controller
│       │   │           │   ├── CarController.java
│       │   │           │   ├── CarForm.java
│       │   │           │   ├── TireController.java
│       │   │           │   ├── TireForm.java
│       │   │           │   ├── TrimController.java
│       │   │           │   ├── TrimForm.java
│       │   │           │   ├── TrimTireForm.java
│       │   │           │   ├── UserController.java
│       │   │           │   └── UserForm.java
│       │   │           ├── exception
│       │   │           │   ├── BadRequestException.java
│       │   │           │   └── UnauthorizedException.java
│       │   │           ├── models
│       │   │           │   ├── Car.java
│       │   │           │   ├── TireInfo.java
│       │   │           │   ├── Tire.java
│       │   │           │   ├── Trim.java
│       │   │           │   ├── User.java
│       │   │           │   └── UserTrimTire.java
│       │   │           ├── repository
│       │   │           │   ├── CarRepository.java
│       │   │           │   ├── TireRepository.java
│       │   │           │   ├── TrimRepository.java
│       │   │           │   └── UserRepository.java
│       │   │           ├── service
│       │   │           │   ├── CarService.java
│       │   │           │   ├── TireService.java
│       │   │           │   ├── TrimService.java
│       │   │           │   └── UserService.java
│       │   │           └── util
│       │   │               ├── Encryption.java
│       │   │               ├── Token.java
│       │   │               └── Validation.java
│       │   └── resources
│       │       ├── application.properties
│       │       ├── static
│       │       └── templates
│       └── test
│           ├── java
│           │   └── Cardoc
│           │       └── cardoc
│           │           ├── CardocApplicationTests.java
│           │           └── service
│           │               └── UserServiceTest.java
│           └── resources
│               └── application.properties
└── c:\trim.json
```

<br/>
<br/>
<br/>

# Reference
이 프로젝트는 원티드x위코드 백엔드 프리온보딩 과제 일환으로 Cardoc에서 출제한 과제를 기반으로 만들었습니다. 
    
