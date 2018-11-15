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

import com.merit.tlg.dbutility.DBUtility;

/**
 * Servlet implementation class SubmitQuestionnaire
 */
@WebServlet("/SubmitQuestionnaire")
public class SubmitQuestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitQuestionnaire() {
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
		try {
			String projectid = null;
			String toolid = null;
			String templateid = null;
			String uid = null;
			String final_response;
			JSONObject submit_questionnaire_request = new JSONObject(sb.toString());
			//out.println("get_page_details request data---->" + get_questionnaire_request);
			System.out.println("get_page_details request data---->" + submit_questionnaire_request);
			JSONObject submit_questionnaire_request_json = submit_questionnaire_request.getJSONObject("request");
			//out.println("get_page_details request_json data---->" + get_questionnaire_request_json);
			System.out.println("get_page_details request_json data---->" + submit_questionnaire_request_json);

			if (submit_questionnaire_request_json.has("pid")) {
				projectid = submit_questionnaire_request_json.getString("pid");
			}
			if (submit_questionnaire_request_json.has("toolid")) {
				toolid = submit_questionnaire_request_json.getString("toolid");
			}
			if (submit_questionnaire_request_json.has("templateid")) {
				templateid = submit_questionnaire_request_json.getString("templateid");
			}
			if (submit_questionnaire_request_json.has("uid")) {
				uid = submit_questionnaire_request_json.getString("uid");
			}
			//System.out.println("\nprojetid" + projectid + "\ntoolid---->" + toolid + "\ntemplateid--->" + templateid
					//+ "\npageid---->" + pageid);

			if (submit_questionnaire_request_json.has("pid") && submit_questionnaire_request_json.has("toolid")
					&& submit_questionnaire_request_json.has("templateid")
					&& submit_questionnaire_request_json.has("uid")) {
				final_response = dbutility.submitQuestionnaireResponse(projectid, toolid, templateid, uid);
				out.println(final_response);

			} else {
				final_response = "{\"Response\": [{\"ReqType\": \"RequestedOperationType\",\"Status\": \"Status\",\"ErrCode\": \"ErrorCode\",\"Description\": \"Description\"}]}";
				out.println(final_response);

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		out.close();
		
		
		
	}

}
