# **plan**

## ERD
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdzHoUI%2FbtsMWmnkYKa%2Fulj1WBWw6fl633KI8kBfu1%2Fimg.jpg">



## API 명세서

| 기능       | Method | URL             | request                                                                                                                  | response                                                                                                                                                             | 상태코드                    |
|----------|--------|-----------------|--------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------|
| 일정 생성    | POST   | /plans          | 요청 body <br/> {<br/> "userId" : "작성자명",<br/>  "password" : "비밀번호",<br/>  "title" : "제목",<br/> "contents" : "할일" <br/> }  | {<br/>"planId" : "식별자",<br/> "title" : "제목",<br/> "contents" : "할일",<br/>"createdAt" : "작성일" <br/> }                                                                 | 201: 정상등록<br/>400: 요청실패 |
| 전체 일정 조회 | GET    | /plans          | 요청 param                                                                                                                 | [<br/>{<br/>"planId" : "식별자", <br> "userId" : "작성자명", <br/> "title" : "제목",<br/>  "contents" : "할일",<br/> "updatedAt" : "수정일",<br/> "createdAt" : "작성일"<br/> }<br/>] | 200: 정상조회<br/>400: 요청실패 |
| 특정 일정 조회 | GET    | /plans/{planId} | 요청 param                                                                                                                 | {<br/>"planId" : "식별자", <br> "userId" : "작성자명", <br/> "title" : "제목",<br/>  "contents" : "할일",<br/> "updatedAt" : "수정일",<br/> "createdAt" : "작성일"<br/> }             | 200: 정상조회<br/>400: 요청실패 |
| 일정 수정    | PATCH  | /plans{planId}  | 요청 body <br/> {<br/> "userId" : "작성자명", <br/> "password" : "비밀번호", <br/> "title" : "제목",<br/>  "contents" : "할일" <br/> } | {<br/>"planId" : "식별자",<br/> "userId" : "작성자명",<br/> "title" : "제목",<br/>  "contents" : "할일", <br/> "updatedAt" : "수정일" <br/> }                                      | 200: 정상수정<br/>404: 요청실패 |
| 일정 삭제    | DELETE | /plans/{planId} | 요청 param                                                      |                                                                                                                                                                      | 200: 정상삭제<br/>404: 요청실패               |
