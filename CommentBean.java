package project1;

import java.sql.Date;

public class CommentBean {
	private int commentNum;
	private String sellerId;
	private String purchaserId;
	private int commentItemNum;
	private String commentContent;
	private Date commentTime;
	
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getPurchaserId() {
		return purchaserId;
	}
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}
	public int getCommentItemNum() {
		return commentItemNum;
	}
	public void setCommentItemNum(int commentItemNum) {
		this.commentItemNum = commentItemNum;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	
}
