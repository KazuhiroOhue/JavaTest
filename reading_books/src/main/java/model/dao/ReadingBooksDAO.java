package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entity.ReadBooksBean;
import model.logic.Calculation;
import model.logic.CategoryImage;

public class ReadingBooksDAO {
	
	/**
	 * ユーザが所持しているすべての本の情報をリストにして返します
	 * @param useId
	 * @return List
	 */
	public List<ReadBooksBean> selectAllBooks(int useId){
	
	List<ReadBooksBean> bookList = new ArrayList<ReadBooksBean>();
	
	// データベースへ接続
	try(Connection conn =ConnectionManager.getConnection()){
		// SELECT文を準備
		String sql = 
				"SELECT "
					+"t1.image_path,"
					+"t1.book_id,"
					+"t1.book_name,"
					+"t1.book_category,"
					+"t1.total_pages,"
					+"t2.read_pages_sum "
				+"FROM reading_books.m_book as t1 "
				+"INNER JOIN "
					+"(SELECT "
						+"user_id,book_id,"
						+"SUM(read_pages) as read_pages_sum "
					+"FROM reading_books.m_read_pages "
					+"GROUP BY user_id, book_id) as t2 "
				+"ON t1.book_id = t2.book_id "
				+"WHERE  t1.user_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, useId);
		
		// SELECTを実行、結果ををリストに格納
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int bookId = rs.getInt("book_id");
			String bookName = rs.getString("book_name");
			String bookCategory = rs.getString("book_category");
			int totalPages = rs.getInt("total_pages");
			int readPages = rs.getInt("read_pages_sum");
			
			// 読んだページ数のパーセンテージを取得
			Calculation calc = new Calculation();
			double percentage = calc.calculation(totalPages, readPages);
			
			// カテゴリ別の画像パスを取得
			CategoryImage img = new CategoryImage();
			String path = img.CategoryImage(bookCategory);
			
			ReadBooksBean rbb = new ReadBooksBean();
			rbb.setImagePath(path);
			rbb.setBookId(bookId);
			rbb.setBookCategory(bookCategory);
			rbb.setBookName(bookName);
			rbb.setTotalPages(totalPages);
			rbb.setReadPages(readPages);
			rbb.setPercentage(percentage);
			
			bookList.add(rbb);
		}
		
	} catch (ClassNotFoundException | SQLException e) {
		
		e.printStackTrace();
		System.out.println("エラーが発生しました");
		return null;
	}
	
	return bookList; 
	}
	
	/**
	 * 本の詳細情報を返します
	 * @param bookId
	 * @return ReadBooksBean
	 */
	public ReadBooksBean selectBook(int bookId) {
	ReadBooksBean rbb = new ReadBooksBean();
	
	// データベースへ接続
	try(Connection conn = ConnectionManager.getConnection()){
		// SELECT文を準備
		String sql = "SELECT * FROM reading_books.m_book WHERE book_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bookId);
		// SELECTを実行、結果ををリストに格納
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			String bookName = rs.getString("book_name");
			String bookCategory = rs.getString("book_category");
			Date purchaseDate = rs.getDate("purchase_date");
			int totalpages = rs.getInt("total_pages");
			String bookMemos = rs.getString("book_memos");
			
			// カテゴリ別の画像パスを取得
			CategoryImage img = new CategoryImage();
			String path = img.CategoryImage(bookCategory);
			
			rbb.setBookId(bookId);
			rbb.setImagePath(path);
			rbb.setBookName(bookName);
			rbb.setPurchaseDate(purchaseDate);
			rbb.setTotalPages(totalpages);
			rbb.setBookmemos(bookMemos);
		}
			
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
		
	return rbb;
		
	}
	
	public void addBook(ReadBooksBean rbb) {
		// データベースへ接続
		try(Connection conn =ConnectionManager.getConnection()){
			// SELECT文を準備
			String sql = "INSERT INTO reading_books.m_book "
					+"(book_name, purchase_date, user_id, book_category, total_pages, book_memos) "
					+"VALUES(?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rbb.getBookName());
			pstmt.setDate(2, (java.sql.Date) rbb.getPurchaseDate());
			pstmt.setInt(3, rbb.getUserId());
			pstmt.setString(4, rbb.getBookCategory());
			pstmt.setInt(5, rbb.getTotalPages());
			pstmt.setString(6, rbb.getBookmemos());
//			System.out.println("DAO内のユーザID："+ rbb.getUserId());
//			System.out.println(rbb.getBookName());
//			System.out.println(rbb.getBookCategory());
			// SELECTを実行
			int ud = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("エラーが発生しました");
			e.printStackTrace();
		}
	}
	
	/**
	 * 本の情報を更新します
	 * @param rbb
	 */
	public void updateBook(ReadBooksBean rbb) {
		// データベースへ接続
		try(Connection conn =ConnectionManager.getConnection()){
			// SELECT文を準備
			String sql = "UPDATE reading_books.m_book "
			+"SET book_name = ?, "
					+"purchase_date = ?, "
					+"book_category = ?, "
					+"total_pages = ?, "
					+"book_memos = ? "
			+"WHERE book_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rbb.getBookName());
			pstmt.setDate(2, (java.sql.Date) rbb.getPurchaseDate());
			pstmt.setString(3, rbb.getBookCategory());
			pstmt.setInt(4, rbb.getTotalPages());
			pstmt.setString(5, rbb.getBookmemos());
			pstmt.setInt(6, rbb.getBookId());
			
			
			System.out.println("DAO内のユーザID："+ rbb.getUserId());
			System.out.println(rbb.getBookName());
			System.out.println(rbb.getBookCategory());
			System.out.println(rbb.getTotalPages());
			System.out.println(rbb.getBookmemos());
			System.out.println(rbb.getBookId());
			// SELECTを実行
			int ud = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("エラーが発生しました");
			e.printStackTrace();
		}
	}
}
