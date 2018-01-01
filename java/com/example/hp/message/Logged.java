package com.example.hp.message;


import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.message.Adapter.CustomAdapter;
import com.example.hp.message.Helper.HttpDataHandler;
import com.example.hp.message.Models.ChatModel;
import com.example.hp.message.Models.SimsimiModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Logged extends AppCompatActivity {
    TextView textView;
    ListView listView;
    EditText editText;
    String userName;
    List<ChatModel> list_chat = new ArrayList<>();
    FloatingActionButton btn_send_message;
    Bundle ob= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);
        textView = findViewById(R.id.textView4);
        listView = findViewById(R.id.list_of_messages);
        btn_send_message = findViewById(R.id.fab);
        editText = findViewById(R.id.editText4);
        ob = new Bundle();
            userName = ob.getBundle("Username").toString();
        textView.setText(userName);

        btn_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                ChatModel model = new  ChatModel(text,true); // User send message
                list_chat.add(model);
                new SimsimiAPI().execute(list_chat);

                //recieve user message
                editText.setText("'");
            }
        });

    }
    private class SimsimiAPI extends AsyncTask<List<ChatModel>,Void,String> {
        String stream = null;
        List<ChatModel> models;
        String text = editText.getText().toString();
        @Override
        protected String doInBackground(List<ChatModel>[] lists) {
            String url = String.format("http://sandbox.api.simsimi.com/request.p?key=%s&lc=en&ft=1.0&text=%s" , getString(R.string.simSimi_api));
            models = lists[0];
            HttpDataHandler httpDataHandler = new HttpDataHandler();
            stream = httpDataHandler.GetHTTPData(url);
            return stream;
        }


        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            SimsimiModel response = gson.fromJson(s,SimsimiModel.class);

            ChatModel chatModel = new ChatModel(response.getResponse(),false); //get response from simsimi
            models.add(chatModel);
            CustomAdapter adapter = new CustomAdapter(models,getApplicationContext());
            listView.setAdapter(adapter);
        }
    }

}


