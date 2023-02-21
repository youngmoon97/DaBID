package project1;

import java.io.File;

import javax.swing.JButton;

public class ItemBean {
   private int itemNum;
   private String itemSeller;
   private int CategoryNum;
   private String itemName;
   private int itemPrice;
   private String itemPhoto;
   private String itemMemo;
   private int itemStatus;
   private int itemStartTime;
   private int itemEndTime;
   private int purchaserCount;
   private int allCount;
   

   
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
   public String getItemPhoto() {
      return itemPhoto;
   }
   public void setItemPhoto(String itemPhoto) {
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
   public int getItemStartTime() {
      return itemStartTime;
   }
   public void setItemStartTime(int itemStartTime) {
      this.itemStartTime = itemStartTime;
   }
   public int getItemEndTime() {
      return itemEndTime;
   }
   public void setItemEndTime(int itemEndTime) {
      this.itemEndTime = itemEndTime;
   }
   public int getPurchaserCount() {
      return purchaserCount;
   }
   public void setPurchaserCount(int purchaserCount) {
      this.purchaserCount = purchaserCount;
   }
   public int getAllCount() {
	  return allCount;
   }
   public void setAllCount(int allCount) {
	  this.allCount = allCount;
   }
   

   
   
}