package com.fencingapp.fencingapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewMatchFragment extends Fragment {


    private DatabaseReference databaseReference;
    private EditText editTextOwnScore,editTextOpponentScore,editTextOpponentName;
    private Button registerMatch;

    private FirebaseAuth mAuth;

    public NewMatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_match, container, false);
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        if(mAuth == null){
//            finish();
            getActivity().getFragmentManager().popBackStack();
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextOwnScore = (EditText) view.findViewById(R.id.own_score);
        editTextOpponentScore = (EditText) view.findViewById(R.id.opponent_score);
        editTextOpponentName = (EditText) view.findViewById(R.id.opponent_name);

        registerMatch = (Button) view.findViewById(R.id.register_match);



        registerMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMatchResult();
            }
        });
        return view;
    }

    private void saveMatchResult(){
        String ownScore = editTextOwnScore.getText().toString().trim();
        String opponentScore = editTextOpponentScore.getText().toString().trim();
        String opponentName = editTextOpponentName.getText().toString().trim();
        FirebaseUser user = mAuth.getCurrentUser();
        MatchData matchData = new MatchData(user.getUid(),ownScore, opponentScore, opponentName);


        databaseReference.child("matches").push().setValue(matchData);

        Toast.makeText(getActivity(),"Result saved",Toast.LENGTH_LONG).show();
    }


}
