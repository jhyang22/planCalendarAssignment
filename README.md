# **planCalendarAssignment**

## API 명세서
|기능|Method|URL|request|response|상태코드|
|일정 생성|POST|/plans|요청 body { "title":"제목", "location":"위치", "contents":"할일" }|{ "location":"위치", "contents":"할일" }|200: 정상등록|
|일정 조회|GET|/plans|요청 param|{ "title":"제목", "location":"위치", "contents":"할일" }|200: 정상등록|
|일정 수정|PUT|/plans|요청 body { "title":"제목", "location":"위치", "contents":"할일" }|{ "title":"제목", "location":"위치", "contents":"할일" }|200: 정상등록|
|일정 삭제|DELETE|/plans/{date}|요청 param||200: 정상등록|
