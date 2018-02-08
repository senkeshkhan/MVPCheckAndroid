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

package empolyesecurity.mvpcheckandroid.di.module;

import android.app.Application;
import android.content.Context;



import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import empolyesecurity.mvpcheckandroid.BuildConfig;
import empolyesecurity.mvpcheckandroid.R;
import empolyesecurity.mvpcheckandroid.data.AppDataManager;
import empolyesecurity.mvpcheckandroid.data.DataManager;
import empolyesecurity.mvpcheckandroid.data.db.AppDbHelper;
import empolyesecurity.mvpcheckandroid.data.db.DbHelper;
import empolyesecurity.mvpcheckandroid.data.network.ApiHeader;
import empolyesecurity.mvpcheckandroid.data.network.ApiHelper;
import empolyesecurity.mvpcheckandroid.data.network.AppApiHelper;
import empolyesecurity.mvpcheckandroid.data.prefs.AppPreferencesHelper;
import empolyesecurity.mvpcheckandroid.data.prefs.PreferencesHelper;
import empolyesecurity.mvpcheckandroid.di.ApiInfo;
import empolyesecurity.mvpcheckandroid.di.ApplicationContext;
import empolyesecurity.mvpcheckandroid.di.DatabaseInfo;
import empolyesecurity.mvpcheckandroid.di.PreferenceInfo;
import empolyesecurity.mvpcheckandroid.utils.AppConstants;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
