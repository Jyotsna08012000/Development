package Controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CustomerDao;
import Dao.SellerDao;
import Model.Customer;
import Model.Seller;
import services.Services;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("register")) {
			
			String email = request.getParameter("email");
			System.out.println(email);
		    Customer c = CustomerDao.checkEmailduringRegister(email);
			
		if ( c!=null) {
				request.setAttribute("msg", "Account registered already");
				request.getRequestDispatcher("Customer-Register.jsp").forward(request, response);
				
	 } else {
			Customer c1 = new Customer();
			c1.setName(request.getParameter("name"));
			c1.setContact(Long.parseLong(request.getParameter("contact")));
			c1.setEmail(request.getParameter("email"));
			c1.setPassword(request.getParameter("password"));
			c1.setAddress(request.getParameter("address"));
			c1.setCity(request.getParameter("city"));
			c1.setState(request.getParameter("state"));
			CustomerDao.insertCustomer(c1);
			request.setAttribute("msg", "Account register sucessfully");
			request.getRequestDispatcher("Customer-Login.jsp").forward(request, response);
	 }

		} else if (action.equalsIgnoreCase("login")) {
			Customer c = new Customer();
			c.setEmail(request.getParameter("email"));
			c.setPassword(request.getParameter("password"));
			Customer c1 = CustomerDao.loginCustomer(c);
			if (c1 == null) {
				request.setAttribute("msg", "password is incorrect");
				request.getRequestDispatcher("Customer-Login.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("data", c1);
				request.getRequestDispatcher("Customer-home.jsp").forward(request, response);

			}
		} else if (action.equalsIgnoreCase("update")) {
			Customer c = new Customer();
			c.setName(request.getParameter("name"));
			c.setContact(Long.parseLong(request.getParameter("contact")));
			c.setEmail(request.getParameter("email"));
			c.setAddress(request.getParameter("address"));
			c.setCity(request.getParameter("city"));
			c.setState(request.getParameter("state"));
			c.setId(Integer.parseInt(request.getParameter("id")));
			CustomerDao.updateProfile(c);
			HttpSession session = request.getSession();
			session.setAttribute("data", c);
			request.getRequestDispatcher("Customer-home.jsp").forward(request, response);

		} else if (action.equalsIgnoreCase("ChangePassword")) {
			Customer c = new Customer();
			int id = Integer.parseInt(request.getParameter("id"));
			String op = request.getParameter("op");
			String np = request.getParameter("np");
			String cnp = request.getParameter("cnp");
			boolean flag = CustomerDao.checkOldPassword(id, op);
			if (flag == true) {
				if (np.equals(cnp)) {
					CustomerDao.changePasswrod(id, np);
					response.sendRedirect("Customer-home.jsp");
				} else {
					request.setAttribute("msg1", "old password and new password not matched");
					request.getRequestDispatcher("Customer-ChangePassword.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "Old password incorrect");
				request.getRequestDispatcher("Customer-ChangePassword.jsp").forward(request, response);
			}

		} else if (action.equalsIgnoreCase("GET OTP")) {
			String email = request.getParameter("email");
			boolean flag = CustomerDao.checkEmail(email);
			System.out.println(flag);
			if (flag == true) {
				Services s = new Services();
				Random r = new Random();
				int num = r.nextInt(999999);
				System.out.println(num);
				s.sendMail(email, num);
				request.setAttribute("email", email);
				request.setAttribute("otp", num);
				request.getRequestDispatcher("customer-verify-otp.jsp").forward(request, response);

			}
		} else if (action.equalsIgnoreCase("verify")) {
			String email = request.getParameter("email");
			int otp1 = Integer.parseInt(request.getParameter("otp1"));
			int otp2 = Integer.parseInt(request.getParameter("otp2"));
			if (otp1 == otp2) {
				request.setAttribute("email", email);
				request.getRequestDispatcher("customer-new-password.jsp").forward(request, response);
			}

			else {
				request.setAttribute("email", email);
				request.setAttribute("otp", otp1);
				request.setAttribute("msg", "otp not matched");
				request.getRequestDispatcher("customer-verify-otp.jsp").forward(request, response);
			}

		} else if (action.equalsIgnoreCase("Change Password")) {
			String email = request.getParameter("email");
			String np = request.getParameter("np");
			String cnp = request.getParameter("cnp");
			System.out.println("confirm new password"+cnp);
			System.out.println("new password"+np);
			System.out.println("email"+email);
			if (np.equals(cnp)) {
				CustomerDao.changeNewPassword(email, np);
				response.sendRedirect("Customer-Login.jsp");

			} else {
				request.setAttribute("msg", "np and cnp not matched");
			}
		}
		else if (action.equalsIgnoreCase("updatebyadmin")) {
			Customer c = new Customer();
			c.setName(request.getParameter("name"));
			c.setContact(Long.parseLong(request.getParameter("contact")));
			c.setEmail(request.getParameter("email"));
			c.setAddress(request.getParameter("address"));
			c.setCity(request.getParameter("city"));
			c.setState(request.getParameter("state"));
			c.setId(Integer.parseInt(request.getParameter("id")));
			CustomerDao.updateProfile(c);
			HttpSession session = request.getSession();
			session.setAttribute("data", c);
			request.getRequestDispatcher("admin-customer.jsp").forward(request, response);
}
	}
	}


