package com.zafer.mvvmzaferapp.data.network

import com.zafer.mvvmzaferapp.data.network.responses.AuthResponse
import com.zafer.mvvmzaferapp.data.network.responses.QuotesResponse
import com.zafer.mvvmzaferapp.data.network.responses.TodoItemsResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password")password: String
    ): Response<AuthResponse>


    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignup(
        @Field("name") name: String,
        @Field("email") email:String,
        @Field("password") password: String
    ): Response<AuthResponse>


    @GET("todoItems")  //TODO add get service your restfull api on serverside :( could nt finish yet
    suspend fun getTodoItems() : Response<TodoItemsResponse>

    @GET("quotes")
    suspend fun getQuotes() : Response<QuotesResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi{

            val okHttpClient =  OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor)
                .build()

            return  Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)

        }
    }

}