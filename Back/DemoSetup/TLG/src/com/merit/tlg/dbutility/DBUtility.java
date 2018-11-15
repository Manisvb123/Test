package com.merit.tlg.dbutility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.merit.tlg.dbutility.GetProperties;

@SuppressWarnings("unused")
public class DBUtility {
  CloseConnections close=new CloseConnections();


	
	Connection connObj = null;
	Connection connObj1 = null;
	Connection connObj2 = null;
	Connection connObj3 = null;
	Connection connObj4= null;
	Connection connObj5 = null;
	Connection connObj6 = null;
	Connection connObj7 = null;
	Connection connResponseMetric = null;
	Connection connObjgetQuetionnaireDetails = null;

	Statement stmtObj_user_id = null;
	Statement stmtObj = null;
	Statement stmtObj_dimention = null;
	Statement stmtObj_status = null;
	Statement stmtObj_max_score = null;
	Statement stmtObj_resp_record = null;
	Statement stmtObj_update_resp_record = null;
	Statement stmtObj_insert_resp_record = null;
	Statement stmtObj_metric_id = null;
	Statement stmtResponseMetric = null;
	
	
	ResultSet rsObj = null;
	ResultSet rsObj_dimention = null;
	ResultSet rsObj_status = null;
	ResultSet rsObj_max_score = null;
	ResultSet rsObj_resp_record = null;
	ResultSet rsObj_metric_id = null;
	ResultSet rsObj_user_id = null;
	ResultSet rsResponseMetric = null;

//	public Connection getConnection() throws Exception {
//		Connection con = null;
//		try {
//	        GetProperties p=new GetProperties();
//	        Properties pro=p.getProperties();
//			Class.forName(GetProperties.JDBC_DRIVER);
//		    con = DriverManager.getConnection(GetProperties.JDBC_DB_URL, GetProperties.USER_NAME, GetProperties.PASSWORD);
//		
//		} catch (Exception e) {
//			System.out.println("Exception: " + e);
//		} finally {
//		}
//
//		return con;
//
//	}
	
	public Connection getConnection() throws Exception {
		InputStream inputStream = null;
		String JDBC_DRIVER = null;
		String JDBC_DB_URL = null;
		String USER_NAME = null;
		String PASSWORD = null;
		Connection con = null;
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			Date time = new Date(System.currentTimeMillis());
 
			// get the property value and print it out
		    JDBC_DRIVER = prop.getProperty("JDBC_DRIVER");
		    System.out.println("JDBC_DRIVER---------->"+JDBC_DRIVER);
		    JDBC_DB_URL = prop.getProperty("JDBC_DB_URL");
		    System.out.println("JDBC_DB_URL---------->"+JDBC_DB_URL);
		    USER_NAME = prop.getProperty("USER_NAME");
		    System.out.println("USER_NAME---------->"+USER_NAME);
		    PASSWORD = prop.getProperty("PASSWORD");
		    System.out.println("PASSWORD---------->"+PASSWORD);

			Class.forName(JDBC_DRIVER);
		    con = DriverManager.getConnection(JDBC_DB_URL, USER_NAME, PASSWORD);
		
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}



		return con;

	}
/**********************************************getQuestionnaire START************************************************/
	@SuppressWarnings("unchecked")
	public String getQuestionnaire(String toolid, String projectid, String templateid) {
		System.out.println("In side getQuestionnaire......................");
		System.out.println("In side getQuestionnaire toolid......................" + toolid);
		System.out.println("In side getQuestionnaire projectid......................" + projectid);
		System.out.println("In side getQuestionnaire templateid......................" + templateid);
		String response = null;
		try {
			connObj = getConnection();
			stmtObj = connObj.createStatement();
			String sql = "select * from tabQuestionnaire where tool_id='" + toolid + "' and project_id='" + projectid
					+ "' and template_id ='" + templateid + "' and version='v1.0'";
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@_------------->"+sql);
			rsObj = stmtObj.executeQuery(sql);
			System.out.println("RS_OBJ-->" + rsObj);
			String page_id = null;
			int no_of_pages = 0;
			int page_no = 0;
			String version = null;
			String template_name = null;
			JSONArray rootjosnArray = new JSONArray();
			JSONObject rootJson = new JSONObject();
			JSONObject subJson = new JSONObject();
			while (rsObj.next()) {
				JSONObject obj = new JSONObject();

				page_id = rsObj.getString("page_id");
				no_of_pages = rsObj.getInt("no_of_pages");
				page_no = rsObj.getInt("page_no");
				version = rsObj.getString("version");
				template_name = rsObj.getString("template_name");

				System.out.println(
						"\n page_id---->" + page_id + "\n no_of_pages--->" + no_of_pages + "\n page_no---->" + page_no);

				obj.put("pgid", page_id);
				obj.put("pgno", page_no);
				rootjosnArray.put(obj);

			}
			System.out.println("Json Array-------->" + rootjosnArray);

			subJson.put("userid", "userid");
			subJson.put("prjid", projectid);
			subJson.put("toolid", toolid);
			subJson.put("toolver", version);
			subJson.put("tmplname", template_name);
			subJson.put("tmplver", "TemplateVersion");
			subJson.put("numpgs", no_of_pages);
			subJson.put("pagelst", rootjosnArray);
			rootJson.put("Questionnaire", subJson);
			response = rootJson.toString();

			System.out.println("rootJson---->" + response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				close.closeStatement(stmtObj);
				close.closeConnection(connObj);
				close.closeResultSet(rsObj);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return response;

	}
/**********************************************getQuestionnaire END************************************************/
	
/**********************************************getQuestionnaireDetails START************************************************/

	@SuppressWarnings("unchecked")
	public String getQuestionnaireDetails(String toolid, String projectid, String pageid) {
		System.out.println("In side getQuestionnaireDetails......................");
		System.out.println("In side getQuestionnaireDetails toolid......................" + toolid);
		System.out.println("In side getQuestionnaireDetails projectid......................" + projectid);
		System.out.println("In side getQuestionnaireDetails pageid......................" + pageid);

		String question_detail_response = null;
		try {
			connObjgetQuetionnaireDetails = getConnection();
			stmtObj = connObjgetQuetionnaireDetails.createStatement();
			String sql = "select * from tabQuestionnaire where tool_id ='" + toolid + "' and project_id ='" + projectid
					+ "' and page_id ='" + pageid + "'";
			System.out.println("sql--->"+sql);
			// String sql="select * from tabQuestionnaire where tool_id ='SST-001' and
			// project_id ='PR-001' and page_id ='PG-001'";
			rsObj = stmtObj.executeQuery(sql);
			System.out.println("RS_OBJ-->" + rsObj);
			String page_id = null;
			int no_of_pages = 0;
			int page_no = 0;
			String version = null;
			String template_name = null;
			String title = null;
			String instruction_to_fill = null;
			String remarks = null;

			JSONObject rootjsonObj = new JSONObject();
			JSONObject subjsonObj = new JSONObject();
			JSONObject childjsonObj = new JSONObject();
			while (rsObj.next()) {
				page_id = rsObj.getString("page_id");
				no_of_pages = rsObj.getInt("no_of_pages");
				page_no = rsObj.getInt("page_no");
				version = rsObj.getString("version");
				template_name = rsObj.getString("template_name");
				title = rsObj.getString("title");
				instruction_to_fill = rsObj.getString("instruction_to_fill");
				remarks = rsObj.getString("remarks");

			}

			childjsonObj.put("title", title);
			childjsonObj.put("inst", instruction_to_fill);
			childjsonObj.put("Rmrks", remarks);

			subjsonObj.put("userid", "userid");
			subjsonObj.put("prjid", projectid);
			subjsonObj.put("toolid", toolid);
			subjsonObj.put("toolver", version);
			subjsonObj.put("tmplname", template_name);
			subjsonObj.put("tmplver", "tmplver");
			subjsonObj.put("pgno", page_no);
			subjsonObj.put("pgid", pageid);
			subjsonObj.put("numqns", "");

			subjsonObj.put("commonInfo", childjsonObj);

			rootjsonObj.put("Page", subjsonObj);
			question_detail_response = rootjsonObj.toString();
			System.out.println("rootJson---->" + question_detail_response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
//				close.closeStatement(stmtObj);
//				close.closeConnection(connObj);
//				close.closeResultSet(rsObj);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return question_detail_response;
	}
/**********************************************getQuestionnaireDetails END************************************************/
	
/**********************************************getPageDetails************************************************/
	@SuppressWarnings("unchecked")
	public String getPageDetails(String projectid, String toolid, String templateid, String pageid) {
		String user_id = "rekhas";
		int id=3;
		System.out.println("In side getPageDetails......................");
		System.out.println("In side getPageDetails projectid......................" + projectid);
		System.out.println("In side getPageDetails toolid......................" + toolid);
		System.out.println("In side getPageDetails templateid......................" + templateid);
		System.out.println("In side getPageDetails pageid......................" + pageid);
		System.out.println();
		System.out.println();
		System.out.println();

		String page_detail_response = null;
		
		String tool_id = null;
		String project_id = null;
		String page_id = null;
		int dimension_id = 0;
		int no_of_questions = 0;
		int question_no = 0;
		String question_context = null;
		String question_id = null;
		String response_metric_id = null;
		String response_metric_name = null;
		String reponse_options_label = null;
		String remarks = null;
		String created_by = null;
		String creation_date = null;
		String last_modified_date = null;
		String question_description = null;
		String question_type = null;
		String option_ids = null;
		String parent_dimention = null;
		String secondary_dimention = null;
		JSONObject subjsonObj = new JSONObject();
		JSONArray optlbl_array = new JSONArray();
		JSONArray metric_key_array = new JSONArray();
		JSONObject metric_optlbel_object = new JSONObject();
		Boolean selected;

		JSONObject rootjsonObj = new JSONObject();
		JSONArray PageQuestionsArray = new JSONArray();
		JSONObject szPageQues = null;
		JSONObject szSecDim = null;
		JSONObject szQues = null;
		JSONArray szSecDimAr = null;
		JSONArray szQuesAr = null;
		JSONArray szResMetAr = null;
		JSONObject szResMet = null;
		int pd_id = -1;
		try {
			connObj = getConnection();
			stmtObj = connObj.createStatement();

			String sql = "select * from tabPages where tool_id ='" + toolid + "' and project_id ='" + projectid
					+ "' and page_id ='" + pageid + "'  ORDER BY question_no ";
			// and question_id='Q-100'
			System.out.println("sql-------------->" + sql);

			rsObj = stmtObj.executeQuery(sql);
			System.out.println("RS_OBJ-->" + rsObj);
			

			while (rsObj.next()) {
				JSONObject sqlist_object = new JSONObject();

				tool_id = rsObj.getString("tool_id");
				project_id = rsObj.getString("project_id");
				page_id = rsObj.getString("page_id");
				dimension_id = rsObj.getInt("dimension_id");
				no_of_questions = rsObj.getInt("no_of_questions");
				question_no = rsObj.getInt("question_no");

				System.out.println("question_no------------------------>" + question_no);
				question_context = rsObj.getString("question_context");
				question_id = rsObj.getString("question_id");
				response_metric_id = rsObj.getString("response_metric_id");
				reponse_options_label = rsObj.getString("reponse_options_label");

				remarks = rsObj.getString("remarks");
				created_by = rsObj.getString("created_by");
				creation_date = rsObj.getString("creation_date");
				last_modified_date = rsObj.getString("last_modified_date");
				question_description = rsObj.getString("question_description");
				question_type = rsObj.getString("question_type");
				option_ids = rsObj.getString("option_ids");
				sqlist_object.put("QnNo", question_no);
				sqlist_object.put("QnID", question_id);
				sqlist_object.put("QnCOntext", question_context);
				sqlist_object.put("Qn", question_description);
				sqlist_object.put("Type", question_type);

				String[] variables_1 = option_ids.split(",");

				String status = null;
				int option_id = 0;

				String status_query = "select option_id,status from tabQuestionnaireResponses where template_id ='"
						+ templateid + "'" + " AND tool_id ='" + toolid + "' AND project_id ='" + projectid
						+ "' AND user_id ='" + id + "' AND entity_type ='question' " + "AND entity_id ='"
						+ question_id + "' AND  response_metric ='" + response_metric_id
						+ "' AND secondary_dimension_id ='" + dimension_id + "'";

				System.out.println("status_query-------------------->" + status_query);

				stmtObj_status = connObj.createStatement();
				rsObj_status = stmtObj_status.executeQuery(status_query);
				System.out.println("rsObj_status-->" + rsObj_status);

				ArrayList<Integer> dimention_id_array_list = new ArrayList<>();

				while (rsObj_status.next()) {
					status = rsObj_status.getString("status");
					option_id = rsObj_status.getInt("option_id");
					System.out.println("status-->" + status);
					System.out.println("option_id-->" + option_id);
					dimention_id_array_list.add(option_id);
				}

				System.out.println("dimention_id_array_list------->" + dimention_id_array_list);
				/*********/
				String dimention_query = "select pd.dimension_id as pd_id, pd.dimension_name as pd_name, sd.dimension_name as sd_name from tabQuestionDimensions sd join tabQuestionDimensions pd on sd.parent_dimension = pd.dimension_id where sd.dimension_id='"
						+ dimension_id + "' and sd.tool_id='" + toolid + "'";
				System.out.println("sql_1-------------------->" + dimention_query);
				stmtObj_dimention = connObj.createStatement();
				rsObj_dimention = stmtObj_dimention.executeQuery(dimention_query);
				System.out.println("rsObj_dimention-->" + rsObj_dimention);
				while (rsObj_dimention.next()) {
					parent_dimention = rsObj_dimention.getString("pd_name");
					secondary_dimention = rsObj_dimention.getString("sd_name");
					pd_id = rsObj_dimention.getInt("pd_id");
					System.out.println("parent_dimention-->" + parent_dimention);
					System.out.println("secondary_dimention-->" + secondary_dimention);
				}
				
				String resMetric_query = "select response_metric from tabResponseMetrics  where response_metric_id='"
						+ response_metric_id + "'";
				System.out.println("sql_1-------------------->" + resMetric_query);
				stmtObj_dimention = connObj.createStatement();
				rsObj_dimention = stmtObj_dimention.executeQuery(resMetric_query);
				System.out.println("rsObj_dimention-->" + rsObj_dimention);
				while (rsObj_dimention.next()) {
					response_metric_name = rsObj_dimention.getString("response_metric");
				}

				String[] res_met_label_name_array = null;

				res_met_label_name_array = reponse_options_label.split(",");
				metric_optlbel_object = new JSONObject();
				metric_key_array = new JSONArray();
				for (int i = 0; i < res_met_label_name_array.length; i++) { // Response Option
					JSONObject optlbl_object_1 = new JSONObject();
					String[] label_type_array = res_met_label_name_array[i].split("-");
					String label_type;
					String radio_type;
					if (label_type_array.length > 1) {
						label_type = label_type_array[0];
						radio_type = label_type_array[1];
					} else {
						label_type = res_met_label_name_array[i];
						radio_type = "radio";
					}

					String labelid = variables_1[i];

					int label_ids = Integer.parseInt(labelid);

					if (dimention_id_array_list.contains(label_ids)) {
						selected = true;
					} else {
						selected = false;
					}
					optlbl_object_1.put("labelname", label_type);
					optlbl_object_1.put("labelid", labelid);
					optlbl_object_1.put("type", radio_type);

					optlbl_object_1.put("selected", selected);

					metric_key_array.put(optlbl_object_1);
					metric_optlbel_object.put("metric", response_metric_name);
					metric_optlbel_object.put("Optlbl", metric_key_array);

				} // Response Option Label
				System.out.println("metric_optlbel_object::" + metric_optlbel_object.toString());
				optlbl_array = new JSONArray();
				optlbl_array.put(metric_optlbel_object);

				System.out.println("------00000====>" + optlbl_array);

				szPageQues = findElement(PageQuestionsArray, "pd_id", pd_id + "");
				System.out.println("szPageQues::" + szPageQues);
				System.out.println("dimension_id::"+dimension_id);
				

				if (szPageQues != null) {
					szSecDimAr = szPageQues.getJSONArray("sdlist");
					System.out.println("szSecDimAr::" + szSecDimAr);
					if (szSecDimAr != null) {
						System.out.println("dimension_id::"+dimension_id);
						szSecDim = findElement(szSecDimAr, "sd_id", dimension_id + "");
						System.out.println("szPageQues::" + szSecDim);
						if (szSecDim != null) {
							szQuesAr = szSecDim.getJSONArray("qlist");
							System.out.println("szQuesAr::" + szQuesAr);
							if (szQuesAr != null) {
								System.out.println("question_id::" + question_id);
								szQues = findElement(szQuesAr, "QnID", question_id);
								System.out.println("szQues::" + szQues);
								if (szQues != null) {
									szResMetAr = szQues.getJSONArray("metrics");
									System.out.println("szResMetAr::" + szResMetAr);
									if (szResMetAr != null) {
										System.out.println("optlbl_array::" + optlbl_array);
										szResMetAr.put(optlbl_array.get(0));
									} else {
										szQues.put("metrics", optlbl_array);
									}
								} else {
									szQues = new JSONObject();
									szQues.put("QnNo", question_no);
									szQues.put("QnID", question_id);
									szQues.put("QnCOntext", question_context);
									szQues.put("Qn", question_description);
									szQues.put("Type", question_type);
									szQues.put("metrics", optlbl_array);
									szQuesAr.put(szQues);
								}
							} else {
								szQuesAr = new JSONArray();
								szQues = new JSONObject();
								szQues.put("QnNo", question_no);
								szQues.put("QnID", question_id);
								szQues.put("QnCOntext", question_context);
								szQues.put("Qn", question_description);
								szQues.put("Type", question_type);
								szQues.put("metrics", optlbl_array);
								szQuesAr.put(szQues);
								szSecDim.put("qlist", szQuesAr);
							}
						} else {
							szSecDim = new JSONObject();
							szQues = new JSONObject();
							szQues.put("QnNo", question_no);
							szQues.put("QnID", question_id);
							szQues.put("QnCOntext", question_context);
							szQues.put("Qn", question_description);
							szQues.put("Type", question_type);
							szQues.put("metrics", optlbl_array);
							szQuesAr = new JSONArray();
							szQuesAr.put(szQues);
							szSecDim.put("qlist", szQuesAr);
							szSecDim.put("sd_name", secondary_dimention);
							szSecDim.put("sd_id", dimension_id);
							szSecDimAr.put(szSecDim);
						}
					} else {
						szSecDimAr = new JSONArray();
						szQuesAr = new JSONArray();
						szSecDim = new JSONObject();
						szQues = new JSONObject();
						szQues.put("QnNo", question_no);
						szQues.put("QnID", question_id);
						szQues.put("QnCOntext", question_context);
						szQues.put("Qn", question_description);
						szQues.put("Type", question_type);
						szQues.put("metrics", optlbl_array);
						szQuesAr.put(szQues);
						szSecDim.put("qlist", szQuesAr);
						szSecDim.put("sd_name", secondary_dimention);
						szSecDim.put("sd_id", dimension_id);
						szSecDimAr.put(szSecDim);
						szQuesAr.put(szQues);
					}
				} else {
					szQues = new JSONObject();
					szQues.put("QnNo", question_no);
					szQues.put("QnID", question_id);
					szQues.put("QnCOntext", question_context);
					szQues.put("Qn", question_description);
					szQues.put("Type", question_type);
					szQues.put("metrics", optlbl_array);
					szQuesAr = new JSONArray();
					szQuesAr.put(szQues);
					szSecDim = new JSONObject();
					szSecDim.put("qlist", szQuesAr);
					szSecDim.put("sd_name", secondary_dimention);
					szSecDim.put("sd_id", dimension_id);
					szSecDimAr = new JSONArray();
					szSecDimAr.put(szSecDim);
					szPageQues = new JSONObject();
					szPageQues.put("sdlist", szSecDimAr);
					szPageQues.put("pd_name", parent_dimention);
					szPageQues.put("pd_id", pd_id);
					PageQuestionsArray.put(szPageQues);
				}

			}
			rootjsonObj.put("PageQuestions", PageQuestionsArray);subjsonObj.put("uid", user_id);
			subjsonObj.put("toolid", toolid);
			subjsonObj.put("templateid", templateid);
			subjsonObj.put("pageid", pageid);
			subjsonObj.put("pid", projectid);
			subjsonObj.put("no_of_questions", no_of_questions);
			rootjsonObj.put("keys", subjsonObj);

			page_detail_response = rootjsonObj.toString();
			System.out.println("rootJson---->" + page_detail_response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmtObj != null) {
					stmtObj.close();
				}
				if (connObj != null) {
					connObj.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return page_detail_response;
	}


	public JSONObject findElement(JSONArray szJArray, String key, String value) {
		try {
			for (int i = 0; i < szJArray.length(); i++) {
				if (((JSONObject) szJArray.get(i)).getString(key).equals(value)) {
					return (JSONObject) szJArray.get(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
/**********************************************getPageDetails************************************************/

/**********************************************getSavePage************************************************/

	@SuppressWarnings("unchecked")
	public String getSavePage(org.json.JSONObject get_savepage_request) {
        String save_page_response=null;
        int score=0;
        int max_score=0;
		System.out.println("Entered into getSavePage-------------------------------------!"+get_savepage_request);
		try {
			//get_savepage_request = new JSONObject(jsons.toString());
			
	        String keys_key = (String) get_savepage_request.keys().next();
	        System.out.println("keys_key====================>"+keys_key);
			JSONObject kesy_json_string = get_savepage_request.getJSONObject(keys_key);
	        String page_question_key = (String) kesy_json_string.keys().next();
			String toolid=kesy_json_string.getString("toolid");
			String projectid=kesy_json_string.getString("pid");
			String pageid=kesy_json_string.getString("pageid");
			
			System.out.println("PAGE ID -------------->"+pageid);
			String templateid=kesy_json_string.getString("templateid");
			String user_id=kesy_json_string.getString("uid");
			int id = 0;

			try {
				connObj1 = getConnection();
				stmtObj_user_id = connObj1.createStatement();
				
				
				String sql = "select * from tabUser where user_id='"+user_id+"' ";
				//and question_id='Q-100'
				System.out.println("sql-------------->" + sql);

				rsObj_user_id = stmtObj_user_id.executeQuery(sql);
				System.out.println("RS_OBJ-->" + rsObj);
				while (rsObj_user_id.next()) {
					id=rsObj_user_id.getInt("id");
					
				}
				System.out.println("user_id_-------------"+id);
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {
				try {
					if(connObj1 !=null){
						connObj1.close();
					}
					if(stmtObj_user_id !=null){
						stmtObj_user_id.close();
					}
					if(rsObj_user_id !=null){
						rsObj_user_id.close();
					}

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			
			org.json.JSONArray PageQuestions = get_savepage_request.getJSONArray("PageQuestions");

			
	
			System.out.println("keys data---->" + kesy_json_string);
			System.out.println("PageQuestions data---->" + PageQuestions);
			System.out.println();
            int primary_dinmention_id=0;
			for (int i = 0; i < PageQuestions.length(); i++) {
				JSONObject json = PageQuestions.getJSONObject(i);
				/****/
				Iterator<String> pq_pd_id = json.keys();
				while (pq_pd_id.hasNext()) {
					String pq_keys = pq_pd_id.next();
                    if(pq_keys.equals("pd_id")){
                    	primary_dinmention_id=(int) json.get(pq_keys);
						System.out.println("primary_dinmention_id --------------> :" +primary_dinmention_id);
                    }
				}
				Iterator<String> keys = json.keys();
				// System.out.println("json data---->"+json);

				while (keys.hasNext()) {
					String key = keys.next();
					System.out.println("Pagequestions Key :" + key + "\nPagequestions  Value :" + json.get(key));
					if (key.equals("sdlist")) {
						org.json.JSONArray sdlist_array = (org.json.JSONArray) json.get(key);
						//System.out.println("sdlist_array======" + sdlist_array);

						for (int j = 0; j < sdlist_array.length(); ++j) {
							JSONObject qlist = sdlist_array.getJSONObject(j);
					        String qlist_key = (String) qlist.keys().next();
					        System.out.println("qlist_key--------------------->"+qlist_key);
							/****/
							Iterator<String> ql_kry = qlist.keys();
                            int sd_id=0;
                            String sd_name=null;
							while (ql_kry.hasNext()) {
								String ql_keys = ql_kry.next();
								//System.out.println(">>>>>>>>>>>>>> ql_kry :" + ql_keys + "  Value :" + qlist.get(ql_keys));
                               if(ql_keys.equals("sd_id")){
                            	   sd_id=(int) qlist.get(ql_keys);
   								System.out.println("SD_ID -------------->: " +sd_id);
                               }
                               if(ql_keys.equals("sd_name")){
                            	   sd_name=(String) qlist.get(ql_keys);
   								System.out.println("QLIST JSON -------------->: " +qlist);
                               }
							}//qlist while loop
							/*****/
							System.out.println("SD NAME -------------->: " +sd_name);
							
							org.json.JSONArray qlist_arrays = (org.json.JSONArray) qlist.get(qlist_key);
							System.out.println("QLIST ARRAY-------------->: " + qlist_arrays);
							
					
							
							for (int k = 0; k < qlist_arrays.length(); ++k) {
								JSONObject qlist_josn = qlist_arrays.getJSONObject(k);
						        
								System.out.println("QLIST JSON------------>: "+qlist_josn);
								JSONObject qlist_josn_string = new JSONObject(qlist_josn.toString());
								String type= qlist_josn_string.getString("Type");
								System.out.println("type---"+type);
								String QnNo= qlist_josn_string.getString("QnNo");
								System.out.println("QnNo---"+QnNo);
								String QnID= qlist_josn_string.getString("QnID");
								System.out.println("QnID---"+QnID);
								String Qn= qlist_josn_string.getString("Qn");
								System.out.println("Qn---"+Qn);
								org.json.JSONArray metric_array= qlist_josn_string.getJSONArray("metrics");
								System.out.println("Optlbl_array---"+metric_array);	
								for (int l = 0; l < metric_array.length(); ++l) {
									
									JSONObject Optlbl_array_json = metric_array.getJSONObject(l);
							        String metric_key = (String) Optlbl_array_json.keys().next();
							        System.out.println("metric_key------------>"+metric_key);
									System.out.println("metrics #############"+Optlbl_array_json);
									///********////
                                    String metrics_list=Optlbl_array_json.getString(metric_key);
    								org.json.JSONArray oplbl_list_array= Optlbl_array_json.getJSONArray("Optlbl");
    								max_score = 0;

									System.out.println("metrics_list #############\n########\n------->"+metrics_list);
									System.out.println("oplbl_list_array #############"+oplbl_list_array);String response_metric_id=null;
									try {
										connResponseMetric = getConnection();
										stmtResponseMetric = connResponseMetric.createStatement();
										String res_metric_name_sql="select response_metric_id from tabResponseMetrics where response_metric='"+metrics_list+"'";
                                        System.out.println("====>"+res_metric_name_sql);
										rsResponseMetric=stmtResponseMetric.executeQuery(res_metric_name_sql);
										while(rsResponseMetric.next()){
											response_metric_id=rsResponseMetric.getString("response_metric_id");
											System.out.println("response_metric_id   ===>  "+response_metric_id);
										}
									} catch (Exception e) {
										// TODO: handle exception
									}finally {
										try {
											if(connResponseMetric !=null){
												connResponseMetric.close();
											}
											if(stmtResponseMetric !=null){
												stmtResponseMetric.close();
											}
											if(rsResponseMetric !=null){
												rsResponseMetric.close();
											}
										} catch (Exception e2) {
											// TODO: handle exception
										}
									}
									System.out.println("response_metric_id####################################-------------------->"+response_metric_id);

									for (int m = 0; m < oplbl_list_array.length(); ++m) {///////////////
										JSONObject oplbl_list_array_values = oplbl_list_array.getJSONObject(m);
										System.out.println("oplbl_list_array_values------------>: "+oplbl_list_array_values);
									
									///********////
									System.out.println("labelid====>"+oplbl_list_array_values.getString("labelid"));
									System.out.println("type====>"+oplbl_list_array_values.getString("type"));
									System.out.println("labelname====>"+oplbl_list_array_values.getString("labelname"));
									System.out.println("selected====>"+oplbl_list_array_values.getBoolean("selected"));
                                    String labelid=oplbl_list_array_values.getString("labelid");
                                    String option_type=oplbl_list_array_values.getString("type");
                                    String labelname=oplbl_list_array_values.getString("labelname");
                                    boolean selected=oplbl_list_array_values.getBoolean("selected");
                                    //String response_metrics=null;
									/**************************/
									
									
									/*************************/
									if(selected == true){
										
										System.out.println("slected labelid "+labelid+" is--->"+selected);
										/******** First Connection Of Try block ***********/
										try {
											connObj = getConnection();
											stmtObj = connObj.createStatement();
											String sql_score = "SELECT Score FROM tabUserOptionScore WHERE tool_id='"+toolid+"' AND question_id='"+QnID+"' AND option_id='"+labelid+"' ";
											System.out.println("sql_score-------------->" + sql_score);

											rsObj = stmtObj.executeQuery(sql_score);
											System.out.println("Score rsObj----->" + rsObj);
											int tota_score=0;
											score=0;
											while(rsObj.next()){//SCORE rsObj
												score=rsObj.getInt("Score");
												tota_score=tota_score + score;
												System.out.println("score------->"+score);
												System.out.println("tota_score------->"+tota_score);								
											}//SCORE rsObj End
												
												try {
											
												connObj2 = getConnection();
												stmtObj_max_score = connObj2.createStatement();
												String sql_max_score = "SELECT max_score FROM tabPages WHERE tool_id='"+toolid+"' AND  project_id='"+projectid+"' AND page_id='"+pageid+"' AND question_id='"+QnID+"'  AND dimension_id=(SELECT dimension_id FROM tabQuestionDimensions WHERE dimension_id='"+sd_id+"')";
												System.out.println("sql_max_score-------------->" + sql_max_score);
												rsObj_max_score=stmtObj_max_score.executeQuery(sql_max_score);
												System.out.println("sql_max_score rsObj----->" + rsObj_max_score);
												float total_percentage_score;
												while(rsObj_max_score.next()){//MAX SCORE rsObj_max_score
													
													 max_score=rsObj_max_score.getInt("max_score");
													System.out.println("------------>max_score------->"+max_score +"----"+score);
												}//MAX SCORE rsObj_max_score End
													//int percentage_score=(score/max_score)*100;
													    float percentage_score = ((float) score) / ((float) max_score);
													    total_percentage_score= percentage_score * 100;
													System.out.println("------------>percentage_score------->"+total_percentage_score);
                                                   


													try {
														
													connObj3 = getConnection();
													stmtObj_resp_record = connObj3.createStatement();
													//String sql_resp_record= "select * from tabQuestionnaireResponses where template_id='"+templateid+"' and tool_id='"+toolid+" and project_id='PR-001' and user_id='"+user_id+"' and page_id='"+pageid+"' and question_id='"+QnID+"' and primary_dimension_id in (select dimension_id from tabQuestionDimensions where dimension_id=5) and secondary_dimension_id in (select dimension_id from tabQuestionDimensions where dimension_id=5)";
													String sql_resp_record=" select * from tabQuestionnaireResponses where template_id='"+templateid+"' and tool_id='"+toolid+"' and project_id='"+projectid+"' and user_id='"+id+"' and page_id='"+pageid+"' and question_id='"+QnID+"' and primary_dimension_id in (select dimension_id from tabQuestionDimensions where dimension_id='"+sd_id+"') and secondary_dimension_id in (select dimension_id from tabQuestionDimensions where dimension_id='"+sd_id+"') and response_metric='"+response_metric_id+"'";
													System.out.println("sql_resp_record-------------->" + sql_resp_record);
													rsObj_resp_record=stmtObj_resp_record.executeQuery(sql_resp_record);
													System.out.println("rsObj_resp_record rsObj----->" + rsObj_resp_record);
													int number_of_updates=0;
													if(rsObj_resp_record.isBeforeFirst()){
													while(rsObj_resp_record.next()){
														number_of_updates=rsObj_resp_record.getInt("number_of_updates");
														System.out.println("number_of_updates !!!!!!!!!!! "+number_of_updates);
														System.out.println("LabelId !!!!!!!!!!! "+labelid);
														System.out.println("labelname !!!!!!!!!!! "+labelname);
														System.out.println("total_percentage_score !!!!!!!!!!! "+total_percentage_score);
												        //method 1
												        Timestamp last_modified_date = new Timestamp(System.currentTimeMillis());
												        System.out.println(last_modified_date);
												          try {
															
													      System.out.println("UPDATING tabQuestionnaireResponses..............");
													      connObj4 = getConnection();
													      stmtObj_update_resp_record = connObj4.createStatement();
													      String sql = "UPDATE tabQuestionnaireResponses SET number_of_updates = '"+number_of_updates+"' ,option_id='"+labelid+"',option_label='"+labelname+"',last_modified_date ='"+last_modified_date+"',score='"+score+"',max_score='"+max_score+"',percentage_score='"+total_percentage_score+"' WHERE template_id='"+templateid+"' AND tool_id='"+toolid+"' AND project_id='"+projectid+"' AND user_id='"+id+"' AND page_id='"+pageid+"' AND question_id='"+QnID+"' AND primary_dimension_id ='"+sd_id+"' AND secondary_dimension_id='"+sd_id+"' AND  response_metric='"+response_metric_id+"'";
													      System.out.println("Update-------"+sql);
													      int result = stmtObj_update_resp_record.executeUpdate(sql);
													      System.out.println("Update Response====>"+result);
													      if(result == 1){
													      save_page_response="Success";
													      }else
													      {
														  save_page_response="Failed";

													      }
												  		} catch (Exception e) {
															// TODO: handle exception
														}finally {
															try {
																if(connObj4 !=null){
																	connObj4.close();
																}
																if(stmtObj_update_resp_record !=null){
																	stmtObj_update_resp_record.close();
																}
															} catch (Exception e2) {
																// TODO: handle exception
															}
														}
													}
													
													
													}else
													{
														  System.out.println("NO DATA ----------->");
                                                          try {
															

													      connObj5 = getConnection();
													      stmtObj_insert_resp_record = connObj5.createStatement();
													      String team_id="";
													      String response_type="";
													      String response_metric=response_metric_id;
													      String status="draft";
													      String role="";
													      String entity_type="question";

													        Timestamp request_time_stamp = new Timestamp(System.currentTimeMillis());
													        Timestamp response_time_stamp = new Timestamp(System.currentTimeMillis());
													        Timestamp creation_date = new Timestamp(System.currentTimeMillis());
													        Timestamp last_modified_date = new Timestamp(System.currentTimeMillis());

													        String sql = "INSERT INTO tabQuestionnaireResponses ("
													    		  		+ "template_id,"
													    		  		+ "tool_id,"
													    		  		+ "project_id,"
													    		  		+ "user_id,"
													    		  		+ "team_id,"
													    		  		+ "role,"
													    		  		+ "question_id,"
													    		  		+ "primary_dimension_id,"
													    		  		+ "secondary_dimension_id,"
													    		  		+ "response_type,"
													    		  		+ "response_metric,"
													    		  		+ "option_id,"
													    		  		+ "score,"
													    		  		+ "percentage_score,"
													    		  		+ "max_score,"
													    		  		+ "status,"
													    		  		+ "number_of_updates,"
													    		  		+ "request_time_stamp,"
													    		  		+ "response_time_stamp,"
													    		  		+ "creation_date,"
													    		  		+ "last_modified_date,"
													    		  		+ "option_label,"
													    		  		+ "entity_type,"
													    		  		+ "page_id,"
													    		  		+ "entity_id )"
													    		  		+ "VALUES ('"+templateid+"','"+toolid+"','"+projectid+"',"
													    		  				+ "'"+id+"','"+team_id+"','"+role+"','"+QnID+"',"
													    		  				+ "'"+sd_id+"','"+sd_id+"','"+response_type+"','"
													    		  				+ ""+response_metric+"','"+labelid+"',"
													    		  			    + "'"+score+"','"+total_percentage_score+"',"
													    		  			 	+ "'"+max_score+"','"+status+"','"+number_of_updates+"',"
													    		  			 	+ "'"+request_time_stamp+"','"+response_time_stamp+"','"+creation_date+"',"
													    		  			 	+ "'"+last_modified_date+"','"+labelname+"',"
													    		  			 	+ "'"+entity_type+"','"+pageid+"','"+QnID+"')";				    		  
													    		  
													        System.out.println("INSERTING INTO tabQuestionnaireResponses--->"+sql);
													        int result=stmtObj_insert_resp_record.executeUpdate(sql);
													      if(result == 1){
															  System.out.println("Record Inserted successfully.");
															  save_page_response="Record Inserted successfully.";
													      }else
													      {
															  System.out.println("Record Inserted successfully.");
															  save_page_response="Record Not Inserted successfully.";
													      }
													      
  														} catch (Exception e) {
  															e.printStackTrace();
															// TODO: handle exception
														}finally {
															try {
																if(stmtObj_insert_resp_record !=null){
																	stmtObj_insert_resp_record.close();
																}
																if(connObj5 !=null){
																	connObj5.close();
																}
															} catch (Exception e2) {
																
																e2.printStackTrace();
																// TODO: handle exception
															}
														}
													}
													
												} catch (Exception e) {
													// TODO: handle exception
												}finally {
													try {
														if(connObj3 !=null){
															connObj3.close();
														}
														if(stmtObj_resp_record !=null){
															stmtObj_resp_record.close();
														}
														if(rsObj_resp_record !=null){
															rsObj_resp_record.close();
														}
													} catch (Exception e2) {
														// TODO: handle exception
													}
												}
													
													
												
												
												} catch (Exception e) {
													// TODO: handle exception
													e.printStackTrace();
												}finally {
													try {
														if(connObj2 !=null){
															connObj2.close();
														} 
														if(stmtObj_max_score !=null){
															stmtObj_max_score.close();
														}
														if(rsObj_max_score !=null){
															rsObj_max_score.close();
														}
													} catch (Exception e2) {
														e2.printStackTrace();
														// TODO: handle exception
													}
												}				
											
										} catch (Exception e) {
											e.printStackTrace();
											
											// TODO: handle exception
										}finally {
											try {
												if(stmtObj !=null){
													stmtObj.close();
												}
												if(rsObj !=null){
													rsObj.close();
												}
												if(connObj !=null){
													connObj.close();
												}
											} catch (Exception e2) {
												// TODO: handle exception
											}
										}
										/******** First Try Of connection ***********/

										
									}
									
									
								}////////////////
								}

							}
							
						}//sdlist_array for loop
					

					}
				

				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*************/
		return save_page_response;

	}
/**********************************************getSavePage************************************************/

/**********************************************submitQuestionnaireResponse************************************************/
	
	@SuppressWarnings("unchecked")
	public String submitQuestionnaireResponse(String projectid, String toolid, String templateid, String uid) {
		String submit_response = null;
		String submit_response_json = null;
        JSONObject response=new JSONObject();
		System.out.println("In side submitQuestionnaireResponse......................");
		System.out.println("In side getPageDetails projectid......................" + projectid);
		System.out.println("In side getPageDetails toolid......................" + toolid);
		System.out.println("In side getPageDetails templateid......................" + templateid);
		System.out.println("In side getPageDetails pageid......................" + uid);
		int id=0;
		try {
			connObj = getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			stmtObj = connObj.createStatement();
			String sql = "select id from tabUser where user_id='"+uid+"' ";
			//and question_id='Q-100'
			System.out.println("sql-------------->" + sql);

			rsObj = stmtObj.executeQuery(sql);
			System.out.println("RS_OBJ-->" + rsObj);
			while (rsObj.next()) {
				id=rsObj.getInt("id");
				
			}
			System.out.println("user_id_-------------"+id);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		//System.exit(0);
		 try {
			System.out.println("Connection Got....!");
			stmtObj=connObj.createStatement();
			String submit_questionnaire_resp="UPDATE tabQuestionnaireResponses set status='submit' WHERE template_id='"+templateid+"' "
					+ "AND tool_id='"+toolid+"' AND project_id ='"+projectid+"' AND user_id='"+id+"'";
		     System.out.println(submit_questionnaire_resp);
			  int result = stmtObj.executeUpdate(submit_questionnaire_resp);
			  System.out.println(result);
              if(result ==1){
            	  //submit_response="Record Submitted Sucessfully.";
				  JSONArray response_array=new JSONArray();
				  JSONObject response_json=new JSONObject();
				  response_json.put("ReqType", "SubmitQuestionnaire");
				  response_json.put("Status", "1");
				  response_json.put("ErrCode", "0");
				  response_json.put("Description", "Record Submitted Sucessfully.");
				  response_array.put(response_json);
				  response.put("Response", response_array);
				  submit_response=response.toString();
              }else
              {
            	  //submit_response="Record Not Submitted Sucessfully.";
				  JSONArray response_array=new JSONArray();
				  JSONObject response_json=new JSONObject();
				  response_json.put("ReqType", "SubmitQuestionnaire");
				  response_json.put("Status", "-1");
				  response_json.put("ErrCode", "-1");
				  response_json.put("Description", "Record Not Submitted Sucessfully.");
				  response_array.put(response_json);
				  response.put("Response", response_array);
				  submit_response=response.toString();
              }
		 
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(stmtObj != null){
					stmtObj.close();
				}
				if(connObj != null){
					connObj.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		 
		
		
		
		
		return submit_response;
	}
	public static void main(String args[]) {
		DBUtility dbUtility = new DBUtility();
		String final_response=null;

//		String projectid = "PR-001";
//		String toolid = "SST-001";
//		String templateid = "TMP-001";
//		String pageid = "PG-001";
//		String uid = "Rekhas";

		
		String projectid = "PR-001";
		String toolid = "SSTBR-001";
		String templateid = "TMP-001";
		String pageid = "PG-024";
		String uid = "rekhas";
	    final_response = dbUtility.getPageDetails(projectid, toolid, templateid, pageid);
		// final_responsea = dbUtility.getQuestionnaireDetails(toolid, projectid, pageid);
		// final_response = dbUtility.submitQuestionnaireResponse(projectid, toolid, templateid, uid);


//      String jsons="{\r\n\t\"keys\": {\r\n\t\t\"uid\": \"Rekhas\",\r\n\t\t\"toolid\": \"SST-001\",\r\n\t\t\"pid\": \"PR-001\",\r\n\t\t\"templateid\": \"TMP-001\",\r\n\t\t\"pageid\": \"PG-001\",\r\n\t\t\"no_of_questions\": 3\r\n\t},\r\n\t\"PageQuestions\": [{\r\n\t\t\"pd_name\": \"Robust\",\r\n\t\t\"sdlist\": [{\r\n\t\t\t\"qlist\": [{\r\n\t\t\t\t\"Type\": \"Multi-choice\",\r\n\t\t\t\t\"QnNo\": 1,\r\n\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement(1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\"selected\": true\r\n\t\t\t\t}, {\r\n\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\"selected\": false\r\n\t\t\t\t}, {\r\n\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\"selected\": false\r\n\t\t\t\t}, {\r\n\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\"selected\": false\r\n\t\t\t\t}, {\r\n\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Do not know\",\r\n\t\t\t\t\t\"selected\": false\r\n\t\t\t\t}],\r\n\t\t\t\t\"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\r\n\t\t\t\t\"QnID\": \"Q-001\"\r\n\t\t\t}, {\r\n\t\t\t\t\"Type\": \"Multi-choice\",\r\n\t\t\t\t\"QnNo\": 2,\r\n\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\"selected\": false\r\n\t\t\t\t}, {\r\n\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\"selected\": true\r\n\t\t\t\t}, {\r\n\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\"selected\": false\r\n\t\t\t\t}, {\r\n\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\"selected\": false\r\n\t\t\t\t}, {\r\n\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Do not know\",\r\n\t\t\t\t\t\"selected\": false\r\n\t\t\t\t}],\r\n\t\t\t\t\"Qn\": \"We have all of the external competitive strengths (eg technology, brand, distribution, scale, access) we need to ensure we can win with our strategy\",\r\n\t\t\t\t\"QnID\": \"Q-002\"\r\n\t\t\t}, {\r\n\t\t\t\t\"Type\": \"Multi-choice\",\r\n\t\t\t\t\"QnNo\": 3,\r\n\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\"selected\": false\r\n\t\t\t\t}, {\r\n\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\"selected\": false\r\n\t\t\t\t}, {\r\n\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\"selected\": true\r\n\t\t\t\t}, {\r\n\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\"selected\": false\r\n\t\t\t\t}, {\r\n\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\"metric\": \"Degree of agreement (1-4)\",\r\n\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\"labelname\": \"Do not know\",\r\n\t\t\t\t\t\"selected\": false\r\n\t\t\t\t}],\r\n\t\t\t\t\"Qn\": \"We have explicitly considered current or potential  non-traditional competitors\",\r\n\t\t\t\t\"QnID\": \"Q-003\"\r\n\t\t\t}],\r\n\t\t\t\"sd_name\": \"Looking Outwards\",\r\n\t\t\t\"sd_id\": 5\r\n\t\t}],\r\n\t\t\"pd_id\": 5\r\n\t}]\r\n}";
//	    String jsons="{\r\n\t\"keys\": {\r\n\t\t\"uid\": \"Rekhas\",\r\n\t\t\"toolid\": \"SST-001\",\r\n\t\t\"pid\": \"PR-001\",\r\n\t\t\"templateid\": \"TMP-001\",\r\n\t\t\"pageid\": \"PG-001\",\r\n\t\t\"no_of_questions\": 3\r\n\t},\r\n\t\"PageQuestions\": [{\r\n\t\t\"pd_name\": \"Robust\",\r\n\t\t\"sdlist\": [{\r\n\t\t\t\"qlist\": [{\r\n\t\t\t\t\"Type\": \"Multi-choice\",\r\n\t\t\t\t\"QnNo\": 1,\r\n\t\t\t\t\"metrics\": [{\r\n\t\t\t\t\t\"metric\": \"RM-001\",\r\n\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\"type\": \"Button\",\r\n\t\t\t\t\t\t\"labelname\": \"Do not know\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}]\r\n\t\t\t\t}],\r\n\t\t\t\t\"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\r\n\t\t\t\t\"QnID\": \"Q-001\"\r\n\t\t\t}, {\r\n\t\t\t\t\"Type\": \"Multi-choice\",\r\n\t\t\t\t\"QnNo\": 2,\r\n\t\t\t\t\"metrics\": [{\r\n\t\t\t\t\t\"metric\": \"RM-001\",\r\n\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\"type\": \"Button\",\r\n\t\t\t\t\t\t\"labelname\": \"Do not know\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}]\r\n\t\t\t\t}],\r\n\t\t\t\t\"Qn\": \"We have all of the external competitive strengths (eg technology, brand, distribution, scale, access) we need to ensure we can win with our strategy\",\r\n\t\t\t\t\"QnID\": \"Q-002\"\r\n\t\t\t}, {\r\n\t\t\t\t\"Type\": \"Multi-choice\",\r\n\t\t\t\t\"QnNo\": 3,\r\n\t\t\t\t\"metrics\": [{\r\n\t\t\t\t\t\"metric\": \"RM-001\",\r\n\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\"type\": \"Button\",\r\n\t\t\t\t\t\t\"labelname\": \"Do not know\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}]\r\n\t\t\t\t}],\r\n\t\t\t\t\"Qn\": \"We have explicitly considered current or potential  non-traditional competitors\",\r\n\t\t\t\t\"QnID\": \"Q-003\"\r\n\t\t\t}],\r\n\t\t\t\"sd_name\": \"Looking Outwards\",\r\n\t\t\t\"sd_id\": 5\r\n\t\t}],\r\n\t\t\"pd_id\": 5\r\n\t}]\r\n}";
//      String jsons="{\r\n\t\"keys\": {\r\n\t\t\"uid\": \"rekhas\",\r\n\t\t\"toolid\": \"SST-001\",\r\n\t\t\"pid\": \"PR-001\",\r\n\t\t\"templateid\": \"TMP-001\",\r\n\t\t\"pageid\": \"PG-001\",\r\n\t\t\"no_of_questions\": 3\r\n\t},\r\n\t\"PageQuestions\": [{\r\n\t\t\"pd_name\": \"Robust\",\r\n\t\t\"sdlist\": [{\r\n\t\t\t\"qlist\": [{\r\n\t\t\t\t\"Type\": \"Multi-choice\",\r\n\t\t\t\t\"QnNo\": 1,\r\n\t\t\t\t\"metrics\": [{\r\n\t\t\t\t\t\"metric\": \"Degree of agreement\\r\",\r\n\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\"type\": \"Button\",\r\n\t\t\t\t\t\t\"labelname\": \"Do not know\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}]\r\n\t\t\t\t}],\r\n\t\t\t\t\"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\r\n\t\t\t\t\"QnID\": \"Q-001\"\r\n\t\t\t}, {\r\n\t\t\t\t\"Type\": \"Multi-choice\",\r\n\t\t\t\t\"QnNo\": 2,\r\n\t\t\t\t\"metrics\": [{\r\n\t\t\t\t\t\"metric\": \"Degree of agreement\\r\",\r\n\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\"type\": \"Button\",\r\n\t\t\t\t\t\t\"labelname\": \"Do not know\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}]\r\n\t\t\t\t}],\r\n\t\t\t\t\"Qn\": \"We have all of the external competitive strengths (eg technology, brand, distribution, scale, access) we need to ensure we can win with our strategy\",\r\n\t\t\t\t\"QnID\": \"Q-002\"\r\n\t\t\t}, {\r\n\t\t\t\t\"Type\": \"Multi-choice\",\r\n\t\t\t\t\"QnNo\": 3,\r\n\t\t\t\t\"metrics\": [{\r\n\t\t\t\t\t\"metric\": \"Degree of agreement\\r\",\r\n\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\"type\": \"RadioButton\",\r\n\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\"type\": \"Button\",\r\n\t\t\t\t\t\t\"labelname\": \"Do not know\",\r\n\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t}]\r\n\t\t\t\t}],\r\n\t\t\t\t\"Qn\": \"We have explicitly considered current or potential  non-traditional competitors\",\r\n\t\t\t\t\"QnID\": \"Q-003\"\r\n\t\t\t}],\r\n\t\t\t\"sd_name\": \"Looking Outwards\",\r\n\t\t\t\"sd_id\": 5\r\n\t\t}],\r\n\t\t\"pd_id\": 1\r\n\t}]\r\n}";   
//		String jsons="{\r\n\t\"keys\": {\r\n\t\t\"uid\": \"rekhas\",\r\n\t\t\"pid\": \"Merit-2018\",\r\n\t\t\"toolid\": \"SST-001\",\r\n\t\t\"templateid\": \"SST-Template\",\r\n\t\t\"pageid\": \"PG-001\"\r\n\t},\r\n\t\"PageQuestions\": [\r\n    {\r\n    \"padmane\":\"Robust\",\r\n    \"sdlist\":[\r\n        {\r\n            \"sdname\":\"Looking Outwards\",\r\n             \"qlist\":[\r\n                 {\"QnNo\": \"1\",\"QnID\": \"Q-001\", \"QnCOntext\": \"QnCOntext\", \"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\"Type\":\"Multi_choice\",\r\n                   \"metrics\": [{\r\n    \"metric\":\"Impact\",\r\n    \"Optlbl\":[{\"labelname\":\"Strongly Agree\",\"labelid\":\"1\",\"selected\":false,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Agree\",\"labelid\":\"2\",\"selected\":true,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Disagree\",\"labelid\":\"3\",\"selected\":false,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Strongly Disagree\",\"labelid\":\"4\",\"selected\":false,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Don't Know\",\"labelid\":\"5\",\"selected\":false,\"metric\":\"Impact\"}]\r\n},\r\n{\r\n    \"metric\":\"Probability\",\r\n    \"Optlbl\":[{\"labelname\":\"Strongly Agree\",\"labelid\":\"1\",\"selected\":false,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Agree\",\"labelid\":\"2\",\"selected\":true,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Disagree\",\"labelid\":\"3\",\"selected\":false,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Strongly Disagree\",\"labelid\":\"4\",\"selected\":false,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Don't Know\",\"labelid\":\"5\",\"selected\":false,\"metric\":\"Probability\"}]\r\n},\r\n{\r\n    \"metric\":\"Level of Confidence\",\r\n    \"Optlbl\":[{\"labelname\":\"Strongly Agree\",\"labelid\":\"1\",\"selected\":false,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Agree\",\"labelid\":\"2\",\"selected\":true,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Disagree\",\"labelid\":\"3\",\"selected\":false,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Strongly Disagree\",\"labelid\":\"4\",\"selected\":false,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Don't Know\",\"labelid\":\"5\",\"selected\":false,\"metric\":\"Level of Confidence\"}]\r\n}],\r\n                             \"UsrOpt\": [\"1\"]}\r\n             ]\r\n        \r\n    },\r\n         {\r\n            \"sdname\":\"Looking inward\",\r\n                         \"qlist\":[\r\n                 {\"QnNo\": \"1\",\"QnID\": \"Q-001\", \"QnCOntext\": \"QnCOntext\", \"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\"Type\":\"Multi_choice\",\r\n                   \"metrics\": [{\r\n    \"metric\":\"Impact\",\r\n    \"Optlbl\":[{\"labelname\":\"Strongly Agree\",\"labelid\":\"1\",\"selected\":false,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Agree\",\"labelid\":\"2\",\"selected\":true,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Disagree\",\"labelid\":\"3\",\"selected\":false,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Strongly Disagree\",\"labelid\":\"4\",\"selected\":false,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Don't Know\",\"labelid\":\"5\",\"selected\":false,\"metric\":\"Impact\"}]\r\n},\r\n{\r\n    \"metric\":\"Probability\",\r\n    \"Optlbl\":[{\"labelname\":\"Strongly Agree\",\"labelid\":\"1\",\"selected\":false,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Agree\",\"labelid\":\"2\",\"selected\":true,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Disagree\",\"labelid\":\"3\",\"selected\":false,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Strongly Disagree\",\"labelid\":\"4\",\"selected\":false,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Don't Know\",\"labelid\":\"5\",\"selected\":false,\"metric\":\"Probability\"}]\r\n},\r\n{\r\n    \"metric\":\"Level of Confidence\",\r\n    \"Optlbl\":[{\"labelname\":\"Strongly Agree\",\"labelid\":\"1\",\"selected\":false,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Agree\",\"labelid\":\"2\",\"selected\":true,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Disagree\",\"labelid\":\"3\",\"selected\":false,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Strongly Disagree\",\"labelid\":\"4\",\"selected\":false,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Don't Know\",\"labelid\":\"5\",\"selected\":false,\"metric\":\"Level of Confidence\"}]\r\n}],\r\n                             \"UsrOpt\": [\"1\"]},\r\n\t\t\t\t\t\t\t{\"QnNo\": \"2\",\"QnID\": \"Q-003\", \"QnCOntext\": \"QnCOntext\", \"Qn\": \"We have explicitly considered current or potential  non-traditional competitors\",\"Type\":\"Multi_choice\",\r\n                            \"metrics\": [{\r\n    \"metric\":\"Impact\",\r\n    \"Optlbl\":[{\"labelname\":\"Strongly Agree\",\"labelid\":\"1\",\"selected\":false,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Agree\",\"labelid\":\"2\",\"selected\":true,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Disagree\",\"labelid\":\"3\",\"selected\":false,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Strongly Disagree\",\"labelid\":\"4\",\"selected\":false,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Don't Know\",\"labelid\":\"5\",\"selected\":false,\"metric\":\"Impact\"}]\r\n},\r\n{\r\n    \"metric\":\"Probability\",\r\n    \"Optlbl\":[{\"labelname\":\"Strongly Agree\",\"labelid\":\"1\",\"selected\":false,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Agree\",\"labelid\":\"2\",\"selected\":true,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Disagree\",\"labelid\":\"3\",\"selected\":false,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Strongly Disagree\",\"labelid\":\"4\",\"selected\":false,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Don't Know\",\"labelid\":\"5\",\"selected\":false,\"metric\":\"Probability\"}]\r\n},\r\n{\r\n    \"metric\":\"Level of Confidence\",\r\n    \"Optlbl\":[{\"labelname\":\"Strongly Agree\",\"labelid\":\"1\",\"selected\":false,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Agree\",\"labelid\":\"2\",\"selected\":true,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Disagree\",\"labelid\":\"3\",\"selected\":false,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Strongly Disagree\",\"labelid\":\"4\",\"selected\":false,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Don't Know\",\"labelid\":\"5\",\"selected\":false,\"metric\":\"Level of Confidence\"}]\r\n}],\r\n                             \"UsrOpt\": [\"1\",\"2\"]\r\n                            }\r\n             ]\r\n        \r\n    }\r\n        ]\r\n    },\r\n     {\r\n    \"padmane\":\"Resilient\",\r\n    \"sdlist\":[\r\n        {\r\n            \"sdname\":\"Looking Outwards\",\r\n                         \"qlist\":[\r\n                 {\"QnNo\": \"1\",\"QnID\": \"Q-001\", \"QnCOntext\": \"QnCOntext\", \"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\"Type\":\"Multi_choice\",\r\n                   \"metrics\": [{\r\n    \"metric\":\"Impact\",\r\n    \"Optlbl\":[{\"labelname\":\"Strongly Agree\",\"labelid\":\"1\",\"selected\":false,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Agree\",\"labelid\":\"2\",\"selected\":true,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Disagree\",\"labelid\":\"3\",\"selected\":false,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Strongly Disagree\",\"labelid\":\"4\",\"selected\":false,\"metric\":\"Impact\"},\r\n                                        {\"labelname\":\"Don't Know\",\"labelid\":\"5\",\"selected\":false,\"metric\":\"Impact\"}]\r\n},\r\n{\r\n    \"metric\":\"Probability\",\r\n    \"Optlbl\":[{\"labelname\":\"Strongly Agree\",\"labelid\":\"1\",\"selected\":false,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Agree\",\"labelid\":\"2\",\"selected\":true,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Disagree\",\"labelid\":\"3\",\"selected\":false,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Strongly Disagree\",\"labelid\":\"4\",\"selected\":false,\"metric\":\"Probability\"},\r\n                                        {\"labelname\":\"Don't Know\",\"labelid\":\"5\",\"selected\":false,\"metric\":\"Probability\"}]\r\n},\r\n{\r\n    \"metric\":\"Level of Confidence\",\r\n    \"Optlbl\":[{\"labelname\":\"Strongly Agree\",\"labelid\":\"1\",\"selected\":false,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Agree\",\"labelid\":\"2\",\"selected\":true,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Disagree\",\"labelid\":\"3\",\"selected\":false,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Strongly Disagree\",\"labelid\":\"4\",\"selected\":false,\"metric\":\"Level of Confidence\"},\r\n                                        {\"labelname\":\"Don't Know\",\"labelid\":\"5\",\"selected\":false,\"metric\":\"Level of Confidence\"}]\r\n}],\r\n                             \"UsrOpt\": [\"1\"]}\r\n             ]\r\n        \r\n    }\r\n        ]\r\n    }\r\n]\r\n}";
		/*JSONObject get_savepage_request;
		try {
			get_savepage_request = new JSONObject(jsons.toString());
			final_response = dbUtility.getSavePage(get_savepage_request);
			//System.out.println("****************" + final_response);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//System.out.println("------------>" + final_response.length());
		System.out.println("-------------->" + final_response);


	}

}