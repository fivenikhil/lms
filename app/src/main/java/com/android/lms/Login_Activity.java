package com.android.lms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLData;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login_Activity extends BaseActivity
{

    private EditText email, password;
    private Button btn_login;
    private CoordinatorLayout coordinatorLayout;
    private TextView txt_new;
    private String name_check, password_check;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    Sqlite_Database sqlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_content);

        sqlData= new Sqlite_Database(this);
        sqlData.open();

        coordinatorLayout =(CoordinatorLayout)findViewById(R.id.coordinatorlayout);

        txt_new = (TextView)findViewById(R.id.txt_new);
        txt_new.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        email = (EditText)findViewById(R.id.txt_email);
        email.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));
        password = (EditText)findViewById(R.id.txt_password);
        password.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        saveLoginCheckBox = (CheckBox)findViewById(R.id.check_true);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true)
        {
            email.setText(loginPreferences.getString("username", ""));
            password.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "GOTH720N.TTF"));

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name_check = email.getText().toString().trim();
                password_check = password.getText().toString().trim();

                asyncTask();

                if (TextUtils.isEmpty(name_check)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password_check)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                /*if (email.getText().toString().equals("admin") && password.getText().toString().equals("dicv@123"))
                {
                    Intent i = new Intent(Login_Activity.this, DashBoard_Activity.class);
                    startActivity(i);
                }*/

                if (saveLoginCheckBox.isChecked())
                {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", name_check);
                    loginPrefsEditor.putString("password", password_check);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }

            }


        });


    }


    private void asyncTask()
    {
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... arg0)
            {
                try
                {

                    OkHttpClient client = new OkHttpClient();

                    RequestBody formBody = new FormBody.Builder()
                            .add(ConstantClass.username, name_check)
                            .add(ConstantClass.password, password_check)
                            .build();

                    Request request = new Request.Builder()
                                        .url(ConstantClass.URL + ConstantClass.login_url)
                                        .post(formBody)
                                        .build();

                   client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("APp", e.toString());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            String output = response.body().string();
                            //System.out.println("RESULT : " + output);

                            try {
                                JSONObject jobject = new JSONObject(output);
                                    String name = jobject.getString(ConstantClass.username);
                                     String user_id = jobject.getString(ConstantClass.userId);
                                     String role = jobject.getString(ConstantClass.roleId);
                                     String bank = jobject.getString(ConstantClass.bankId);
                                     String showroom = jobject.getString(ConstantClass.showroomId);
                                    //System.out.println("RESULTTTT : " + name);
                                            DataHolderClass.getInstance().setUserid(user_id);
                                            DataHolderClass.getInstance().setRole(role);
                                            DataHolderClass.getInstance().setBank(bank);
                                            DataHolderClass.getInstance().setShowroom(showroom);


                                        sqlData.insert(name);

                                          if (email.getText().toString().equals(name) && password.getText().toString().equals("dicv@123"))
                                            {
                                                Intent i = new Intent(Login_Activity.this, DashBoard_Activity.class);
                                                startActivity(i);
                                            }
                                          else
                                          {
                                              Snackbar snackbar = Snackbar
                                                      .make(coordinatorLayout, "Wrong Credentials", Snackbar.LENGTH_SHORT);

                                              snackbar.show();
                                          }

                            }
                            catch (JSONException j)
                            {
                                j.printStackTrace();
                            }
                        }
                    });


                }
                catch(RuntimeException io)
                {
                    io.printStackTrace();
                }
                return null;
            }

        }.execute();
    }



}
