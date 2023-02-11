package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.ReadBooksBean;

/**
 * Servlet implementation class EditBookInfoServlet
 */
@WebServlet("/EditBookInfo")
public class EditBookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBookInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = null;
		//actionによって処理を分岐します
		request.setCharacterEncoding("UTF-8"); // 文字化け防止
		action = request.getParameter("action");
		// セッションスコープ呼び出し
		HttpSession session = request.getSession();
		session = request.getSession();
		
		/**
		 * 編集画面に移動します
		 */
		if (action.equals("編集する")) {
			/**
			 * 以下のように変更！！
			 * ①ここでは、セッションスコープのreadBookから要素を取り出し、
			 * それぞれのeXXXXに代入
			 * ②それらを新たにセッションスコープに設定する
			 * ③編集画面の${readBook.XXXX}を、${eXXXX}に変更
			 */
			// セッションスコープから変更したい内容を取得
			ReadBooksBean ebook = (ReadBooksBean) session.getAttribute("readBook");
			String eBookName = ebook.getBookName();
			Date ePurchaseDate = ebook.getPurchaseDate();
			String eBookCategory = ebook.getBookCategory();
			int eTotalPages = ebook.getTotalPages();
			String eBookMemos = ebook.getBookmemos();
			//セッションススコープに設定
			session.setAttribute("eBookName", eBookName);
			session.setAttribute("ePurchaseDate", ePurchaseDate);
			session.setAttribute("eBookCategory", eBookCategory);
			session.setAttribute("eTotalPages", eTotalPages);
			session.setAttribute("eBookMemos", eBookMemos);
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit-book-info.jsp");
			dispatcher.forward(request, response);
		
			/**
			 * 編集確認画面に移動します
			 */
		} else if (action.equals("変更する")) {
			// リクエストパラメータの取得
			String eBookName = request.getParameter("eBookName");
			String ePurchaseDate = request.getParameter("ePurchaseDate");
			String eBookCategory =request.getParameter("eBookCategory");
			int eTotalPages = Integer.parseInt(request.getParameter("eTotalPages"));
			String eBookMemos = request.getParameter("eBookMemos");
			// セッションスコープに設定
			session.setAttribute("eBookName", eBookMemos);
			session.setAttribute("ePurchaseDate", ePurchaseDate);
			session.setAttribute("eBookCategory", eBookCategory);
			session.setAttribute("eTotalPages", eTotalPages);
			session.setAttribute("eBookMemos", eBookMemos);
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit-book-info-confirm.jsp");
			dispatcher.forward(request, response);
		} else {
			System.out.println("エラーです！！");
		}
//			
//		} else if(action.equals("この内容で確定")) {
//			//idを取得
//			Memos memosId = (Memos) session.getAttribute("memos");
//			int id = memosId.getId();
//			
//			//変更内容を取得
//			editTitle = (String) session.getAttribute("editTitle");
//			editText = (String) session.getAttribute("editText");
//			
//			Memos memos = new Memos(id, editTitle, editText);
//			
//			// DAOを呼び出し、UPDATEを実行
//			AccountDAO dao = new AccountDAO();
//			dao.update(memos);
//			
//			// フォワード
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/contents-edit-complation.jsp");
//			dispatcher.forward(request, response);
			
	}

	/**
	 * 編集画面に戻ります
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = null;
		//actionによって処理を分岐します
		request.setCharacterEncoding("UTF-8"); // 文字化け防止
		action = request.getParameter("action");
		// セッションスコープ呼び出し
		HttpSession session = request.getSession();
		session = request.getSession();
		
		/**
		 * 詳細表示画面に戻ります
		 */
		if(action.equals("やめる")) {
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/book-info.jsp");
			dispatcher.forward(request, response);
		
		/**
		 * 編集画面に戻ります
		 */
		} else if (action.equals("編集画面に戻る")) {
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit-book-info.jsp");
			dispatcher.forward(request, response);
			
		} else {
			System.out.println("エラーです！！");
		}
	}

}
