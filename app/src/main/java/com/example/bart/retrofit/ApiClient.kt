package com.example.bart.retrofit

import com.example.bart.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    private const val BASE_URL = "https://api.bart.gov/api/"

   val client = OkHttpClient.Builder()
    .addInterceptor { chain: Interceptor.Chain ->
        val original = chain.request()

        // Use url() method to get the URL
        val url = original.url().newBuilder()
            .addQueryParameter("key", BuildConfig.API_KEY)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        chain.proceed(request)
    }.build()


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Replace with your base URL
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    private val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }


//    @Provides
//    @Singleton
//    val apiService: ApiInterface by lazy {
//        retrofit.create(ApiInterface::class.java)
//    }
}
