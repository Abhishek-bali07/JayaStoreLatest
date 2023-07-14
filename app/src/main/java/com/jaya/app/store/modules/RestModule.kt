package com.jaya.app.store.modules


import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jaya.app.store.data.sources.online.AddProductApi
import com.jaya.app.store.data.sources.online.AppVersionApi
import com.jaya.app.store.data.sources.online.DashboardApi
import com.jaya.app.store.data.sources.online.MobileVerifyApi
import com.jaya.app.store.data.sources.online.SendOtpApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RestModule {

    private fun <T> provideApi(appContext: Context, klass: Class<T>): T {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(
            ChuckerInterceptor.Builder(appContext)
                .collector(ChuckerCollector(appContext))

                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        ).build()

        return Retrofit.Builder()
            .baseUrl("https://api.npoint.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(klass)
    }


    @Provides
    @Singleton
    fun provideAppVersionApi(@ApplicationContext appContext: Context): AppVersionApi =
        provideApi(appContext, AppVersionApi::class.java)



    @Provides
    @Singleton
    fun provideMobileVrifyApi(@ApplicationContext appContext: Context): MobileVerifyApi =
        provideApi(appContext, MobileVerifyApi::class.java)


    @Provides
    @Singleton
    fun provideSendOtpApi(@ApplicationContext appContext: Context): SendOtpApi =
        provideApi(appContext, SendOtpApi::class.java)

    @Provides
    @Singleton
    fun provideDashboardApi(@ApplicationContext appContext: Context): DashboardApi =
        provideApi(appContext, DashboardApi::class.java)


    @Provides
    @Singleton
    fun provideAddProductApi(@ApplicationContext appContext: Context): AddProductApi =
        provideApi(appContext, AddProductApi::class.java)








}