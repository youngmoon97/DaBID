create table category (
	category_num int(10) not null, 
	category_name varchar(20) not null,
	constraint category_num_PK primary key(category_num)
);

create table `member` (
	member_id varchar(10) not null,
	member_name varchar(10) not null,
	member_pw varchar(20) not null,
	member_email varchar(70) null,
	member_position int(10) not null default '2',
	constraint member_id_PK primary key(member_id)
);

create table item (
	item_num int(10) not null auto_increment,
	item_seller varchar(10) not null,
	item_categorynum int(10) not null,
	item_name varchar(10) not null,
	item_price int(10) not null default '0',
	item_photo LONGBLOB null,
	item_memo varchar(150) null,
	item_status int(10) not null default '2',
	item_starttime datetime not null,
	item_endtime datetime not null,
	purchaser_count int(10) not null default '0',
	constraint item_num_PK primary key(item_num),
	constraint item_seller_FK foreign key (item_seller) references `member` (member_id),
	constraint item_categorynum_FK foreign key (item_categorynum) references category (category_num)
);

create table auction(
	auction_num int(10) not null auto_increment,
	auction_itemnum int(10) not null,
	auction_time datetime not null,
	auction_price int(10) not null,
	auction_purchaser varchar(10) not null,
	constraint auction_num_PK primary key(auction_num),
	constraint auction_itemnum_FK foreign key(auction_itemnum) references item(item_num),
	constraint auction_purchaser_FK foreign key(auction_purchaser) references `member`(member_id)
);

create table sell(
	sell_num int(10) not null auto_increment,
	sell_itemnum int(10) not null,
	sell_auctionnum int(10) not null,
	constraint sell_num_PK primary key(sell_num),
	constraint sell_itemnum foreign key(sell_itemnum) references item(item_num),
	constraint sell_auctionnum foreign key(sell_auctionnum) references auction(auction_num)
);

create table comment(
	comment_num int(10) not null auto_increment,
	seller_id varchar(10) not null,
	purchaser_id varchar(10) not null,
	comment_itemnum int(10) not null,
	comment_content varchar(50) not null,
	comment_time datetime not null,
	constraint comment_num_PK primary key(comment_num),
	constraint seller_id_FK foreign key(seller_id) references item(item_seller),
	constraint purchaser_id_FK foreign key(purchaser_id) references `member`(member_id),
	constraint comment_itemnum_FK foreign key(comment_itemnum) references item(item_num)
);