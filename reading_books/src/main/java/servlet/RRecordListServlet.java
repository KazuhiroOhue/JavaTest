package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RRecordListServlet
 */
@WebServlet("/RRecordList")
public class RRecordListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RRecordListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 読書記録一覧の表示画面に移動します
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//ユーザIDを取得
		int id = Integer.parseInt(request.getParameter("userId"));
		
//		System.out.println(id);
				
		// まだこれから！！！
//		// セッションスコープ呼び出し
//		HttpSession session = request.getSession();
//		session = request.getSession();
		
		// フォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/jsp/r-record-list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
