# **planCalendarAssignment**

## API 명세서
|기능|Method|URL|request|response|상태코드|
|----|----|----|----|----|-----|
|일정 생성|POST|/plans|요청 body <br/> {<br/> "userId":"작성자아이디",<br/>  "password":Integer비밀번호,<br/>  "title":"제목",<br/>  "location":"위치",<br/>  "contents":"할일" <br/> }|{<br/>  "location":"위치", <br/> "contents":"할일" <br/> }|200: 정상등록|
|전체 일정 조회|GET|/plans|요청 param|{<br/> "date":날짜, <br/> "title":"제목",<br/>  "location":"위치",<br/>  "contents":"할일" <br/> }|200: 정상등록|
|특정 날짜 조회|GET|/plans/{date}|요청 param|{<br/> "date":날짜,  <br/> "title":"제목",<br/>  "location":"위치",<br/>  "contents":"할일" <br/> }|200: 정상등록|
|일정 수정|PUT|/plans|요청 body <br/> {<br/> "userId":"작성자아이디", <br/> "password":Integer비밀번호, <br/> "title":"제목",<br/>  "location":"위치",<br/>  "contents":"할일" <br/> }|{<br/>  "title":"제목",<br/>  "location":"위치",<br/>  "contents":"할일" <br/> }|200: 정상등록|
|특정 날짜 일정 수정|PATCH|/plans/{date}|요청 body <br/> {<br/> "userId":"작성자아이디", <br/>"password":Integer비밀번호, <br/>"contents
|일정 삭제|DELETE|/plans/{date}|요청 param| |200: 정상등록|
