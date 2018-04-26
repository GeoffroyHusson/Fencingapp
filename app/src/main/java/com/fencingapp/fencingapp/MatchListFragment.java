package com.fencingapp.fencingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MatchListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<MatchData> matchList;

    private FirebaseDatabase database;
    private DatabaseReference myMatches=null;

    private ListView matchListView;


    public MatchListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchListFragment newInstance(String param1, String param2) {
        MatchListFragment fragment = new MatchListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


//        List <MatchData> matchList = createListMatch(database);
//
//        System.out.println(matchList);

//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                MatchData newMatchData = dataSnapshot.getValue(MatchData.class);
//
//
//                System.out.println("op score: " + newMatchData.opponentScore);
//                System.out.println("op name: " + newMatchData.opponentName);
//                System.out.println("Previous Post ID: " + prevChildKey);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {}
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {}
//        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_match_list, container, false);
        // Inflate the layout for this fragment
        matchListView = (ListView)  view.findViewById(R.id.matchListView);


        database=FirebaseDatabase.getInstance();
        myMatches = database.getReference().child("matches");

//        mAdpter = new FirebaseListAdapter<MatchData>(this,MatchData.class,R.layout.row_match,myMatches){
//
//        }

        matchList = new ArrayList<MatchData>();

        myMatches.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                matchList.clear();
                for(DataSnapshot postSnapShot: snapshot.getChildren()){
                    MatchData matchData = postSnapShot.getValue(MatchData.class);
                    matchList.add(matchData);
                }
                MatchListAdapter adapter = new MatchListAdapter(getActivity(),matchList);
                matchListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

    private List<MatchData> createListMatch(FirebaseDatabase db){
        final List<MatchData> matchList = new ArrayList<MatchData>();
        myMatches = db.getReference().child("matches");

        myMatches.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                matchList.clear();
                for(DataSnapshot postSnapShot: snapshot.getChildren()){
                    MatchData matchData = postSnapShot.getValue(MatchData.class);
                    matchList.add(matchData);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return matchList;
    };

}
