package com.fencingapp.fencingapp;

import android.provider.Settings;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by geoff on 27-03-17.
 */

public class MatchData {
    public String ownScore,opponentScore;
    public String opponentName;
    public String ownUid;

    public MatchData(){

    }

    public MatchData(String ownUid,String ownScore, String opponentScore, String opponentName) {
        this.ownUid = ownUid;
        this.ownScore = ownScore;
        this.opponentScore = opponentScore;
        this.opponentName = opponentName;
    }

    public Task getUser(String userId) {
        final TaskCompletionSource tcs = new TaskCompletionSource();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("users")
                .child(userId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            tcs.setResult(snapshot.getValue(UserInformations.class).getAvatar_url());

                            //Do your operations here
                        }
                    }

                    public void onCancelled(DatabaseError error) {

                    }
                });
        return tcs.getTask();
    }


    public String getOwnScore() {
        return ownScore;
    }

    public void setOwnScore(String ownScore) {
        this.ownScore = ownScore;
    }

    public String getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(String opponentScore) {
        this.opponentScore = opponentScore;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getOwnUid() {
        return ownUid;
    }

    public void setOwnUid(String ownUid) {
        this.ownUid = ownUid;
    }
}
