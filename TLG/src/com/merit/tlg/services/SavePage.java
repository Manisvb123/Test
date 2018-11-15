package com.merit.tlg.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.merit.tlg.dashboard.business.SSTDashboardService;
import com.merit.tlg.dbutility.DBUtility;

/**
 * Servlet implementation class SavePage
 */
@WebServlet("/SavePage")
public class SavePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SavePage() {
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		StringBuffer sb = new StringBuffer();
		String sb1 = null;

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

		}

		DBUtility dbutility = new DBUtility();
		try {
			String requests = null;
			String projectid = null;
			String toolid = null;
			String templateid = null;
			String pageid = null;
			String user_id = "rekhas";
			String team_id = "TM-001";

			String final_response;
			JSONObject get_savepage_request = new JSONObject(sb.toString());

			if (get_savepage_request != null) {
				final_response = dbutility.getSavePage(get_savepage_request);
				if (final_response != null) {
					out.println(final_response);

				} else {
					final_response = "{\r\n\t\"Response\": [{\r\n\t\t\"ReqType\": \"SavePage\",\r\n\t\t\"Status\": \"-1\",\r\n\t\t\"ErrCode\": \"-1\",\r\n\t\t\"Description\": \"Description\"\r\n\t}]\r\n}";
					out.println(final_response);
				}
			} else {
				out.println("Wrong requested data.");

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		out.close();

	}

}
