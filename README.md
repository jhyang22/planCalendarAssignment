# **일정관리 과제 - Plan**

## ERD
+ ERD의 경우 Lucidchart(www.lucidchart.com) 을 활용하였습니다

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdzHoUI%2FbtsMWmnkYKa%2Fulj1WBWw6fl633KI8kBfu1%2Fimg.jpg">

## API 명세서

| 기능       | Method | URL              | request                                                                                                                  | response                                                                                                                                                             | 상태코드                    |
|----------|--------|------------------|--------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------|
| 일정 생성    | POST   | /plans           | 요청 body <br/> {<br/> "userId" : "작성자명",<br/>  "password" : "비밀번호",<br/>  "title" : "제목",<br/> "contents" : "할일" <br/> }  | {<br/>"planId" : "식별자",<br/> "title" : "제목",<br/> "contents" : "할일",<br/>"createdAt" : "작성일" <br/> }                                                                 | 201: 정상등록<br/>400: 요청실패 |
| 전체 일정 조회 | GET    | /plans?key=value | 요청 param <br/> "userId" : "작성자명", <br/> "updatedAt" : "수정일" <br/>* 작성자명과 수정일은 필수사항은 아닙니다                                 | [<br/>{<br/>"planId" : "식별자", <br> "userId" : "작성자명", <br/> "title" : "제목",<br/>  "contents" : "할일",<br/> "updatedAt" : "수정일",<br/> "createdAt" : "작성일"<br/> }<br/>] | 200: 정상조회<br/>400: 요청실패 |
| 특정 일정 조회 | GET    | /plans/{planId}  | 요청 param                                                                                                                 | {<br/>"planId" : "식별자", <br> "userId" : "작성자명", <br/> "title" : "제목",<br/>  "contents" : "할일",<br/> "updatedAt" : "수정일",<br/> "createdAt" : "작성일"<br/> }             | 200: 정상조회<br/>400: 요청실패 |
| 일정 수정    | PATCH  | /plans{planId}   | 요청 body <br/> {<br/> "userId" : "작성자명", <br/> "password" : "비밀번호", <br/> "title" : "제목",<br/>  "contents" : "할일" <br/> } | {<br/>"planId" : "식별자",<br/> "userId" : "작성자명",<br/> "title" : "제목",<br/>  "contents" : "할일", <br/> "updatedAt" : "수정일" <br/> }                                      | 200: 정상수정<br/>404: 요청실패 |
| 일정 삭제    | DELETE | /plans/{planId}  | 요청 param <br/>  {<br/> "password" : "비밀번호" <br/> }                                                                       |                                                                                                                                                                      | 200: 정상삭제<br/>404: 요청실패               |


## 요구사항

### LV 0
+ API 명세서 작성하기
+ ERD 작성하기
+ SQL 작성하기

### LV 1
+ 일정 생성
  + 할일, 작성자명, 비밀번호, 작성/수정일을 저장
  + 작성/수정일은 날짜와 시간을 모두 포함한 형태
  + 각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리
  + 최초 입력 시, 수정일은 작성일과 동일
+ 전체 일정 조회
  + 수정일 기준 내림차순 정렬
+ 선택 일정 조회

### LV 2
+ 선택한 일정 수정
  + 할일, 작성자명만 수정가능
  + 비밀번호 검증 필요
  + 작성일은 변경 불가능, 수정일은 수정 완료 시 수정한 시점으로 변경
+ 선택한 일정 삭제
  + 비밀번호 검증 필요

## Package 분리
+ entity, dto, controller, service, repository로 분리하였습니다
+ dto의 경우 1개의 requestDto와 3개의 responseDto로 분리하였습니다

## 구현기능
### 1. POST
+ @PostMapping 사용
+ PlanRequestDto를 두어 데이터를 JSON 형태로 받아와 DB에 저장할 수 있습니다
+ DB에 저장하는 기능은 JdbcTemplate을 이용하였습니다
+ PlanCreateResponseDto를 두어 데이터를 JSON 형태로 응답할 수 있습니다
+ DB에 저장할 때 LocalDateTime을 받아와 작성일을 표시합니다
+ 첫 작성이기 때문에 수정일도 작성일과 동일한 시간을 표시합니다
+ Post 성공 시엔 201 CREATED를 출력합니다

### 2. GET
+ @GetMapping 사용
+ PlanResponseDto를 두어 전체 일정과 식별자를 이용하여 특정 일정에 대한 데이터를 응답할 수 있습니다
+ 전체 일정 조회 시에는 수정일 기준 내림차순으로 정렬되어 응답됩니다
+ 전체 일정 조회 시 param 값으로 작성자명 혹은 수정일을 입력하여 필터링된 결과를 조회할 수 있습니다
+ Get 성공 시엔 200 OK, 데이터가 없을 시엔 404 NOT FOUND를 출력합니다

### 3. PATCH
+ @PatchMapping 사용
+ PlanUpdateResponseDto를 두어 JSON형태로 데이터를 반환합니다
+ Post했던 비밀번호가 일치할 경우 작성자명과 할일을 수정할 수 있습니다
+ 수정 시 수정일에 대한 데이터가 업데이트 됩니다
+ Patch 성공 시엔 200 OK, 입력 오류의 경우엔 400 BAD REQUEST, 데이터가 없을 시엔 404 NOT FOUND를 출력합니다

### 4. DELETE
+ @DeleteMapping 사용
+ Post했던 비밀번호와 일치할 경우 데이터를 삭제할 수 있습니다
+ Delete 성공 시엔 200 OK, 입력 오류의 경우엔 400 BAD REQUEST, 데이터가 없을 시엔 404 NOT FOUND를 출력합니다

### 5. 공통사항
+ 예외처리가 필요하다고 생각되는 부분은 예외처리를 하였습니다
+ DB 작성시에 두었던 글자수 제한을 requestBody의 각 필드에 @Size 어노테이션을 두어 해결하였습니다
