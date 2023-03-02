package project1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.IndexColorModel;
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

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

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
   public CategoryBean getCategoryName(int categoryNum){
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
            sql = "select i1.item_seller, i1.item_status , i1.item_num, i1.item_name, i1.item_photo, i1.item_memo, i1.item_price, i1.purchaser_count, timestampdiff(second, now(), i1.item_endtime) "
            		+ "from item i1 "
            		+ "where purchaser_count = (select max(purchaser_count) from item i where timestampdiff(second, now(), i.item_endtime) > 0 ) "
            		+ "order by item_price desc limit 1;";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	ibean.setItemSeller(rs.getString("i1.item_seller"));
            	ibean.setItemNum(rs.getInt("i1.item_num"));
            	ibean.setItemName(rs.getString("i1.item_name"));
            	ibean.setItemMemo(rs.getString("i1.item_memo"));
            	ibean.setItemPrice(rs.getInt("i1.item_price"));
            	ibean.setPurchaserCount(rs.getInt("i1.purchaser_count"));
            	ibean.setItemEndTime(rs.getInt("timestampdiff(second, now(), i1.item_endtime)"));
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
            sql = "select item_seller, item_num, item_name, item_photo, item_memo, item_price, purchaser_count, timestampdiff(second, now(), item_endtime) "
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
            	ibean.setItemEndTime(rs.getInt("timestampdiff(second, now(), item_endtime)"));
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
            sql = "select item_num, item_name, item_price, purchaser_count, timestampdiff(second, now(), item_endtime)  "
            		+ "from item i , category c "
            		+ "where i.item_categorynum = c.category_num "
            		+ "and c.category_name  = ? "
            		+ "and timestampdiff(second, now(), item_endtime) > 0";
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
      //seller체크
      public boolean sellerChk(String memberId, String memberPwd) {
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
   //경매참여 상품(마이페이지 판매한 상품 - member_id = item_seller)
   public Vector<ItemBean> getIngItemList(String itemSeller){
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      Vector<ItemBean> ilist = new Vector<ItemBean>();
      try {
         con = pool.getConnection();
         sql = "select distinct i.item_num ,i.item_seller, i.item_name ,i.item_price, timestampdiff(second, now(), i.item_endtime), i.purchaser_count "
         		+ "from item i , auction a "
         		+ "where i.item_num = a.auction_itemnum "
         		+ "	and a.auction_purchaser = ? "
         		+ "	and timestampdiff(second, now(), item_endtime) > 0";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, itemSeller);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            ItemBean bean = new ItemBean();    
            bean.setItemNum(rs.getInt("item_num"));
            bean.setItemName(rs.getString("item_seller"));
            bean.setItemName(rs.getString("item_name"));
            bean.setItemPrice(rs.getInt("item_price"));            
            bean.setPurchaserCount(rs.getInt("purchaser_count"));
            bean.setItemEndTime(rs.getInt("timestampdiff(second, now(), i.item_endtime)"));
            ilist.addElement(bean);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         pool.freeConnection(con, pstmt, rs);
      }
      return ilist;
   }
   
   //판매한상품(마이페이지 구매한 상품 - join 필요할 것 같음)
   public Vector<ItemBean> getSellItemList(String item_seller){
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      Vector<ItemBean> SIlist = new Vector<ItemBean>();
      try {
         con = pool.getConnection();
         sql = "select i.item_num ,i.item_seller, i.item_name ,i.item_price, "
         		+ "timestampdiff(second, now(), i.item_endtime), i.purchaser_count " 
        		 + "from item i "
        		 + "where item_seller = ? and item_status = 1;";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, item_seller);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            ItemBean bean = new ItemBean();
            bean.setItemNum(rs.getInt("item_num"));
            bean.setItemSeller(rs.getString("item_seller"));
            bean.setItemName(rs.getString("item_name"));
            bean.setItemPrice(rs.getInt("item_price"));
            bean.setItemEndTime(rs.getInt("timestampdiff(second, now(), i.item_endtime)"));
            bean.setPurchaserCount(rs.getInt("purchaser_count"));
            SIlist.addElement(bean);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         pool.freeConnection(con, pstmt, rs);
      }
      return SIlist;
   }
   
   //구매한상품(마이페이지 )
   public Vector<ItemBean> getBuyItemList(String itemSeller){
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      Vector<ItemBean> SIlist = new Vector<ItemBean>();
      try {
         con = pool.getConnection();
         sql = "select i.item_num ,i.item_seller, i.item_name ,i.item_price, "
         		+ "timestampdiff(second, now(), i.item_endtime), i.purchaser_count "
         		+ "from item i , auction a "
         		+ "where i.item_num = a.auction_itemnum "
         		+ "	and a.auction_purchaser = ? "
         		+ "	and i.item_status = 1";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, itemSeller);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            ItemBean bean = new ItemBean();
            bean.setItemNum(rs.getInt("item_num"));
            bean.setItemName(rs.getString("item_seller"));
            bean.setItemName(rs.getString("item_name"));
            bean.setItemPrice(rs.getInt("item_price"));            
            bean.setPurchaserCount(rs.getInt("purchaser_count"));
            bean.setItemEndTime(rs.getInt("timestampdiff(second, now(), i.item_endtime)"));
            SIlist.addElement(bean);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         pool.freeConnection(con, pstmt, rs);
      }
      return SIlist;
   }
   
   //옥션 - 댓글저장
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
   //옥션 - comment가져오기(댓글 - 판매상품별)
   public Vector<CommentBean> getCommentList(Integer commentItemNum){
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      Vector<CommentBean> clist = new Vector<CommentBean>();
      try {
         con = pool.getConnection();
         sql = "select seller_id, purchaser_id, comment_content , comment_time "
         		+ "from comment "
         		+ "where comment_itemnum = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, commentItemNum);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            CommentBean bean = new CommentBean();
            bean.setSellerId(rs.getString("seller_id"));
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
	//관리자 - 카테고리별 판매완료상품
	 public Vector<ItemBean> getAdminCategory(){
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = null;
	      Vector<ItemBean> clist = new Vector<ItemBean>();
	      try {
	         con = pool.getConnection();
	         sql = "select item_categorynum, count(*) "
	         		+ "from item "
	         		+ "where item_status = 1 "
	         		+ "group by item_categorynum";
	         pstmt = con.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
	            ItemBean ibean = new ItemBean();
	            ibean.setCategoryNum(rs.getInt("item_categorynum"));
	            ibean.setAllCount(rs.getInt("count(*)"));
	            clist.addElement(ibean);
	         }         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         pool.freeConnection(con, pstmt, rs);
	      }
	      return clist;
	   }
	   //관리자 - 경매 중인 상품
	    public void getAuctionIng(DefaultTableModel ingModel){
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         String sql = null;
	         try {
	            con = pool.getConnection();
	            sql = "select distinct item_num, item_name , item_seller , purchaser_count , item_price "
	            		+ "from item  "
	            		+ " where item_status = 2 ";
	            pstmt = con.prepareStatement(sql);
	            rs = pstmt.executeQuery();
	            while(rs.next()) {
	            	Vector<Object> data = new Vector<>();
	               data.add(rs.getString("item_name"));
	               data.add(rs.getString("item_seller"));
	               data.add(rs.getString("purchaser_count"));
	               data.add(rs.getString("item_price"));
	               ingModel.addRow(data);
	            }         
	         } catch (Exception e) {
	            e.printStackTrace();
	         }finally {
	            pool.freeConnection(con, pstmt, rs);
	         }
	      }
	    
	   //관리자 - 경매 종료 상품
	    public void getAuctionEnd(DefaultTableModel endModel){
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         String sql = null;
	         try {
	            con = pool.getConnection();
	            sql = "select a.auction_purchaser ,i.item_num, i.item_name , i.item_seller , i.purchaser_count , i.item_price "
	            		+ "from item i, auction a "
	            		+ "where i.item_num = a.auction_itemnum "
	            		+ "	and i.item_status = 1";
	            pstmt = con.prepareStatement(sql);
	            rs = pstmt.executeQuery();
	            while(rs.next()) {
		            Vector<Object> data = new Vector<>();
	            	data.add(rs.getString("item_name"));
	            	data.add(rs.getString("item_seller"));
	            	data.add(rs.getString("purchaser_count"));
	            	data.add(rs.getString("item_price"));
	            	endModel.addRow(data);
	            }         
	         } catch (Exception e) {
	            e.printStackTrace();
	         }finally {
	            pool.freeConnection(con, pstmt, rs);
	         }
	      }
	//비밀번호 변경- 마이페이지
	   public void pwChange(String memberPwd, String memberId) {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      String sql = null;
	      try {
	         con = pool.getConnection();
	         sql = "update `member` set member_pw = ? where member_name = ?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, memberPwd);
	         pstmt.setString(2, memberId);
	         pstmt.executeUpdate();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         pool.freeConnection(con, pstmt);
	      }
	   }
	 //현재비밀번호 확인 -마이페이지
	   public boolean pwChk(String memberPwd, String memberId) {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = null;
	      boolean flag = false;
	      try {
	         con = pool.getConnection();
	         sql = "select count(member_pw) "
	         		+ "from member "
	         		+ "where member_pw = ? and member_id = ?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, memberPwd);
	         pstmt.setString(2, memberId);
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

}