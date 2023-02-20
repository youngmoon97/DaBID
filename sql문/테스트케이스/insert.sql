-- //member 회원 정보
-- 관리자
INSERT INTO `member` VALUES ('admin', '관리자', 'manage12', 'admin@deu.ac.kr', '1');

-- 회원
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('tester1', '조영문', '1234', 'aaa@naver.com');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('tester2', '이상미', '1234', 'bbb@gmail.com');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('tester3', '하서영', '1234', 'ccc@gmail.com');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('tester4', '서지우', '1234', 'ddd@daum.net');
INSERT INTO `member` (member_id, member_name, member_pw, member_email)
VALUES ('tester5', '이상목', '1234', 'eee@gmail.com');


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
VALUES ('tester1', '1', '갤럭시 A9', '30000', '사용은 한 3년 한 것 같습니다. 하자 없고 포맷완료.', '2023-02-10 00:05:20', '2023-02-11 00:05:20');
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester2', '7', '강아지 간식', '10000', '대량구매 했다가 아이 입에 맞지 않는 것 같아 팝니다ㅜㅜ', '2023-02-10 10:28:40', '2023-02-12 10:28:40');
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester3', '8', '식탁, 의자 세트', '40000', 'oo쇼핑몰에서 8만원 주고 샀습니다. 상세컷 첨부', '2023-02-9 23:17:46', '2023-02-14 23:17:46');

-- // item test 물품들 입력(값 = 0, 판매자 = aaa, 카테고리랑 이름 등은 변경)
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester1', '1', 'testitem1', 1000, 'testitemmemo1', now(), date_add(now(), interval 30 day));
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester2', '2', 'testitem2', 2000, 'testitemmemo2', now(), date_add(now(), interval 5 day));
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester3', '3', 'testitem3', 3000, 'testitemmemo3', now(), date_add(now(), interval 1 day));
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester4', '4', 'testitem4', 4000, 'testitemmemo4', now(), date_add(now(), interval 5 minute));
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester5', '5', 'testitem5', 5000, 'testitemmemo5', now(), date_add(now(), interval 1 minute));
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('조영문', '1', '아이폰123123', 10000000,
'SVG를 사용하는 것 외에, 오늘날 개개인이 가지고 있는 방대한 화면 크기와 해상도를 고려할 때 사이트 모든 방문자가 동일한 이미지 파일을 받아야 하는 경우는 거의 없습니다.
귀하의 사이트는 시계로도 작동합니까?'
, now(), date_add(now(), interval 30 day));

INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester3', '3', 'testitem3', 3000, 'testitemmemo3', now(), date_add(now(), interval 1 day));
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester4', '4', 'testitem4', 4000, 'testitemmemo4', now(), date_add(now(), interval 5 minute));INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester3', '3', 'testitem3', 3000, 'testitemmemo3', now(), date_add(now(), interval 1 day));
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester4', '4', 'testitem4', 4000, 'testitemmemo4', now(), date_add(now(), interval 5 minute));INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester3', '3', 'testitem3', 3000, 'testitemmemo3', now(), date_add(now(), interval 1 day));
INSERT INTO item(item_seller, item_categorynum, item_name, item_price, item_memo, item_starttime, item_endtime)
VALUES ('tester4', '4', 'testitem4', 4000, 'testitemmemo4', now(), date_add(now(), interval 5 minute));
-- // auction test 경매 입력(값 변경 해야 함)
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (5, now(), 1100, 'tester4');
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (2, now(), 2200, 'tester3');
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (3, now(), 3300, 'tester2');
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (4, now(), 4400, 'tester1');
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (5, now(), 4500, 'tester2');
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (5, now(), 4700, 'tester3');
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (5, now(), 4900, 'tester4');

INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (5, now(), 5000, 'tester2');
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (5, now(), 6000, '123');
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (5, now(), 7000, '아이디1');
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (5, now(), 8000, '이상목');

INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (3, now(), 3700, 'tester1');
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (3, now(), 3800, 'tester3');
INSERT INTO auction (auction_itemnum, auction_time, auction_price, auction_purchaser)
VALUES (3, now(), 3800, 'tester4');
use dabid;
-- sell insert 


alter table item auto_increment = 1;
delete from item;


select count(member_id) from member
where member_id = 'tester1' and member_pw = '1234';

select item_name, item_photo, item_memo, item_price, purchaser_count
from item 
where item_status = 2 
	and purchaser_count = (select max(purchaser_count) from item);


select item_name, item_photo, item_memo, item_price, purchaser_count
from item 
where item_status = 2 and purchaser_count = (select max(purchaser_count) from item)
order by item_price desc
limit 1;

select category_name 
from category c;
