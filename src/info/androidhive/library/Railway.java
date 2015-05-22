package info.androidhive.library;

import info.androidhive.listviewfeed.app.AppController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;

import android.content.Context;
import android.util.Log;

public class Railway {
	
	private String url="http://api.railwayapi.com/";
	private String INRapi="20238";
	JSONObject jObj;
//	private String forFare="http://api.railwayapi.com/fare/train/12555/source/gkp/dest/ndls/age/18/quota/PT/doj/23-11-2014/apikey/20238/";
	private String forFare="fare/train/";
	private String trailArrival="http://api.railwayapi.com/arrivals/station/gkp/hours/2/apikey/20238/";
	private String stationSuggest="http://api.railwayapi.com/suggest_station/name/mum/apikey/20238/";
	private String trainSuggest="http://api.railwayapi.com/suggest_train/trains/123/apikey/20238/";
	private String liveTrain="http://api.railwayapi.com/live/train/11093/doj/20141125/apikey/20238/";
	private String pnrStatus="http://api.railwayapi.com/pnr_status/pnr/1234567890/apikey/20238/";
	private String seatAvailibity="http://api.railwayapi.com/check_seat/train/12001/source/BPL/dest/NDLS/date/14-10-2014/class/CC/quota/GN/apikey/20238/";
	private String trainRoute="http://api.railwayapi.com/route/train/12555/apikey/20238/";
	private String trainBtwnStation="http://api.railwayapi.com/between/source/lko/dest/anvt/apikey/20238/";
	private String trainName="http://api.railwayapi.com/name_number/train/bhopal/apikey/20238/";
	private String stationName="http://api.railwayapi.com/name_to_code/station/luckn/apikey/20238/";
	private String stationCode="http://api.railwayapi.com/code_to_name/code/gkp/apikey/20238/";
	
	private String getUrl(String api){
		
		return "";
	}
	
	public JSONObject getFare(String train,String source,String Destination,int age,Date dt){
		SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
		url+=forFare+train+"/source/"+source+"/dest/"+Destination+"/age/"+Integer.toString(age)+"/quota/PT/doj/"+sdf.format(dt)+"/apikey/"+INRapi;
		customRequest jsObjRequest = new customRequest(Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
            	Log.d("response", response.toString());
            	jObj=response;
            	//parseJsonFeed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError response) {
            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);
		/**/
		return jObj;
	}
}
