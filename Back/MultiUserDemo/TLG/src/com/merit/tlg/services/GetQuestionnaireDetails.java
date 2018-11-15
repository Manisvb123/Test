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
 * Servlet implementation class GetQuestionnaireDetails
 */
@WebServlet("/GetQuestionnaireDetails")
public class GetQuestionnaireDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		}

		DBUtility dbutility = new DBUtility();
		try {
			String requests = null;
			String projectid = null;
			String toolid = null;
			String templateid = null;
			String pageid = null;
			int uid = 0;

			String final_response;
			JSONObject get_questionnaire_request = new JSONObject(sb.toString());
			JSONObject get_questionnaire_request_json = get_questionnaire_request.getJSONObject("request");

			if (get_questionnaire_request_json.has("pid")) {
				projectid = get_questionnaire_request_json.getString("pid");
			}
			if (get_questionnaire_request_json.has("toolid")) {
				toolid = get_questionnaire_request_json.getString("toolid");
			}
			if (get_questionnaire_request_json.has("templateid")) {
				templateid = get_questionnaire_request_json.getString("templateid");
			}
			if (get_questionnaire_request_json.has("pageid")) {
				pageid = get_questionnaire_request_json.getString("pageid");
			}
			if (get_questionnaire_request_json.has("uid")) {
				uid = get_questionnaire_request_json.getInt("uid");
			}
			System.out.println("\nprojetid" + projectid + "\ntoolid---->" + toolid + "\ntemplateid--->" + templateid
					+ "\npageid---->" + pageid);

			if (get_questionnaire_request_json.has("pid") && get_questionnaire_request_json.has("toolid")
					&& get_questionnaire_request_json.has("templateid")
					&& get_questionnaire_request_json.has("pageid")&& get_questionnaire_request_json.has("uid")) {
				final_response = dbutility.getQuestionnaireDetails(toolid, projectid, pageid,uid);
				if (final_response != null) {
					out.println(final_response);
				} else {
					final_response = "{\"Response\": [{\"ReqType\": \"GetQuestionnaireDetails\",\"Status\": \"-1\",\"ErrCode\": \"-1\",\"Description\": \"Description\"}]}";
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
