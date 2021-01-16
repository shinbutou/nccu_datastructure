
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		IMDBQuery X = new IMDBQuery(request.getParameter("keyword"));
		X.query();
		RequestGoogle Y = new RequestGoogle();
		Y.Request();
		//GoogleQuery Y=new GoogleQuery(request.getParameter("keyword"));
		//List<UrlTempResult> query=Y.query();
		//ArrayList <UrlResult> query= Y.Results;
		String[][] s = new String[Y.Results.size()][2];
		request.setAttribute("query",s);
		int num = 0;
		
		for(UrlResult r:Y.Results) {
		    String key = r.getUrl();
		    String value = r.gettopName();
		    s[num][0] = key;
		    s[num][1] = value;
		    num++;
		}
		request.getRequestDispatcher("GoogleResult.jsp")
		 .forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
