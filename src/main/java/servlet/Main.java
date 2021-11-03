package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Integer count;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// 訪問回数を表すIntegerインスタンスを新規作成し、アプリケーションスコープに保存
		//Integer
		count = 0;
		ServletContext application = config.getServletContext();
		application.setAttribute("count", count);
		
		System.out.println("init()が実行されました");
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// つぶやきリストをアプリケーションスコープから取得
		ServletContext application = this.getServletContext();
		List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
		// 取得できなかった場合は、つぶやきリストを新規作成してアプリケーションスコープに保存
		if (mutterList == null) {
			mutterList = new ArrayList<>();
			application.setAttribute("mutterList", mutterList);
		}
		
		// ログインしているか確認するためセッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			// リダイレクト
			response.sendRedirect("/example/");
		} else { // ログイン済みの場合
			
			// アプリケーションスコープに保存された訪問回数を増加
//			ServletContext application = this.getServletContext();
			Integer count = (Integer) application.getAttribute("count");
			count++;
			application.setAttribute("count", count);
			// フォワード
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// リクエストパラメータの取得
//		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		
		// 入力値チェック
		if (text != null && text.length() != 0) {
			// アプリケションスコープに保存されたつぶやきリストを取得
			ServletContext application = this.getServletContext();
			List<Mutter> mutterList = 
					(List<Mutter>) application.getAttribute("mutterList");
			// セッションスコープに保存されたユーザ情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			
			// つぶやきを呟きリストに追加
			Mutter mutter = new Mutter(loginUser.getName(), text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter,  mutterList);
			
			// アプリケーションスコープにつぶやきリストを保存
			application.setAttribute("mutterList", mutterList);
		} else {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
		}
		
		// メイン画面にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

	public void destroy() {
		System.out.println("destroy()が実行されました");
	}
}
