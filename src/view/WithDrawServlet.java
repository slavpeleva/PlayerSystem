package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.Queries;
import model.ValidationsUsernames;


/**
 * Servlet implementation class WithDrawServlet
 */
@WebServlet("/WithDrawServlet")
public class WithDrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithDrawServlet() {
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
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String balance = request.getParameter("balance");
		try {
			if (balance.matches("[0-9]+")) {
				double bal = Double.valueOf(balance);
				if (ValidationsUsernames.validBalance(request.getParameter("temp"), bal)) {
					Queries.withdraw(request.getParameter("temp"), bal);
					JSONObject result = new JSONObject().put("status", "ok");
					out.println("<p>"+result.toString()+"</p>");
					out.println("<form method=\"POST\" action=\"PlayerServlet\" >");					
					out.println("<input type=\"hidden\" name=\"user\" value=\""+request.getParameter("temp")+"\"/>");
					out.println("<input type=\"submit\" value=\"Player panel\"/>");
					out.println("</form>");
				
				}else {
					JSONObject result = new JSONObject().put("status", "not ok");
					out.println("<p>" + result.toString() + "</p>");
					out.println("<form method=\"POST\" action=\"PlayerServlet\" >");				
					out.println("<input type=\"hidden\" name=\"user\" value=\""+request.getParameter("temp")+"\"/>");
					out.println("<input type=\"submit\" value=\"Player Panel\"/>");
					out.println("</form>");
				}
			}
			else{
				JSONObject result = new JSONObject().put("status", "Please insert a positive digit");
				out.println("<p>" + result.toString() + "</p>");
				out.println("<form method=\"POST\" action=\"PlayerServlet\" >");				
				out.println("<input type=\"hidden\" name=\"user\" value=\""+request.getParameter("temp")+"\"/>");
				out.println("<input type=\"submit\" value=\"Player Panel\"/>");
				out.println("</form>");
			}
			
			
		} catch (NullPointerException e) {
		

		}
	}

}
