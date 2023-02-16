-- //member 회원 정보
-- 관리자
INSERT INTO `member` VALUES ('admin', '관리자', 'manage12', 'admin@deu.ac.kr', '1');

-- 회원
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('aaa', '홍길동', '1234', 'aaa@naver.com');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('bbb', '뭐있지', 'password', 'bbb@gmail.com');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('ccc', '비번임', 'qlqjs', 'ccc@tistory.com');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('ddd', '끝났다', '1357', 'ddd@deu.ac.kr');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('abc', '김첨지', 'def', 'abc@deu.ac.kr');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('abcd', '귀찮아', 'rnlcksgdk', 'abcd@naver.com');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('efg', '뭐하지', 'anjgkwl', 'efg@naver.com');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('cat', '고양이', 'rhdiddl', 'cat@gmail.com');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('dog', '강아지', 'rkddkwl', 'dog@gmail.com');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('end', '살려줘', 'tkffuwnj', 'end@tistory.com');

-- //category 카테고리 정보
INSERT INTO `category` VALUES ('1', '디지털기기');
INSERT INTO `category` VALUES ('2', '의류');
INSERT INTO `category` VALUES ('3', '생활가전');
INSERT INTO `category` VALUES ('4', '스포츠/레저');
INSERT INTO `category` VALUES ('5', '취미/게임/음반');
INSERT INTO `category` VALUES ('6', '뷰티/미용');
INSERT INTO `category` VALUES ('7', '반려동물용품');
INSERT INTO `category` VALUES ('8', '가구/인테리어');
INSERT INTO `category` VALUES ('9', '차량');
INSERT INTO `category` VALUES ('10', '도서');

-- //item 판매 물품 입력 (blob 사용이 어려워 일단 null로 변경)
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('aaa', '1', '갤럭시 A9', '30000', '사용은 한 3년 한 것 같습니다. 하자 없고 포맷완료.', '2023-02-10 00:05:20', '2023-02-11 00:05:20');
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('dog', '7', '강아지 간식', '10000', '대량구매 했다가 아이 입에 맞지 않는 것 같아 팝니다ㅜㅜ', '2023-02-10 10:28:40', '2023-02-12 10:28:40');
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('efg', '8', '식탁, 의자 세트', '40000', 'oo쇼핑몰에서 8만원 주고 샀습니다. 상세컷 첨부', '2023-02-9 23:17:46', '2023-02-14 23:17:46');

-- // item test 물품들 입력(값 = 0, 판매자 = aaa, 카테고리랑 이름 등은 변경)
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('aaa', '2', 'test', 0, 'test', now(), date_add(now(), interval 10 minute));

-- // auction test 경매 입력(값 변경 해야 함)
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (4, now(), 100, 'bbb');