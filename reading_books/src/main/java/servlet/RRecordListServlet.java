package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.ReadingRecordsDAO;
import model.entity.ReadBooksBean;

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
		// セッションスコープからユーザーIDを取得
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		System.out.println("セッションスコープから："+userId);
		// 読書記録リストを取得し、セッションスコープに保存
		ReadingRecordsDAO rrd = new ReadingRecordsDAO();
		List<ReadBooksBean> rRecordList = rrd.selectAllRecords(userId);
		session.setAttribute("rRecordList", rRecordList);
		// フォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/jsp/r-record-list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// フォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/jsp/main-menu.jsp");
		dispatcher.forward(request, response);
	}

}
