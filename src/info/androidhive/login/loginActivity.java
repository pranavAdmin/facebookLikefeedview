package info.androidhive.login;

import java.util.HashMap;
import java.util.Map;

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

import info.androidhive.library.DatabaseHandler;
import info.androidhive.library.UserFunctions;
import info.androidhive.listviewfeed.MainActivity;
import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.app.AppController;
import info.androidhive.listviewfeed.data.FeedItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import info.androidhive.library.customRequest;
import info.androidhive.register.registerActivity;

public class loginActivity extends Activity {
	
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	
	private static String LOGIN_URL="http://10.0.2.2/all_structure/admin/checkLogin.php";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		inputEmail = (EditText) findViewById(R.id.loginEmail);
		inputPassword = (EditText) findViewById(R.id.loginPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);
		

		// Login button Click Event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				
				/********* Custom code by pranav*/
				//String url = some valid url;
				Map<String, String> params = new HashMap<String, String>();
				params.put("username", email);
				params.put("password", password);

				customRequest jsObjRequest = new customRequest(Method.POST, LOGIN_URL, params, new Response.Listener<JSONObject>() {

		            @Override
		            public void onResponse(JSONObject response) {
		            	parseJsonFeed(response);
		            	// Log.d("Response: ", response.toString());
		            }
		        }, new Response.ErrorListener() {
		            @Override
		            public void onErrorResponse(VolleyError response) {
		                //Log.d("Response: ", response.toString());
		            }
		        });
		        AppController.getInstance().addToRequestQueue(jsObjRequest);
				/**********************************/
			}
		});
		// Link to Register Screen
		btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),registerActivity.class);
				startActivity(i);
				finish();
			}
		});
		
	}
	private void parseJsonFeed(JSONObject response) {
		try {
			JSONArray feedArray = response.getJSONArray("record");
			Log.d("record",feedArray.toString());
			Log.d("success", response.getString("success"));
			if(response.getString("success")=="1"){
				Intent i=new Intent(getApplicationContext(),MainActivity.class);
				startActivity(i);
				finish();
			}
			else {
				String str="You are not authorised";
				loginErrorMsg.setText(str);
				}
			/*for (int i = 0; i < feedArray.length(); i++) {
				
				JSONObject feedObj = (JSONObject) feedArray.get(i);
				FeedItem item = new FeedItem();
				item.setId(feedObj.getInt("id"));
				item.setName(feedObj.getString("name"));

				// Image might be null sometimes
				String image = feedObj.isNull("image") ? null : feedObj.getString("image");
				item.setImge(image);
				item.setStatus(feedObj.getString("status"));
				item.setProfilePic(feedObj.getString("profilePic"));
				item.setTimeStamp(feedObj.getString("timeStamp"));

				// url might be null sometimes
				String feedUrl = feedObj.isNull("url") ? null : feedObj.getString("url");
				item.setUrl(feedUrl);

				//feedItems.add(item);
			}*/

			// notify data changes to list adapater
			//listAdapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
