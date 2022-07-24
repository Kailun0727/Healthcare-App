package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class commentPage extends AppCompatActivity {

    DBHandler dbHandler;

    TextView tvTip;
    TextView tvDate;
    EditText etComment;
    ImageButton btnComment;
    RecyclerView rvComment;
    CommentAdapter commentAdapter;

    ArrayList<String> commentList;

    String tipId, tip, date, user_id, username;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_page);

        dbHandler = new DBHandler(this);

        Intent i = getIntent();
        tipId = i.getStringExtra("tip_id");
        tip = i.getStringExtra("tip");
        date = i.getStringExtra("date");
        user_id = i.getStringExtra("user_id");
        username = i.getStringExtra("username");
        points = i.getIntExtra("points", 0);

        etComment = findViewById(R.id.et_comment);
        btnComment = findViewById(R.id.commentButton);

        tvTip = findViewById(R.id.t_tip);
        tvTip.setText(tip);

        tvDate = findViewById(R.id.t_date);
        tvDate.setText(date);

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etComment.getText().toString().matches("")){
                    dbHandler.addComment(user_id, tipId, etComment.getText().toString());
                    dbHandler.updateUserPoint(user_id, points + 1);
                    Toast.makeText(commentPage.this, "Comment save successful, point +1", Toast.LENGTH_SHORT).show();
                    commentPage.this.onBackPressed();
                } else {
                    Toast.makeText(commentPage.this, "Comment field are empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        storeDataInArray();

        rvComment = findViewById(R.id.rv_comment);
        commentAdapter = new CommentAdapter(this);
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.setAdapter(commentAdapter);
        commentAdapter.updateCommentList(commentList);
    }

    public void storeDataInArray(){
        Cursor c = dbHandler.readComments(user_id, tipId);

        if(c.getCount() == 0){
            Toast.makeText(this,"No comment.",Toast.LENGTH_SHORT).show();
        } else {
            commentList = new ArrayList<>();
            c.moveToFirst();
            do{
                commentList.add(c.getString(c.getColumnIndex("cm_text")));
            }while(c.moveToNext());
        }
    }
}