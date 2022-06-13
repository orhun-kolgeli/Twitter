package com.codepath.apps.restclienttemplate;


import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {
    Context context;
    List<Tweet> tweets;
    Activity activity;

    public static final String TAG = "TweetsAdapter";
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;
    private final int REQUEST_CODE = 20;

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> newTweets) {
        tweets.addAll(newTweets);
        notifyDataSetChanged();
    }

    // Pass in context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
        this.activity = (Activity) context;

    }

    // For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // Bind values based on position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the tweet
        Tweet tweet = tweets.get(position);
        // Bind tweet with viewHolder
        try {
            holder.bind(tweet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Define viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBody;
        TextView tvUserName;
        TextView tvScreenName;
        ImageView ivProfileImage;
        ImageView ivEmbeddedImage;
        ImageButton ibReply;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvBody = itemView.findViewById(R.id.tvBody);
            ivEmbeddedImage = itemView.findViewById(R.id.embedded_image);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            ibReply = (ImageButton) itemView.findViewById(R.id.ibReply);
        }

        public void bind(Tweet tweet) throws ParseException {
            String relativeTime = getRelativeTimeAgo(tweet.createdAt);
            // Bind tweet body, username
            tvBody.setText(tweet.body);
            tvScreenName.setText(String.format("@%s · %s", tweet.user.screenName, relativeTime));
            tvUserName.setText(tweet.user.name);
            ibReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ComposeActivity.class);
                    intent.putExtra("screenName", "@" + tweet.user.screenName + " ");
                    activity.startActivityForResult(intent, REQUEST_CODE);

                }
            });

            // Bind profile picture
            Glide.with(context)
                    .load(tweet.user.profileImageUrl)
                    .into(ivProfileImage);

            if (tweet.mediaurl.isEmpty()) {
                ivEmbeddedImage.setVisibility(View.GONE);
            } else {
                ivEmbeddedImage.setVisibility(View.VISIBLE);
                // Bind the embedded image
                Glide.with(context)
                        .load(tweet.mediaurl)
                        .circleCrop()
                        .transform(new RoundedCorners(60))
                        .into(ivEmbeddedImage);
            }
        }
    }

    public String getRelativeTimeAgo(String rawJsonDate) throws ParseException {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        try {
            long time = sf.parse(rawJsonDate).getTime();
            long now = System.currentTimeMillis();

            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                return "just now";
            } else if (diff < 2 * MINUTE_MILLIS) {
                return "a minute ago";
            } else if (diff < 50 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + "m";
            } else if (diff < 90 * MINUTE_MILLIS) {
                return "an hour ago";
            } else if (diff < 24 * HOUR_MILLIS) {
                return diff / HOUR_MILLIS + "h";
            } else if (diff < 48 * HOUR_MILLIS) {
                return "yesterday";
            } else {
                return diff / DAY_MILLIS + "d";
            }
        } catch (ParseException e) {
            Log.i(TAG, "getRelativeTimeAgo failed");
            e.printStackTrace();
        }

        return "";
    }
}
