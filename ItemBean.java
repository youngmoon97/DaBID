package project1;

import java.sql.Blob;
import java.sql.Date;

public class ItemBean {
	private int itemNum;
	private String itemSeller;
	private int CategoryNum;
	private String itemName;
	private int itemPrice;
	private Blob itemPhoto;
	private String itemMemo;
	private int itemStatus;
	private Date itemStartTime;
	private Date itemEndTime;
	private int purchaserCount;
	
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public String getItemSeller() {
		return itemSeller;
	}
	public void setItemSeller(String itemSeller) {
		this.itemSeller = itemSeller;
	}
	public int getCategoryNum() {
		return CategoryNum;
	}
	public void setCategoryNum(int categoryNum) {
		CategoryNum = categoryNum;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Blob getItemPhoto() {
		return itemPhoto;
	}
	public void setItemPhoto(Blob itemPhoto) {
		this.itemPhoto = itemPhoto;
	}
	public String getItemMemo() {
		return itemMemo;
	}
	public void setItemMemo(String itemMemo) {
		this.itemMemo = itemMemo;
	}
	public int getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(int itemStatus) {
		this.itemStatus = itemStatus;
	}
	public Date getItemStartTime() {
		return itemStartTime;
	}
	public void setItemStartTime(Date itemStartTime) {
		this.itemStartTime = itemStartTime;
	}
	public Date getItemEndTime() {
		return itemEndTime;
	}
	public void setItemEndTime(Date itemEndTime) {
		this.itemEndTime = itemEndTime;
	}
	public int getPurchaserCount() {
		return purchaserCount;
	}
	public void setPurchaserCount(int purchaserCount) {
		this.purchaserCount = purchaserCount;
	}
}