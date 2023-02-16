package project1;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class AuctionMgr {
	
	private DBConnectionMgr pool;
	
	public AuctionMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	//
	
	//회원가입
	public void joinMember(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "insert member (member_ID, member_PW, member_Email)"
					+ "values (?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getMemberId());
			pstmt.setString(2, bean.getMemberPwd());
			pstmt.setString(3, bean.getMemberEmail());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	//로그인
	public boolean loginChk(String memberId, String memberPwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			sql = "select count(member_ID) from member"
					+"where member_ID = ? and member_PW = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			rs = pstmt.executeQuery();
			if(rs.next()&&rs.getInt(1)==1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	//id search
	public Vector<MemberBean> getMemberId(String memberName, String memberEmail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<MemberBean> mID = new Vector<MemberBean>();
		try {
			con = pool.getConnection();
			sql = "select member_ID from member"
					+ "where member_name = ? and member_Email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberEmail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				MemberBean bean = new MemberBean();
				bean.setMemberId(rs.getString("member_ID"));
				mID.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return mID;
	}
	
	//password search
	public Vector<MemberBean> getMemberPw(String memberId, String memberName, String memberEmail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<MemberBean> mPW = new Vector<MemberBean>();
		try {
			con = pool.getConnection();
			sql = "select member_PW from member where member_ID = ?"
					+ "and member_name = ? and member_Email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberName);
			pstmt.setString(3, memberEmail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				MemberBean bean = new MemberBean();
				bean.setMemberPwd(rs.getString("member_PW"));
				mPW.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return mPW;
	}
	
		//핫 경매 아이템 정보 출력
	   //만약 참여 인원 수가 동일하다면, 더 높은 입찰가의 아이템 정보 출력
	   public Vector<ItemBean> getHotItem(){
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = null;
	      Vector<ItemBean> Hitem = new Vector<ItemBean>();
	      try {
	         con = pool.getConnection();
	         sql = "select item_name, item_photo, item_memo, item_price, purchaser_count"
	               + "from item where item_status = 1"
	               + "and purchaser_count = (select max(purchaser_count) from item)"
	               + "and item_price = (select max(item_price) from item);";
	         pstmt = con.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
	            ItemBean bean = new ItemBean();
	            bean.setItemName(rs.getString("item_name"));
	            bean.setItemPhoto(rs.getBlob("item_photo"));
	            bean.setItemMemo(rs.getString("item_memo"));
	            bean.setItemPrice(rs.getInt("item_price"));
	            bean.setPurchaserCount(rs.getInt("purchaser_count"));
	            Hitem.addElement(bean);
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         pool.freeConnection(con, pstmt, rs);
	      }
	      return Hitem;
	   }
	//아이템 등록
	public void insertItem(ItemBean Ibean, MemberBean Mbean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();//카테고리번호 인덱스로 받기 즉, 카테고리인덱스랑 카테고리이름이랑 일치해야함
			sql = "insert item (item_seller, item_categorynum, item_name, item_price,"
					+"item_photo, item_memo, item_starttime, item_endtime)"
					+"values (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Mbean.getMemberId());
			pstmt.setInt(2, Ibean.getCategoryNum()); 
			pstmt.setString(3, Ibean.getItemName());
			pstmt.setInt(4, Ibean.getItemPrice());
			pstmt.setBlob(5, Ibean.getItemPhoto());
			pstmt.setString(6, Ibean.getItemMemo());
			pstmt.setDate(7, Ibean.getItemStartTime()); //버튼 누르면 그 시간 넣고
			pstmt.setDate(8, Ibean.getItemEndTime()); //starttime에서 더하기
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//아이템 list(카테고리별)
	public Vector<ItemBean> getItemList(Integer ItemCategoryNum){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ItemBean> ilist = new Vector<ItemBean>();
		try {
			con = pool.getConnection();
			sql = "select  item_photo, item_name, item_price, item_endtime, purchaser_count"
					+ "from item i"
					+ "where item_status = 2 and item_categorynum = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ItemCategoryNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setItemPhoto(rs.getBlob("item_photo"));
				bean.setItemName(rs.getString("item_name"));
				bean.setItemPrice(rs.getInt("item_price"));
				bean.setItemEndTime(rs.getDate("item_endtime"));
				bean.setPurchaserCount(rs.getInt("purchaser_count"));
				ilist.addElement(bean);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return ilist;
	}
	
	//아이템 리스트(마이페이지 판매한 상품 - member_id = item_seller)
	public Vector<ItemBean> getSellItemList(String itemSeller){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ItemBean> SIlist = new Vector<ItemBean>();
		try {
			con = pool.getConnection();
			sql = "select item_name, item_photo, item_price,  purchaser_count,item_endtime from item "
					+ "where item_status = 1 and item_seller = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, itemSeller);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ItemBean bean = new ItemBean();
				
				bean.setItemName(rs.getString("item_name"));
				bean.setItemPhoto(rs.getBlob("item_photo"));
				bean.setItemPrice(rs.getInt("item_price"));				
				bean.setPurchaserCount(rs.getInt("purchaser_count"));
				bean.setItemEndTime(rs.getDate("item_endtime"));
				SIlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return SIlist;
	}
	
	//아이템 리스트(마이페이지 구매한 상품 - join 필요할 것 같음)
	public Vector<ItemBean> getBuyItemList(String auctionPurchaser){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ItemBean> BIlist = new Vector<ItemBean>();
		try {
			con = pool.getConnection();
			sql = "select i.item_name, i.item_price, i.item_photo,i.item_endtime"
					+ " from auction a, item i where i.item_status = 1 and"
					+ "a.auction_itemnum = i.item_num and a.auction_purchaser = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auctionPurchaser);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setItemName(rs.getString("item_name"));
				bean.setItemPrice(rs.getInt("item_price"));
				bean.setItemPhoto(rs.getBlob("item_photo"));
				bean.setItemEndTime(rs.getDate("item_endtime"));
				BIlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return BIlist;
	}
	
	//아이템 리스트(마이페이지 경매 참여 상품 - auction)
	public Vector<ItemBean> getIngItemList(String itemPurchaser){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ItemBean> SIlist = new Vector<ItemBean>();
		try {
			con = pool.getConnection();
			sql = "select  i.item_name, i.item_price, i.item_photo,i.item_endtime "
					+ "from item i, auction a "
					+ "where i.item_num = a.auction_itemnum "
					+ "and item_status = 2 and auction_purchaser = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, itemPurchaser);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setItemName(rs.getString("item_name"));
				bean.setItemPhoto(rs.getBlob("item_photo"));
				bean.setItemPrice(rs.getInt("item_price"));				
				bean.setPurchaserCount(rs.getInt("purchaser_count"));
				bean.setItemEndTime(rs.getDate("item_endtime"));
				SIlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return SIlist;
	}
	// comment 전송버튼 
	public void commentSend(CommentBean Cbean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "insert comment(seller_id, purchaser_id, comment_itemnum, comment_content, comment_time) "
					+"values (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Cbean.getSellerId());
			pstmt.setString(2, Cbean.getPurchaserId());
			pstmt.setInt(3, Cbean.getCommentItemNum());
			pstmt.setString(4, Cbean.getCommentContent());
			pstmt.setDate(5,  Cbean.getCommentTime());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	//comment list(댓글 - 판매상품별)
	public Vector<CommentBean> getCommentList(Integer commentItemNum){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<CommentBean> clist = new Vector<CommentBean>();
		try {
			con = pool.getConnection();
			sql = "select seller_id, purchaser_id, comment_time, comment_content"
					+ "from comment where comment_itemnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commentItemNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentBean bean = new CommentBean();
				bean.setSellerId(rs.getString("seller_id"));
				bean.setPurchaserId(rs.getString("purchaser_id"));
				bean.setCommentTime(rs.getDate("comment_time"));
				bean.setCommentContent(rs.getString("comment_content"));
				clist.addElement(bean);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return clist;
	}

}
