package info.androidhive.railway;

import android.app.Activity;
import android.os.Bundle;

public class railwayApi extends Activity {

	private String forFare="http://api.railwayapi.com/fare/train/12555/source/gkp/dest/ndls/age/18/quota/PT/doj/23-11-2014/apikey/20238/";
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
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}	
}
