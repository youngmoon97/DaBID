package project1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Member;
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
   public void joinMember(String memId, String memName,String memPw,String memEmail) {
      Connection con = null;
      PreparedStatement pstmt = null;
      String sql = null;
      
      try {
         con = pool.getConnection();
         sql = "insert into member (member_id, member_name, member_pw, member_email)"
               + "values (?, ?, ?, ?)";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, memId);
         pstmt.setString(2, memName);
         pstmt.setString(3, memPw);
         pstmt.setString(4, memEmail);
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
         sql = "select count(member_ID) from member "
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
   public MemberBean getMemberId(String memberName, String memberEmail) {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      MemberBean bean = new MemberBean();

      try {
         con = pool.getConnection();
         sql = "select member_id from member "
               + "where member_name = ? and member_email = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, memberName);
         pstmt.setString(2, memberEmail);
         rs = pstmt.executeQuery();
         if(rs.next()) {
            bean.setMemberId(rs.getString("member_id"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         pool.freeConnection(con, pstmt, rs);
      }
      return bean;
   }
   
   //password search
   public MemberBean getMemberPw(String memberName, String memberId, String memberEmail) {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      MemberBean bean = new MemberBean();
      try {
         con = pool.getConnection();
         sql = "select member_pw from member where member_name = ?"
               + "and member_id = ? and member_Email = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, memberName);
         pstmt.setString(2, memberId);
         pstmt.setString(3, memberEmail);
         rs = pstmt.executeQuery();
         if(rs.next()) {
            bean.setMemberPwd(rs.getString("member_PW"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         pool.freeConnection(con, pstmt, rs);
      }
      return bean;
   }
   //포지션 검사
   public MemberBean getPosition(String memberId) {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = null;
	      MemberBean bean = new MemberBean();

	      try {
	         con = pool.getConnection();
	         sql = "select member_position from member "
	               + "where member_id = ?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, memberId);
	         rs = pstmt.executeQuery();
	         if(rs.next()) {
	            bean.setMemberPosition(rs.getInt("member_position"));
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         pool.freeConnection(con, pstmt, rs);
	      }
	      return bean;
	   }
   //카테고리이름
   public CategoryBean getHotItem(int categoryNum){
       Connection con = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       String sql = null;
       CategoryBean cbean = new CategoryBean();
       try {
          con = pool.getConnection();
          sql = "select category_name "
          		+ "from category "
          		+ "where category_num = ? ";
          pstmt = con.prepareStatement(sql);
          rs = pstmt.executeQuery();
          if(rs.next()) {
             cbean.setCategoryName(rs.getString("category_name"));
          }
       } catch (Exception e) {
          e.printStackTrace();
       }finally {
          pool.freeConnection(con, pstmt, rs);
       }
       return cbean;
    }
      //핫 경매 아이템 정보 출력
      //만약 참여 인원 수가 동일하다면, 더 높은 입찰가의 아이템 정보 출력
      public ItemBean getHotItem(){
         Connection con = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         String sql = null;
         ItemBean ibean = new ItemBean();
         try {
            con = pool.getConnection();
            sql = "select item_seller, item_num, item_name, item_photo, item_memo, item_price, purchaser_count, item_endtime "
            		+ "from item "
            		+ "where purchaser_count = (select max(purchaser_count) from item where item_status=2) "
            		+ "order by item_price desc limit 1";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	ibean.setItemSeller(rs.getString("item_seller"));
            	ibean.setItemNum(rs.getInt("item_num"));
            	ibean.setItemName(rs.getString("item_name"));
            	ibean.setItemMemo(rs.getString("item_memo"));
            	ibean.setItemPrice(rs.getInt("item_price"));
            	ibean.setPurchaserCount(rs.getInt("purchaser_count"));
//            	ibean.setItemEndTime(rs.getInt());
            }
         } catch (Exception e) {
            e.printStackTrace();
         }finally {
            pool.freeConnection(con, pstmt, rs);
         }
         return ibean;
      }
    //옥션 -  아이템 정보가져오기
      public ItemBean getItem(Integer itemNum){
         Connection con = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         String sql = null;
         ItemBean ibean = new ItemBean();
         try {
            con = pool.getConnection();
            sql = "select item_seller, item_num, item_name, item_photo, item_memo, item_price, purchaser_count, item_endtime "
            		+ "from item "
            		+ "where item_num = ? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, itemNum);
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	ibean.setItemSeller(rs.getString("item_seller"));
            	ibean.setItemNum(rs.getInt("item_num"));
            	ibean.setItemName(rs.getString("item_name"));
            	ibean.setItemMemo(rs.getString("item_memo"));
            	ibean.setItemPrice(rs.getInt("item_price"));
            	ibean.setPurchaserCount(rs.getInt("purchaser_count"));
            	//ibean.setItemEndTime(rs.getInt("item_endtime"));
            }
         } catch (Exception e) {
            e.printStackTrace();
         }finally {
            pool.freeConnection(con, pstmt, rs);
         }
         return ibean;
      }
   
      //이미지가져오기
      public File selectImg(String itemName) {
  		Connection con = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		String sql = null;
  		File file = null;
  		InputStream is = null;
  		FileOutputStream fos = null;
  		try {
  			con = pool.getConnection();
  			sql = "select item_photo from item where item_name = ?";
  			pstmt = con.prepareStatement(sql);
  			pstmt.setString(1, itemName);
  			rs = pstmt.executeQuery();
  			
  			if(rs.next()) {
  				file =  new File("./image/"+rs.getString("item_name")+".jpg");
  				fos = new FileOutputStream(file);
  				is = rs.getBinaryStream("img");
  				int i;
  				while((i=is.read())!=-1) {
  					fos.write(i);
  				}
  			}
  			if(fos!=null) fos.close();
  			if(is!=null) is.close();
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  			pool.freeConnection(con, pstmt, rs);
  		}
  		return file;
  	}
    //아이템 등록
      public void insertItem(String sellId, int categoryNum, String itemname,int itemprice, File itemphoto, String itemmemo,int time) {
         Connection con = null;
         PreparedStatement pstmt = null;
         String sql = null;   
         try {
            con = pool.getConnection();//카테고리번호 인덱스로 받기 즉, 카테고리인덱스랑 카테고리이름이랑 일치해야함
            sql = "insert into item (item_seller, item_categorynum, item_name, item_price, item_photo, item_memo, item_starttime, item_endtime) "
                  + "values (?,?,?,?,?,?, now(), date_add(now(), interval ? minute))";
            pstmt = con.prepareStatement(sql);
            InputStream is = new FileInputStream(itemphoto);
            pstmt.setString(1, sellId);
            pstmt.setInt(2, categoryNum); 
            pstmt.setString(3, itemname);
            pstmt.setInt(4, itemprice);
            pstmt.setBinaryStream(5, is);
            pstmt.setString(6, itemmemo);
            pstmt.setInt(7, time);
            pstmt.executeUpdate();
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            pool.freeConnection(con, pstmt);
         }
      }
      //아이템 list(카테고리별)
      public Vector<ItemBean> getItemList(String ItemCategoryName){
         Connection con = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         String sql = null;
         Vector<ItemBean> ilist = new Vector<ItemBean>();
         try {
            con = pool.getConnection();
            sql = "select item_num, item_name, item_price, purchaser_count,timestampdiff(second, now(), item_endtime) "
            		+ "from item i , category c "
            		+ "where i.item_categorynum = c.category_num "
            		+ "and c.category_name  = ? "
            		+ "and i.item_status = 2";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ItemCategoryName);
            rs = pstmt.executeQuery();
            while(rs.next()) {
               ItemBean bean = new ItemBean();
               bean.setItemNum(rs.getInt("item_num"));
               bean.setItemName(rs.getString("item_name"));
               bean.setItemPrice(rs.getInt("item_price"));
               bean.setPurchaserCount(rs.getInt("purchaser_count"));
               bean.setItemEndTime(rs.getInt("timestampdiff(second, now(), item_endtime)"));
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
      //TODO
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
//            bean.setItemPhoto(rs.getBlob("item_photo"));
            bean.setItemPrice(rs.getInt("item_price"));            
            bean.setPurchaserCount(rs.getInt("purchaser_count"));
            bean.setItemEndTime(rs.getInt("item_endtime"));
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
   //TODO
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
//            bean.setItemPhoto(rs.getBlob("item_photo"));
            bean.setItemEndTime(rs.getInt("item_endtime"));
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
   //TODO
   public Vector<ItemBean> getIngItemList(String itemPurchaser){
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      Vector<ItemBean> SIlist = new Vector<ItemBean>();
      try {
         con = pool.getConnection();
         sql = "select item_name, item_photo, item_memo, item_price, purchaser_count,timestampdiff(second, now(), item_endtime) "
         		+ "from item "
         		+ "where purchaser_count = (select max(purchaser_count) from item where item_status=2) "
         		+ "order by item_price desc limit 1";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, itemPurchaser);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            ItemBean bean = new ItemBean();
            bean.setItemName(rs.getString("item_name"));
            bean.setItemPrice(rs.getInt("item_price"));            
            bean.setPurchaserCount(rs.getInt("purchaser_count"));
            bean.setItemEndTime(rs.getInt("item_endtime"));
            SIlist.addElement(bean);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         pool.freeConnection(con, pstmt, rs);
      }
      return SIlist;
   }
   
   //댓글저장
   public void insertComment(String sellerId,String logId, Integer itemNum,String comment){
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      String sql = null;
	      try {
	         con = pool.getConnection();
	         sql = "insert into comment (seller_id , purchaser_id,comment_itemnum ,"
	         		+ "comment_content,comment_time) "
	         		+ "values(?, ?, ?, ?, now())";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, sellerId);
	         pstmt.setString(2, logId);
	         pstmt.setInt(3, itemNum);
	         pstmt.setString(4, comment);
	         pstmt.executeUpdate();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         pool.freeConnection(con, pstmt);
	      }
	   }
 //경매참여가격저장
   public void insertAuction(Integer itemNum, Integer itemPrice, String purchaserName){
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      String sql = null;
	      try {
	         con = pool.getConnection();
	         sql = "insert into auction (auction_itemnum, auction_time, auction_price, auction_purchaser) "
	         		+ "values(? ,now(), ?, ?)";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, itemNum);
	         pstmt.setInt(2, itemPrice);
	         pstmt.setString(3, purchaserName);
	         pstmt.executeUpdate();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         pool.freeConnection(con, pstmt);
	      }
	   }
   //comment가져오기(댓글 - 판매상품별)
   //TODO
   public Vector<CommentBean> getCommentList(Integer commentItemNum){
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      Vector<CommentBean> clist = new Vector<CommentBean>();
      try {
         con = pool.getConnection();
         sql = "select purchaser_id, comment_content , comment_time "
         		+ "from comment "
         		+ "where comment_itemnum = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, commentItemNum);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            CommentBean bean = new CommentBean();
            bean.setPurchaserId(rs.getString("purchaser_id"));
            bean.setCommentContent(rs.getString("comment_content"));
            bean.setCommentTime(rs.getDate("comment_time"));
            clist.addElement(bean);
         }         
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         pool.freeConnection(con, pstmt, rs);
      }
      return clist;
   }
 //iddupcheck
	public boolean IdDupChk(String memberId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = true;
		
		try {
			con = pool.getConnection();
			sql = "select count(member_id) from member "
					+"where member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if(rs.next()&&rs.getInt(1)==1)
				flag = false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	//카테고리리스트 가져오기
	public Vector<CategoryBean> getCategory(){
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = null;
	      Vector<CategoryBean> clist = new Vector<CategoryBean>();
	      try {
	         con = pool.getConnection();
	         sql = "select category_name "
	               + "from category";
	         pstmt = con.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
	            CategoryBean cbean = new CategoryBean();
	            cbean.setCategoryName(rs.getString("category_name"));
	            clist.addElement(cbean);       
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         pool.freeConnection(con, pstmt, rs);
	      }
	      return clist;
	   }
	//카테고리리스트에서 아이템 가져오기
	//TODO
		/*public Vector<ItemBean> getOneItem(){
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      String sql = null;
		      Vector<ItemBean> clist = new Vector<ItemBean>();
		      try {
		         con = pool.getConnection();
		         sql = "select  "
		               + "from item";
		         pstmt = con.prepareStatement(sql);
		         rs = pstmt.executeQuery();
		         while(rs.next()) {
		            cbean.setCategoryName(rs.getString("category_name"));
		         }
		      } catch (Exception e) {
		         e.printStackTrace();
		      }finally {
		         pool.freeConnection(con, pstmt, rs);
		      }
		      return clist;
		   }*/

}