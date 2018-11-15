package com.merit.tlg.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.merit.tlg.dbutility.DBUtility;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		//doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		StringBuffer sb = new StringBuffer();

		if (request.getContentLength() > 0) {
			String line;

			try {
				BufferedReader rd = new BufferedReader(request.getReader());
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//out.println("GetPageDetails Request Data------>" + sb);
		}

		DBUtility dbutility = new DBUtility();
		
		JSONObject submit_questionnaire_request = new JSONObject(sb.toString());
		JSONObject submit_questionnaire_request_json = submit_questionnaire_request.getJSONObject("request");
		System.out.println("get_page_details request_json data---->" + submit_questionnaire_request_json);
        String user_id=null;
        String password=null;
		String final_response=null;

		if (submit_questionnaire_request_json.has("user_id")) {
			user_id = submit_questionnaire_request_json.getString("user_id");
		}
		if (submit_questionnaire_request_json.has("password")) {
			password = submit_questionnaire_request_json.getString("password");
		}
		if (submit_questionnaire_request_json.has("user_id")){
			final_response = dbutility.getLoginUserId(user_id);

			if (final_response != null) {
				out.println(final_response);	

			}else
			{
				final_response = "{\"Response\": [{\"ReqType\": \"Login\",\"Status\": \"-1\",\"ErrCode\": \"-1\",\"Description\": \"Description\"}]}";
				out.println(final_response);	
			}
			
		}else {
			out.println("Wrong requested data.");

		}
		
	}

}
