package admin.com;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dashboardServlet")
public class dashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public dashboardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookiesarray=request.getCookies();
		System.out.println(cookiesarray[0]);
		List<Cookie> cookies = Arrays.asList(cookiesarray);

		System.out.printf("dashBoardServlet:authToken:%s",request.getSession().getAttribute("authToken"));
		Cookie cookie = null;
		if (request.getCookies().length > 0) {
			for (Cookie cooke : cookies) {
				System.out.printf("dashBoardServlet: All cookies received:%s,%s\n",cooke.getName(),cooke.getValue());
			}
			cookie = cookies.stream().filter(cook -> cook.getName().equals("authToken"))
					.collect(Collectors.toList()).get(0);
		}
		if (cookie != null && cookie.getValue().equals(request.getSession().getAttribute("authToken"))) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost: called for dashBoard");
		if (request.getParameter("url") != null && request.getParameter("url").equals("transaction")) {
			HttpSession session = request.getSession();
			String token = (String) session.getAttribute("csrfToken");
			String requestToken = (String) request.getParameter("csrfToken");
			System.out.println(token + "=" + requestToken);
			if (token != null && token.equals(requestToken)) {
				response.getWriter().println("Money transferred successfully!");
			} else {
				response.getWriter()
						.println("Are you kidding me! CSRF to a webapplication is a cyber criminal offense");
			}
		} else {
			doGet(request, response);
		}
	}

}
