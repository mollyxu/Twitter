package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    List<Tweet> tweets;

    // pass in the context and the list of tweets
    public TweetsAdapter(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    // for each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get the data at position
        Tweet tweet = tweets.get(position);
        
        // bind the tween with view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        ImageView ivTweetImage;
        TextView tvCreatedAt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.iv_profile_image);
            tvBody = itemView.findViewById(R.id.tv_body);
            tvScreenName = itemView.findViewById(R.id.tv_screen_name);
            ivTweetImage = itemView.findViewById(R.id.iv_tweet_image);
            tvCreatedAt = itemView.findViewById(R.id.tv_time_stamp);

        }

        public void bind(Tweet tweet) {

            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            tvCreatedAt.setText(tweet.getRelativeTimeAgo(tweet.createdAt));
            Glide.with(ivProfileImage.getContext()).load(tweet.user.profileImageUrl).into(ivProfileImage);

            if (tweet.tweetImageUrl != "") {
                Glide.with(ivTweetImage.getContext())
                        .load(tweet.tweetImageUrl)
                        .fitCenter()
                        .into(ivTweetImage);
                ivTweetImage.setVisibility(View.VISIBLE);
            }
            else {
                ivTweetImage.setVisibility(View.GONE);
            }


        }
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }

}
