/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package empolyesecurity.mvpcheckandroid.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;

import butterknife.ButterKnife;

import empolyesecurity.mvpcheckandroid.R;
import empolyesecurity.mvpcheckandroid.ui.base.BaseActivity;
import empolyesecurity.mvpcheckandroid.ui.login.LoginActivity;
import empolyesecurity.mvpcheckandroid.ui.main.MainActivity;


/**
 * Created by janisharali on 27/01/17.
 */

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;
    private static int SPLASH_TIME_OUT = 3000;

    String json_string = "{\n" +
            "  \"title\":\"JSONParserTutorial\",\n" +
            "  \"array\":[\n" +
            "    {\n" +
            "    \"company\":\"Google\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"company\":\"Facebook\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"company\":\"LinkedIn\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"company\" : \"Microsoft\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"company\": \"Apple\"\n" +
            "    }\n" +
            "    ],\n" +
            "    \"nested\":{\n" +
            "    \"flag\": true,\n" +
            "    \"random_number\":1\n" +
            "    }\n" +
            "}";
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SplashActivity.this);


    }

    /**
     * Making the screen wait so that the  branding can be shown
     */
    @Override
    public void openLoginActivity() {


try{
    JSONObject root = new JSONObject(json_string);

    JSONArray array= root.getJSONArray("array");
    System.out.println("9999999999999999"+array);
}catch (Exception e){

}


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                /*Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();*/
                Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
                startActivity(intent);
                finish();
                System.out.println("11111111111111111111");

            }
        }, SPLASH_TIME_OUT);
hideLoading();
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
        System.out.println("2222222222222222222");
    }

    @Override
    public void startSyncService() {
//        SyncService.start(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
}
