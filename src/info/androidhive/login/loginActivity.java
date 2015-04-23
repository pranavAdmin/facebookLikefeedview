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
				            	 Log.d("Response: ", response.toString());
				                /*try {
				                    Log.d("Response: ", response.toString());
				                } catch (JSONException e) {
				                    // TODO Auto-generated catch block
				                    e.printStackTrace();
				                }*/

				            }
				        }, new Response.ErrorListener() {

				            @Override
				            public void onErrorResponse(VolleyError response) {
				                Log.d("Response: ", response.toString());
				            }
				        });
				        AppController.getInstance().addToRequestQueue(jsObjRequest);
				
				/**********************************/
				
				
				
				
				
				/*JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
						LOGIN_URL, null, new Response.Listener<JSONObject>() {

							@Override
							public void onResponse(JSONObject response) {
								//VolleyLog.d(TAG, "Response: " + response.toString());
								if (response != null) {
									Log.d("response :", response.toString());
									//parseJsonFeed(response);
								}
							}
						}, new Response.ErrorListener() {

							@Override
							public void onErrorResponse(VolleyError error) {
								//VolleyLog.d(TAG, "Error: " + error.getMessage());
							}
						});

				// Adding request to volley request queue
				AppController.getInstance().addToRequestQueue(jsonReq);*/

				/*UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.loginUser(email, password);
				*/
				
				//Log.d("response", json.toString());
				// check for login response
				/*try {
				
					if (json.getString(KEY_SUCCESS) != null) {
						loginErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS); 
						if(Integer.parseInt(res) == 1){
							// user successfully logged in
							// Store user details in SQLite Database
							DatabaseHandler db = new DatabaseHandler(getApplicationContext());
							JSONObject json_user = json.getJSONObject("user");
							
							// Clear all previous data in database
							userFunction.logoutUser(getApplicationContext());
							db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));						
							
							// Launch Dashboard Screen
							Intent dashboard = new Intent(getApplicationContext(),  MainActivity.class);
							
							// Close all views before launching Dashboard
							//dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(dashboard);
							
							// Close Login Screen
							finish();
						}else{
							// Error in login
							loginErrorMsg.setText("Incorrect username/password");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}*/
			}
		});

		/*****************/

		
		/**/
		
		
		// Link to Register Screen
		btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(i);
				finish();
			}
		});
		
	}
	private void parseJsonFeed(JSONObject response) {
		try {
			JSONArray feedArray = response.getJSONArray("feed");

			for (int i = 0; i < feedArray.length(); i++) {
				
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
			}

			// notify data changes to list adapater
			//listAdapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
