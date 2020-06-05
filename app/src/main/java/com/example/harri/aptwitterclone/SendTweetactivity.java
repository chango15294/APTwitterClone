package com.example.harri.aptwitterclone;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.HashMap;

public class SendTweetactivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTweet;

    private ListView viewTweetsListView;
    private Button btnViewTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_tweetactivity);

        edtTweet = findViewById(R.id.edtSendTweet);

        viewTweetsListView = findViewById(R.id.viewTweetsListView);
        btnViewTweets = findViewById(R.id.btnViewTweets);
        btnViewTweets.setOnClickListener(this);
    }

    public void sendTweet(View view)
    {
        ParseObject parseObject = new ParseObject("MyTweet");
        parseObject.put("tweet",edtTweet.getText().toString());
        parseObject.put("user",ParseUser.getCurrentUser().getUsername());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        parseObject.saveInBackground(new SaveCallback()
        {
            @Override
            public void done(ParseException e)
            {
                if(e == null)
                {
                    FancyToast.makeText(SendTweetactivity.this,
                            edtTweet.getText().toString() + "tweet de " +
                                    ParseUser.getCurrentUser().getUsername() + " ha sido guardado...",
                            Toast.LENGTH_LONG, FancyToast.SUCCESS,true).show();

                }
                else
                {
                    FancyToast.makeText(SendTweetactivity.this,
                            e.getMessage(),
                            Toast.LENGTH_LONG, FancyToast.ERROR,true).show();
                }

                progressDialog.dismiss();

            }
        });
    }

    @Override
    public void onClick(View view)
    {
        final ArrayList<HashMap<String, String>> tweetList = new ArrayList<>();

    }
}
