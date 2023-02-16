package project1;

import java.sql.Date;

public class AuctionBean {
	private int auctionNum;
	private int auctionItemNum;
	private Date auctionTime;
	private int auctionPrice;
	private String auctionPurchaser;
	
	public int getAuctionNum() {
		return auctionNum;
	}
	public void setAuctionNum(int auctionNum) {
		this.auctionNum = auctionNum;
	}
	public int getAuctionItemNum() {
		return auctionItemNum;
	}
	public void setAuctionItemNum(int auctionItemNum) {
		this.auctionItemNum = auctionItemNum;
	}
	public Date getAuctionTime() {
		return auctionTime;
	}
	public void setAuctionTime(Date auctionTime) {
		this.auctionTime = auctionTime;
	}
	public int getAuctionPrice() {
		return auctionPrice;
	}
	public void setAuctionPrice(int auctionPrice) {
		this.auctionPrice = auctionPrice;
	}
	public String getAuctionPurchaser() {
		return auctionPurchaser;
	}
	public void setAuctionPurchaser(String auctionPurchaser) {
		this.auctionPurchaser = auctionPurchaser;
	}
}
