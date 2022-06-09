package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {
    Context context;
    List<Tweet> tweets;

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
        holder.bind(tweet);
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvBody = itemView.findViewById(R.id.tvBody);
            ivEmbeddedImage = itemView.findViewById(R.id.embedded_image);
            tvUserName = itemView.findViewById(R.id.tvUserName);
        }

        public void bind(Tweet tweet) {
            // Bind tweet body, username
            tvBody.setText(tweet.body);
            tvScreenName.setText(String.format("@%s", tweet.user.screenName));
            tvUserName.setText(tweet.user.name);

            // Bind profile picture
            Glide.with(context)
                    .load(tweet.user.profileImageUrl)
                    .into(ivProfileImage);

            // Bind the embedded image
            Glide.with(context)
                    .load(tweet.mediaurl)
                    .circleCrop()
                    .transform(new RoundedCorners(60))
                    .into(ivEmbeddedImage);

        }
    }
}
