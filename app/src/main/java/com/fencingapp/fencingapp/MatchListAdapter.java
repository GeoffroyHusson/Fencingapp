package com.fencingapp.fencingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by geoff on 19-08-17.
 */

public class MatchListAdapter extends ArrayAdapter<MatchData> {
    public MatchListAdapter(Context context, List<MatchData> matchDatas){
        super(context,0,matchDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_match,parent, false);
        }

        MatchViewHolder viewHolder = (MatchViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MatchViewHolder();
            viewHolder.own_avatar = (ImageView) convertView.findViewById(R.id.own_avatar);
            viewHolder.own_score = (TextView) convertView.findViewById(R.id.own_score);
            viewHolder.opponent_score = (TextView) convertView.findViewById(R.id.opponent_score);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        MatchData match = getItem(position);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        database.getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    System.out.println(snapshot.getValue(UserInformations.class).getAvatar_url());
////                    System.out.println(avatar);
//                    Picasso.with(getContext()).load(avatar).into(viewHolder.own_avatar);
//                    //Do your operations here
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        System.out.println(match.getUser(match.ownUid).toString());
//        Picasso.with(this.getContext()).load(match.getUserAvatar(match.ownUid)).into(viewHolder.own_avatar);
        viewHolder.own_score.setText(match.getOwnScore());
        viewHolder.opponent_score.setText(match.getOpponentScore());

        return convertView;
    }

    private class MatchViewHolder{
        public ImageView own_avatar;
        public TextView own_score;
        public TextView opponent_score;

    }
}
