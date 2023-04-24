package Controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.SellerDao;

import Model.Seller;

import services.Services;

/**
 * Servlet implementation class SellerController
 */
@WebServlet("/SellerController")
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("register")) {
			
		
			String email = request.getParameter("email");
			System.out.println(email);
		    Seller  s = SellerDao.checkEmailduringRegister(email);
			
		if ( s!=null) {
				request.setAttribute("msg", "Account registered already");
				request.getRequestDispatcher("Seller-Register.jsp").forward(request, response);
				
	 } else {
			Seller s1 = new Seller();
			s1.setName(request.getParameter("name"));
			s1.setContact(Long.parseLong(request.getParameter("contact")));
			s1.setEmail(request.getParameter("email"));
			s1.setPassword(request.getParameter("password"));
			s1.setAddress(request.getParameter("address"));
			s1.setCity(request.getParameter("city"));
			s1.setState(request.getParameter("state"));
			SellerDao.insertSeller(s1);
			request.setAttribute("msg", "Account register sucessfully");
			request.getRequestDispatcher("Seller-Login.jsp").forward(request, response);
	 }

		} else if (action.equalsIgnoreCase("login")) {
			Seller s = new Seller();
			s.setEmail(request.getParameter("email"));
			s.setPassword(request.getParameter("password"));
			Seller s1 = SellerDao.loginSeller(s);
			if (s1 == null) {
				request.setAttribute("msg", "password is incorrect");
				request.getRequestDispatcher("Seller-Login.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("data", s1);
				request.getRequestDispatcher("Seller-home.jsp").forward(request, response);

			}
		} else if (action.equalsIgnoreCase("update")) {
			Seller s = new Seller();
			s.setName(request.getParameter("name"));
			s.setContact(Long.parseLong(request.getParameter("contact")));
			s.setEmail(request.getParameter("email"));
			s.setAddress(request.getParameter("address"));
			s.setCity(request.getParameter("city"));
			s.setState(request.getParameter("state"));
			s.setId(Integer.parseInt(request.getParameter("id")));
			SellerDao.updateProfile(s);
			HttpSession session = request.getSession();
			session.setAttribute("data", s);
			request.getRequestDispatcher("Seller-home.jsp").forward(request, response);

		} else if (action.equalsIgnoreCase("ChangePassword")) {
			Seller s = new Seller();
			int id = Integer.parseInt(request.getParameter("id"));
			String op = request.getParameter("op");
			String np = request.getParameter("np");
			String cnp = request.getParameter("cnp");
			boolean flag = SellerDao.checkOldPassword(id, op);
			if (flag == true) {
				if (np.equals(cnp)) {
					SellerDao.changePasswrod(id, np);
					response.sendRedirect("Seller-home.jsp");
				} else {
					request.setAttribute("msg1", "old password and new password not matched");
					request.getRequestDispatcher("Seller-ChangePassword.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "Old password incorrect");
				request.getRequestDispatcher("Seller-ChangePassword.jsp").forward(request, response);
			}

		} else if (action.equalsIgnoreCase("GET OTP")) {
			String email = request.getParameter("email");
			boolean flag = SellerDao.checkEmail(email);
			System.out.println(flag);
			if (flag == true) {
				Services s = new Services();
				Random r = new Random();
				int num = r.nextInt(999999);
				System.out.println(num);
				s.sendMail(email, num);
				request.setAttribute("email", email);
				request.setAttribute("otp", num);
				request.getRequestDispatcher("seller-verify-otp.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "incorrect email id incorrect");
					request.getRequestDispatcher("Seller-ForgotPassword.jsp").forward(request, response);
				}
		} else if (action.equalsIgnoreCase("verify")) {
			String email = request.getParameter("email");
			int otp1 = Integer.parseInt(request.getParameter("otp1"));
			int otp2 = Integer.parseInt(request.getParameter("otp2"));
			if (otp1 == otp2) {
				request.setAttribute("email", email);
				request.getRequestDispatcher("seller-new-password.jsp").forward(request, response);
			}

			else {
				request.setAttribute("email", email);
				request.setAttribute("otp", otp1);
				request.setAttribute("msg", "otp not matched");
				request.getRequestDispatcher("seller-verify-otp.jsp").forward(request, response);
			}

		} else if (action.equalsIgnoreCase("Change Password")) {
			String email = request.getParameter("email");
			String np = request.getParameter("np");
			String cnp = request.getParameter("cnp");
			System.out.println("confirm new password"+cnp);
			System.out.println("new password"+np);
			System.out.println("email"+email);
			if (np.equals(cnp)) {
				SellerDao.changeNewPassword(email, np);
				response.sendRedirect("Seller-Login.jsp");

			} else {
				request.setAttribute("msg", "np and cnp not matched");
			}
		}
		 else if (action.equalsIgnoreCase("updatebyadmin")) {
				Seller s = new Seller();
				s.setName(request.getParameter("name"));
				s.setContact(Long.parseLong(request.getParameter("contact")));
				s.setEmail(request.getParameter("email"));
				s.setAddress(request.getParameter("address"));
				s.setCity(request.getParameter("city"));
				s.setState(request.getParameter("state"));
				s.setId(Integer.parseInt(request.getParameter("id")));
				SellerDao.updateProfile(s);
				HttpSession session = request.getSession();
				session.setAttribute("data", s);
				request.getRequestDispatcher("admin-sellerlist.jsp").forward(request, response);
	}
}
}
