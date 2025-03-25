use plans;

CREATE TABLE plans
(
    plan_id    bigint auto_increment  primary key comment '식별자',
    user_id    varchar(10) not null comment '작성자 아이디',
    password   varchar(10) not null comment '비밀번호',
    title      varchar(10) not null comment '제목',
    contents   varchar(50) not null comment '할일',
    created_at timestamp   null comment '작성일',
    updated_at timestamp   null comment '수정일'
);
