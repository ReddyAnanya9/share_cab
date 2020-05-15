package com.example.sharecab;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class List_bookings extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mfirestorelist;
    private FirestoreRecyclerAdapter adapter;
    String popUpContents[];
    PopupWindow popupWindowFilter;
    Button buttonShowDropDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bookings);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mfirestorelist = findViewById(R.id.firestoreList);
        CollectionReference query = firebaseFirestore.collection("User Bookings");
        FirestoreRecyclerOptions<UserBookings> options = new FirestoreRecyclerOptions.Builder<UserBookings>()
                .setQuery(query,UserBookings.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<UserBookings, userViewHolder>(options) {
            @NonNull
            @Override
            public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_single_layout,parent,false);

                return new userViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull userViewHolder holder, int position, @NonNull UserBookings model) {

                holder.from.setText( "pick up point : " + model.getPickup_pt());
                holder.to.setText("destination : "+   model.getDestination());
                holder.time.setText("time to pick : "+model.getTime());
                holder.lugg.setText("luggage weight : "+model.getLuggage()+"");
                holder.spreq.setText("special req.: "+model.getSpecial_Req());

            }
        };
        // mfirestorelist.setHasFixedSize(true);
        mfirestorelist.setLayoutManager(new LinearLayoutManager(this));
        mfirestorelist.setAdapter(adapter);

        List<String> filter_options = new ArrayList<String>();
        filter_options.add("Gender::1");
        filter_options.add("PickUp Point::2");
        filter_options.add("Destination::3");
        filter_options.add("Time::4");

        // convert to simple array
        popUpContents = new String[filter_options.size()];
        filter_options.toArray(popUpContents);


        // initialize pop up window
        popupWindowFilter = popupWindowFilter();
        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.filter_btn:
                        // show the list view as dropdown
                        popupWindowFilter().showAsDropDown(v, 0, 0);
                        break;
                }
            }
        };
        buttonShowDropDown = (Button) findViewById(R.id.filter_btn);
        buttonShowDropDown.setOnClickListener(handler);
    }
    public PopupWindow popupWindowFilter() {
        final CollectionReference dbref=firebaseFirestore.collection("User Bookings");//notebookref
        // initialize a pop up window type
        PopupWindow popupWindow = new PopupWindow(this);

        // the drop down list is a list view
        ListView listViewFilter = new ListView(this);

        // set our adapter and pass our pop up window contents
        listViewFilter.setAdapter(filterAdapter(popUpContents));

        // set the item click listener
        listViewFilter.setOnItemClickListener(new FilterDropdownOnItemClickListener());

        // some other visual settings
        popupWindow.setFocusable(true);
        popupWindow.setWidth(250);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the list view as pop up window content
        popupWindow.setContentView(listViewFilter);
        listViewFilter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        break;
                    case 1:

                }
            }
        });
        return popupWindow;
    }
    private ArrayAdapter<String> filterAdapter(String fArray[]) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fArray) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // setting the ID and text for every items in the list
                String item = getItem(position);
                String[] itemArr = item.split("::");
                String text = itemArr[0];
                String id = itemArr[1];

                // visual settings for the list item
                TextView listItem = new TextView(List_bookings.this);

                listItem.setText(text);
                listItem.setTag(id);
                listItem.setTextSize(18);
                listItem.setPadding(9, 9, 9, 9);
                listItem.setTextColor(Color.WHITE);

                return listItem;
            }
        };

        return adapter;
    }

    private class userViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView from;
        private TextView to;
        private TextView time;
        private TextView lugg;
        private TextView spreq;
        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            from=itemView.findViewById(R.id.pick);
            to =itemView.findViewById(R.id.dest);
            time = itemView.findViewById(R.id.timing);
            lugg = itemView.findViewById(R.id.lugg);
            spreq = itemView.findViewById(R.id.specreq);
        }


        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(v.getContext(), Display.class);

//start the activity from the view/context
            //v.getContext().startActivity(intent);
            Toast.makeText(List_bookings.this,"Clicked item at "+ getAdapterPosition(),Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    public interface OnListItemClick{
        void onItemClick();

    }

}