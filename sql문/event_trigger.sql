-- // event
-- // DB에서 1초마다 갱신, endtime이 now()보다 작거나 같을 시, item_status를 1로 set, 판매 완료상태로 변경
create event if not exists timego
on schedule every 1 second
on completion preserve
DO
	update dabid.item 
	set item_status = 1
	where item_status = 2 and item_endtime <=now();
	
-- // trigger
-- // item_status가 1로 변경되면, sell table에 아이템 번호와 낙찰 경매 번호 등록
create trigger sell_end
after update
on item
for each row
begin
	declare an int(10) default 0;
	set an = (select max(a.auction_num)
				from auction a, item i
				where i.item_status = 1 and a.auction_itemnum = i.item_num);
		
	if (new.item_status != 2) then 
		insert into sell (sell_itemnum, sell_auctionnum) values (new.item_num, an);
	end if;
end

-- // sell table에 정보가 입력되고 나면, 낙찰 경매를 제외한 나머지 경매 정보를 모두 삭제
create trigger auction_delete
after insert
on sell
for each row 
begin 
	declare si int(10) default 0;
	declare sa int(10) default 0;

	set si = new.sell_itemnum;
	set sa = new.sell_auctionnum;

	delete from auction a where a.auction_itemnum = si and a.auction_num < sa;
end

-- // 경매에 입찰하면, item 항목의 item_price 또한 그 가격으로 업데이트 (현재 입찰가)
create trigger item_price_update
after insert
on auction
for each row 
begin 
	declare ip int(10) default 0;
	set ip = (select i.item_price
				from item i
				where i.item_num = new.auction_itemnum);
	if (new.auction_price > ip) then 
		update item set item_price = new.auction_price where item_num = new.auction_itemnum;
	end if;
end

-- // 경매에 입찰할 때마다 select(count())를 이용하여 현재 참여인원 계산
create trigger purchaser_count_up
after insert
on auction
for each row 
begin
	update item set purchaser_count = (select count(distinct auction_purchaser)
										from auction a
										where auction_itemnum = new.auction_itemnum)
			where item_num = new.auction_itemnum;
end