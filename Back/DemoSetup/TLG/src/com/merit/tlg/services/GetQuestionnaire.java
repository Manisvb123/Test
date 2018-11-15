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
 * Servlet implementation class GetQuestionnaire
 */
@WebServlet("/GetQuestionnaire")
public class GetQuestionnaire extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//out.println("GetQuestionnaire Request Data------>");
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
			//out.println("GetQuestionnaire Request Data------>" + sb);
		}

		DBUtility dbutility = new DBUtility();
		try {

			JSONObject get_questionnaire_request = new JSONObject(sb.toString());
			//out.println(" get_questionnaire_request data---->"+get_questionnaire_request);
			//System.out.println("get_questionnaire_request data---->"+get_questionnaire_request);
			JSONObject get_questionnaire_request_json = get_questionnaire_request.getJSONObject("request");
			//out.println("get_questionnaire_request_json  data---->"+get_questionnaire_request_json);
			//System.out.println("get_questionnaire_request_json data---->"+get_questionnaire_request_json);

			String toolid = null;
			String projectid = null;
			String templateid = null;
			String final_response;
			
			if (get_questionnaire_request_json.has("toolid")) {
				toolid = get_questionnaire_request_json.getString("toolid");
			}
			if (get_questionnaire_request_json.has("pid")) {
				projectid = get_questionnaire_request_json.getString("pid");
			}
			if (get_questionnaire_request_json.has("templateid")) {
				templateid = get_questionnaire_request_json.getString("templateid");
			}

			//System.out.println(
					//"\ntoolid---->" + toolid + "\nprojectid--->" + projectid + "\ntemplateid---->" + templateid);
			if ( get_questionnaire_request_json.has("toolid") && get_questionnaire_request_json.has("pid")
					&& get_questionnaire_request_json.has("templateid")) {
				final_response = dbutility.getQuestionnaire(toolid, projectid, templateid);
				out.println(final_response);
			}else {
				final_response = "{\"Response\": [{\"ReqType\": \"RequestedOperationType\",\"Status\": \"Status\",\"ErrCode\": \"ErrorCode\",\"Description\": \"Description\"}]}";
				out.println(final_response);

			}
			
       

		} catch (JSONException e) {
			e.printStackTrace();
		}

		out.close();
	}

}
