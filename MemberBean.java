package project1;

public class MemberBean {
	
	private String memberId;
	private String memberName;
	private String memberPwd;
	private String memberEmail;
	private int memberPosition; //멤버 등급, 관리자 = 1 일반 회원 = 2
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public int getMemberPosition() {
		return memberPosition;
	}
	public void setMemberPosition(int memberPosition) {
		this.memberPosition = memberPosition;
	}
}
