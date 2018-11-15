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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		StringBuffer sb = new StringBuffer();
		String sb1=null ;

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
			
			 //sb1 = "{\r\n\t\"keys\": {\r\n\t\t\"uid\": \"rekhas\",\r\n\t\t\"pid\": \"Merit-2018\",\r\n\t\t\"toolid\": \"SST-001\",\r\n\t\t\"templateid\": \"SST-Template\",\r\n\t\t\"pageid\": \"PG-001\",\r\n\t\t\"submit_status\": \"draft\"\r\n\t},\r\n\t\"PageQuestions\": [{\r\n\t\t\t\"pd-name\": \"Robust\",\r\n\t\t\t\"sdlist\": [{\r\n\t\t\t\t\t\"sd-name\": \"Looking Outwards\",\r\n\t\t\t\t\t\"qlist\": [{\r\n\t\t\t\t\t\t\t\"QnNo\": \"1\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-001\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_choice\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t},\r\n\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\"QnNo\": \"3\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-003\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have explicitly considered current or potential  non-traditional competitors\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_Select\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t]\r\n\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"sd-name\": \"Looking inward\",\r\n\t\t\t\t\t\"qlist\": [{\r\n\t\t\t\t\t\t\t\"QnNo\": \"1\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-001\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_choice\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t},\r\n\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\"QnNo\": \"3\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-003\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have explicitly considered current or potential  non-traditional competitors\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_Select\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t]\r\n\r\n\t\t\t\t}\r\n\t\t\t]\r\n\t\t},\r\n\t\t{\r\n\t\t\t\"pd-name\": \"Resilient\",\r\n\t\t\t\"sdlist\": [{\r\n\t\t\t\t\t\"sd-name\": \"Looking Outwards\",\r\n\t\t\t\t\t\"qlist\": [{\r\n\t\t\t\t\t\t\t\"QnNo\": \"1\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-001\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_choice\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t],\r\n\t\t\t\t\t\t\t\"UsrOpt\": [\"1\"]\r\n\t\t\t\t\t\t},\r\n\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\"QnNo\": \"3\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-003\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have explicitly considered current or potential  non-traditional competitors\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_Select\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t]\r\n\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"sd-name\": \"Looking inward\",\r\n\t\t\t\t\t\"qlist\": [{\r\n\t\t\t\t\t\t\t\"QnNo\": \"1\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-001\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_choice\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t},\r\n\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\"QnNo\": \"3\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-003\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have explicitly considered current or potential  non-traditional competitors\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_Select\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t]\r\n\r\n\t\t\t\t}\r\n\t\t\t]\r\n\t\t}\r\n\t]\r\n}";

			 //out.println("GetQuestionnaireDetails Request Data------>" + sb1);
		}

		DBUtility dbutility = new DBUtility();
		try {
			String requests = null;
			String projectid = null;
			String toolid = null;
			String templateid = null;
			String pageid = null;
			String final_response;
			JSONObject get_savepage_request = new JSONObject(sb.toString());
		

			if (get_savepage_request != null) {
				final_response = dbutility.getSavePage(get_savepage_request);
				if(final_response !=null){
					out.println(final_response);

				}else 
				{
				out.println(final_response);
				//if final response is null return error json response:
				final_response = "{\r\n\t\"Response\": [{\r\n\t\t\"ReqType\": \"RequestedOperationType\",\r\n\t\t\"Status\": \"-1\",\r\n\t\t\"ErrCode\": \"-1\",\r\n\t\t\"Description\": \"Description\"\r\n\t}]\r\n}";
				out.println(final_response);
				}
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
