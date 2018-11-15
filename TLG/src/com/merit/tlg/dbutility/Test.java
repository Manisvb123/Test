package com.merit.tlg.dbutility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


//JSONObject jsonChildObject = (JSONObject)jsonObject.get("LanguageLevels");
//for (Map.Entry in jsonChildOBject.entrySet()) {
//    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//}
public class Test {

	@SuppressWarnings("unchecked")


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		// String JsonString =
		// "{\"request\":{\"uid\":\"userid\",\"pid\":\"PR-001\",\"toolid\":\"SST-001\",\"templateid\":\"TMP-001\",\"pageid\":\"PG-001\"}}";
		// try {
		// JSONObject jsonobj = new JSONObject(JsonString);
		// JSONObject getRequest = jsonobj.getJSONObject("request");
		// String toolid = getRequest.getString("toolid");
		// String projectid = getRequest.getString("pid");
		// String templateid = getRequest.getString("templateid");
		//
		// System.out.println(
		// "\ntoolid---->" + toolid + "\nprojectid--->" + projectid +
		// "\ntemplateid---->" + templateid);
		//
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// JSONArray rootjosnArray = new JSONArray();
		// String json_array = "[\"PG-001\", \"PG-002\", \"PG-003\", \"PG-004\",
		// \"PG-005\", \"PG-006\"]";
		// rootjosnArray.add(json_array);
		//
		// JSONObject jo = new JSONObject();
		// try {
		// jo.put("firstName", "John");
		// jo.put("lastName", "Doe");
		//
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// JSONArray ja = new JSONArray();
		// ja.add(jo);
		//
		// JSONObject mainObj = new JSONObject();
		// try {
		// mainObj.put("employees", ja);
		// System.out.println(mainObj);
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// ArrayList<String> arr = new ArrayList<String>();
		// arr.add("yy");
		// arr.add("ss");
		// System.out.println(arr);
		// @SuppressWarnings("unused")
		// String all = "{'pgid':'PG-001','pgno':'1'}";
		// String pgid = "pgid";
		// @SuppressWarnings("unused")
		// String pgno = "pgid";
		// String page_id = "page_id";
		// String pgno1 = "pgno";
		//
		// String all1 = "{'" + pgid + "':'" + page_id + "','" + pgno1 + "':'" +
		// pgno1 + "'}";
		// System.out.println(all1);
		// JSONObject obj = new JSONObject();
		// String s = "{\"{ \"uid\":\"\"userid\", \"pid\": \"PR-001\",
		// \"toolid\": \"SST-001\", \"templateid\\\": \\\"TMP-001\\\",
		// \\\"pageid\\\": \\\"PG-001\\\"
		// }}\",\"Request-URI\":\"request\",\"Method\":\"{\",\"HTTP-Version\":\":\"}";
		// String JsonString1 =
		// "{\"request\":{\"uid\":\"userid\",\"pid\":\"PR-001\",\"toolid\":\"SST-001\",\"templateid\":\"TMP-001\",\"pageid\":\"PG-001\"}}";
		// System.out.println(JsonString1);
		//
		// System.out.println(s);
		// try {
		// obj.put("name", "foo");
		// System.out.println(obj);
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//

		JSONObject rootjsonObj = new JSONObject();
		JSONObject subjsonObj = new JSONObject();
		JSONObject childjsonObj = new JSONObject();
		JSONArray ja = new JSONArray();

		try {

			childjsonObj.put("title", "Title");
			childjsonObj.put("inst", "Instructions");
			childjsonObj.put("Rmrks", "Remarks");

			subjsonObj.put("userid", "hellow");
			subjsonObj.put("prjid", "hellow");
			subjsonObj.put("toolid", "hellow");
			subjsonObj.put("toolver", "hellow");
			subjsonObj.put("tmplname", "hellow");
			subjsonObj.put("tmplver", "hellow");
			subjsonObj.put("pgno", "hellow");
			subjsonObj.put("pgid", "hellow");
			subjsonObj.put("numqns", "hellow");

			subjsonObj.put("commonInfo", childjsonObj);

			rootjsonObj.put("Page", subjsonObj);
			System.out.println(rootjsonObj);

			String animals = "dog, cat, bear, elephant, giraffe";

			String[] animalsArray = animals.split(",");

			System.out.println(animals);

			// ArrayList<String> array_test=new ArrayList<String>();
			ja.put(animals);

	

			String languages = "Java,JavaScript,C++,Python,Ruby,Scala";

			// splitting String by comma, it will return array
			String[] array = languages.split(",");

			System.out.println(array);

			String strMain = "Strongly Agree-RadioButton,Agree-RadioButton,Disagree-RadioButton,Strongly Disagree-RadioButton,Do not know-Button";
			String[] arrSplit = strMain.split(",");
			for (int i = 0; i < arrSplit.length; i++) {
				System.out.println(arrSplit[i]);
			}

			String str = "1,2,3";
			String[] variables = str.split(",");
			String first = variables[0];
			String second = variables[1];
			String third = variables[2];
			System.out.println(third);

			String value = "Strongly Agree-RadioButton";
			String result = value.replace("-RadioButton", "");
			System.out.println(result);

			// creating an Empty Integer ArrayList
			ArrayList<Integer> arr = new ArrayList<Integer>(4);

			// using add() to initialize values
			// [1, 2, 3, 4]
			arr.add(1);
			arr.add(2);
			arr.add(3);
			arr.add(4);

			if (arr.contains(9)) {
				System.out.println("success");
			} else {
				System.out.println("filed");

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/****************/

		String jsons = "{\r\n\t\"keys\": {\r\n\t\t\"uid\": \"rekhas\",\r\n\t\t\"pid\": \"Merit-2018\",\r\n\t\t\"toolid\": \"SST-001\",\r\n\t\t\"templateid\": \"SST-Template\",\r\n\t\t\"pageid\": \"PG-001\",\r\n\t\t\"submit_status\": \"draft\"\r\n\t},\r\n\t\"PageQuestions\": [{\r\n\t\t\t\"pd-name\": \"Robust\",\r\n\t\t\t\"sdlist\": [{\r\n\t\t\t\t\t\"sd-name\": \"Looking Outwards\",\r\n\t\t\t\t\t\"qlist\": [{\r\n\t\t\t\t\t\t\t\"QnNo\": \"1\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-001\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_choice\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t},\r\n\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\"QnNo\": \"3\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-003\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have explicitly considered current or potential  non-traditional competitors\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_Select\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t]\r\n\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"sd-name\": \"Looking inward\",\r\n\t\t\t\t\t\"qlist\": [{\r\n\t\t\t\t\t\t\t\"QnNo\": \"1\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-001\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_choice\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t},\r\n\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\"QnNo\": \"3\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-003\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have explicitly considered current or potential  non-traditional competitors\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_Select\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t]\r\n\r\n\t\t\t\t}\r\n\t\t\t]\r\n\t\t},\r\n\t\t{\r\n\t\t\t\"pd-name\": \"Resilient\",\r\n\t\t\t\"sdlist\": [{\r\n\t\t\t\t\t\"sd-name\": \"Looking Outwards\",\r\n\t\t\t\t\t\"qlist\": [{\r\n\t\t\t\t\t\t\t\"QnNo\": \"1\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-001\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_choice\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t],\r\n\t\t\t\t\t\t\t\"UsrOpt\": [\"1\"]\r\n\t\t\t\t\t\t},\r\n\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\"QnNo\": \"3\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-003\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have explicitly considered current or potential  non-traditional competitors\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_Select\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t]\r\n\r\n\t\t\t\t},\r\n\t\t\t\t{\r\n\t\t\t\t\t\"sd-name\": \"Looking inward\",\r\n\t\t\t\t\t\"qlist\": [{\r\n\t\t\t\t\t\t\t\"QnNo\": \"1\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-001\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have clearly established that there is sufficient market headroom for our planned growth\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_choice\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t},\r\n\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\"QnNo\": \"3\",\r\n\t\t\t\t\t\t\t\"QnID\": \"Q-003\",\r\n\t\t\t\t\t\t\t\"QnCOntext\": null,\r\n\t\t\t\t\t\t\t\"Qn\": \"We have explicitly considered current or potential  non-traditional competitors\",\r\n\t\t\t\t\t\t\t\"Type\": \"Multi_Select\",\r\n\t\t\t\t\t\t\t\"metric\": \"Degree of Agreement\",\r\n\t\t\t\t\t\t\t\"Optlbl\": [{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"1\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Agree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"2\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"3\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t}, {\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Strongly Disagree\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"4\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": false\r\n\t\t\t\t\t\t\t\t},\r\n\t\t\t\t\t\t\t\t{\r\n\t\t\t\t\t\t\t\t\t\"labelname\": \"Don't Know\",\r\n\t\t\t\t\t\t\t\t\t\"labelid\": \"5\",\r\n\t\t\t\t\t\t\t\t\t\"type\": \"radio\",\r\n\t\t\t\t\t\t\t\t\t\"selected\": true\r\n\t\t\t\t\t\t\t\t}\r\n\t\t\t\t\t\t\t]\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t]\r\n\r\n\t\t\t\t}\r\n\t\t\t]\r\n\t\t}\r\n\t]\r\n}";

		// System.out.println("jsons data---->"+jsons.toString());

		JSONObject get_savepage_request;
		try {
			get_savepage_request = new JSONObject(jsons.toString());
			JSONObject kesy_json_string = get_savepage_request.getJSONObject("keys");
			org.json.JSONArray PageQuestions = get_savepage_request.getJSONArray("PageQuestions");

			System.out.println("keys data---->" + kesy_json_string);
			System.out.println("PageQuestions data---->" + PageQuestions);
			System.out.println();

			for (int i = 0; i < PageQuestions.length(); i++) {
				JSONObject json = PageQuestions.getJSONObject(i);
				Iterator<String> keys = json.keys();
				// System.out.println("json data---->"+json);

				while (keys.hasNext()) {
					String key = keys.next();
					System.out.println("Key :" + key + "  Value :" + json.get(key));
					if (key.equals("sdlist")) {
						org.json.JSONArray sdlist_array = (org.json.JSONArray) json.get(key);
						System.out.println("sdlist_array======" + sdlist_array);

						for (int j = 0; j < sdlist_array.length(); ++j) {
							JSONObject qlist = sdlist_array.getJSONObject(j);
							System.out.println(qlist);
							org.json.JSONArray qlist_arrays = (org.json.JSONArray) qlist.get("qlist");
							System.out.println("qlist_array======" + qlist_arrays);
							
					
							
							for (int k = 0; k < qlist_arrays.length(); ++k) {
								JSONObject qlist_josn = qlist_arrays.getJSONObject(k);
								System.out.println("qlist Json String---"+qlist_josn);
								JSONObject qlist_josn_string = new JSONObject(qlist_josn.toString());
								String type= qlist_josn_string.getString("Type");
								System.out.println("type---"+type);
								String metric= qlist_josn_string.getString("metric");
								System.out.println("metric---"+metric);
								String QnNo= qlist_josn_string.getString("QnNo");
								System.out.println("QnNo---"+QnNo);
								String QnID= qlist_josn_string.getString("QnID");
								System.out.println("QnID---"+QnID);
								String Qn= qlist_josn_string.getString("Qn");
								System.out.println("Qn---"+Qn);
//								String QnCOntext= qlist_josn_string.getString("QnCOntext");
//								System.out.println("QnCOntext---"+QnCOntext);
								org.json.JSONArray Optlbl_array= qlist_josn_string.getJSONArray("Optlbl");
								System.out.println("Optlbl_array---"+Optlbl_array);	
								for (int l = 0; l < Optlbl_array.length(); ++l) {
									JSONObject Optlbl_array_json = Optlbl_array.getJSONObject(l);
									System.out.println("labelid====>"+Optlbl_array_json.getString("labelid"));
									System.out.println("type====>"+Optlbl_array_json.getString("type"));
									System.out.println("labelname====>"+Optlbl_array_json.getString("labelname"));
									System.out.println("selected====>"+Optlbl_array_json.getBoolean("selected"));

								}

							}
							
						}
					

					}
				

				}

			}
			
			
			String s="Don\\'t Know";
			String do_not_know;
			if(s.contains("\\")){
				//System.out.println(s);
				do_not_know=s.replace("\\","").toString();
				System.out.println("Dont NO----> "+do_not_know);
			}
			//System.out.println(s);
			//System.out.println("fffff--"+s.replace("\\","").toString());

//			String ss=s.replace("\\","");
//			ss.replace("\\","");
//			System.out.println(ss.toString());
			
	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*************/

	}

}
