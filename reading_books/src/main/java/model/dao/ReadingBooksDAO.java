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
			
			ReadBooksBean readBooks = new ReadBooksBean();
			readBooks.setImagePath(path);
			readBooks.setBookId(bookId);
			readBooks.setBookCategory(bookCategory);
			readBooks.setBookName(bookName);
			readBooks.setTotalPages(totalPages);
			readBooks.setReadPages(readPages);
			readBooks.setPercentage(percentage);
			
			bookList.add(readBooks);
		}
		
	} catch (ClassNotFoundException | SQLException e) {
		
		e.printStackTrace();
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
	
	/**
	 * ユーザのすべての読書記録をリストにして返します
	 * @param useId
	 * @return List
	 */
//	public List<ReadBooksBean> selectAllRecords(int useId){
//		
//		List<ReadBooksBean> List = new ArrayList<ReadBooksBean>();
//		// データベースへ接続
//		try(Connection conn =ConnectionManager.getConnection()){
//			
//			// SELECT文を準備
//			String sql = "SELECT * FROM reading_books.m_read_pages WHERE user_id = ?";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, useId);
			
//			// SELECTを実行、結果ををリストに格納
//			ここから下は未完成！！！！！！
//			ResultSet rs = pstmt.executeQuery();
//			while(rs.next()) {
//				String bookName = rs.getString("book_name");
//				Date purchaseDate = rs.getDate("purchase_date");
//				int totalPages = rs.getInt("total_pages");
//				int readPages = rs.getInt("read_pages_sum");
//			}
			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
//		return null;
//		
//	}
}
