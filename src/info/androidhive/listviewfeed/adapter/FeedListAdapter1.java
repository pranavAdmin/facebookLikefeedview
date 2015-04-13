package info.androidhive.listviewfeed.adapter;

import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import info.androidhive.listviewfeed.R;
//import com.pm.pmv5.R;
import info.androidhive.listviewfeed.FeedImageView;
import info.androidhive.listviewfeed.app.AppController;
import info.androidhive.listviewfeed.data.FeedItem;
/*import com.pm.pmv5.takeover.BoardActivity;
import com.pm.pmv5.takeover.BoardActivity2;
import com.pm.pmv5.takeover.BoardActivity3;
import com.pm.pmv5.takeover.NameActivity;
import com.pm.pmv5.takeover.StartMenuV1;
import com.pm.pmv5.takeover.StartMenuV2;
import com.pm.pmv5.takeover.StartMenuV3;
*/
@SuppressLint("InflateParams")
public class FeedListAdapter1 extends BaseAdapter implements
        View.OnClickListener {

    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItem> feedItems;

    Context context;
    //baZOOKA heyNOW;
    FeedItem item;
    String nueSELECTOR;
    MediaPlayer mp;
    NetworkImageView profilePic;
    FeedImageView feedImageView;
    TextView name;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public FeedListAdapter1(Activity activity, List<FeedItem> feedItems) {
        this.activity = activity;
        this.feedItems = feedItems;

    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        context = AppController.getInstance();

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.feed_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        name = (TextView) convertView.findViewById(R.id.name);
        TextView timestamp = (TextView) convertView
                .findViewById(R.id.timestamp);
        TextView statusMsg = (TextView) convertView
                .findViewById(R.id.txtStatusMsg);
        TextView url = (TextView) convertView.findViewById(R.id.txtUrl);
        profilePic = (NetworkImageView) convertView
                .findViewById(R.id.profilePic);
        feedImageView = (FeedImageView) convertView
                .findViewById(R.id.feedImage1);

        item = feedItems.get(position);

        // name.setText(item.getName());

        final int ok234 = feedItems.indexOf(1);
        int index = feedItems.indexOf("papa"); // index = 2

        // name.setText(Integer.toString(ok234));

        nueSELECTOR = Integer.toString(item.getId());

        name.setText(nueSELECTOR);
        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);

        // Chcek for empty status message
        if (!TextUtils.isEmpty(item.getStatus())) {
            statusMsg.setText(item.getStatus());
            statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            statusMsg.setVisibility(View.GONE);
        }

        // Checking for null feed url
        if (item.getUrl() != null) {
            url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
                    + item.getUrl() + "</a> "));

            // Making url clickable
            url.setMovementMethod(LinkMovementMethod.getInstance());
            url.setVisibility(View.VISIBLE);
        } else {
            // url is null, remove from the view
            url.setVisibility(View.GONE);
        }

        // user profile pic
        profilePic.setImageUrl(item.getProfilePic(), imageLoader);

        // Feed image
        if (item.getImge() != null) {
            feedImageView.setImageUrl(item.getImge(), imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {

            feedImageView.setVisibility(View.GONE);
        }
        profilePic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (item.getId() != 1) {

                    name.setText(item.getName());

                }

                if (item.getId() == 2) {

                    // name.setText(item.getName());

                }

            }
        });

        feedImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                switch (item.getId()) {

                case 1:

                 /*mp = MediaPlayer.create(context, R.raw.cameraflash);
                    mp.setVolume(100, 100);
                    mp.start();

                    Intent myIntent1 = new Intent(context, NameActivity.class);
                    myIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(myIntent1);*/

                    break;

                case 2:

                 /* mp = MediaPlayer.create(context, R.raw.cameraflash);
                    mp.setVolume(100, 100);
                    mp.start();

                    Intent myIntent2 = new Intent(context, StartMenuV1.class);
                    myIntent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(myIntent2);*/

                    break;
                case 3:

                /*  mp = MediaPlayer.create(context, R.raw.cameraflash);
                    mp.setVolume(100, 100);
                    mp.start();

                    Intent myIntent3 = new Intent(context, StartMenuV2.class);
                    myIntent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(myIntent3);*/

                    break;
                }

            }
        });

        return convertView;

    }

    @Override
    public void onClick(View v2) {
        // TODO Auto-generated method stub
        switch (v2.getId()) {
        case R.id.profilePic:

            break;
        case R.id.feedImage1:

            break;

        }
    }

}
