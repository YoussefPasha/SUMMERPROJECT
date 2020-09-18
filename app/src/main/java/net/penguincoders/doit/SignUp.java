package net.penguincoders.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import net.penguincoders.doit.Utils.DatabaseHelper;

public class SignUp extends AppCompatActivity {
    ImageView img;
    EditText e1 , e2 , e3 ;
    Button b1;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        img = findViewById(R.id.GoBackIcon);
        e1 = (EditText)findViewById(R.id.email);
        e2 = (EditText)findViewById(R.id.password);
        e3 = (EditText)findViewById(R.id.repassword);
        b1 = (Button)findViewById(R.id.joinus);
        db = new DatabaseHelper(this);
        try{
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s1 = e1.getText().toString();
                    String s2 = e2.getText().toString();
                    String s3 = e3.getText().toString();
                    System.out.println(s1 + " " + s2 + s3 );
                    if(s1.equals("")|| s2.equals("")||s3.equals("")){
                        Toast.makeText(getApplicationContext(), "Fields are empty" , Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(s2.equals(s3)){
                            Boolean chkmail = db.chkmail(s1);
                            if(chkmail==true){
                                Boolean insert = db.insert(s1 , s2);
                                if(insert==true){
                                    Toast.makeText(getApplicationContext() , "Register Success" , Toast.LENGTH_SHORT).show();
                                    Intent ic = new Intent(SignUp.this,LoginScreen.class);
                                    startActivity(ic);
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Email Already exists" , Toast.LENGTH_SHORT).show();
                            }
                        }
                        Toast.makeText(getApplicationContext() , "password do not match" , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    public void gotoNext(View view) {
        Intent dsp = new Intent(SignUp.this,LoginScreen.class);
        startActivity(dsp);
    }

    public void LognIn(View view) {
        Intent ds = new Intent(SignUp.this,LoginScreen.class);
        startActivity(ds);

    }
}