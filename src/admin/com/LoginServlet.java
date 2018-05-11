package admin.com;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected String getAuthToken(String username,String password) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update((username+password).getBytes());
	    byte[] digest = md.digest();
	    String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
	    return myHash;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		String authtoken="authToken";
		try {
			authtoken = getAuthToken(email, password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String token=generatecsrfToken();
		HttpSession session=request.getSession();
		if(email.equals("max") && password.equals("123123123")) {
			session.setAttribute("csrfToken", token);
			response.addCookie(new Cookie("authToken",authtoken));
			session.setAttribute("authToken", authtoken);
			System.out.printf("LoginServlet:AuthToken:%s,CSRFToken:%s\n",authtoken,token);
			//RequestDispatcher dispatcher=request.getRequestDispatcher("/dashboardServlet");
			//dispatcher.forward(request, response);
			response.sendRedirect("dashboardServlet");
		}else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}

	private String generatecsrfToken() {
		return UUID.randomUUID().toString();
	}

}