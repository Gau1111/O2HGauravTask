package info.softweb.gauravo2hpractical.network

import info.softweb.gauravo2hpractical.models.UserListResponseModel
import info.softweb.gauravo2hpractical.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetriveApi {
    @GET("api")
    suspend fun getUsers(@Query("results") results : Int) : Response<UserListResponseModel>

    //https://randomuser.me/api/?page=1&results=10
    companion object{

        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : RetriveApi {
            val interceptor=HttpLoggingInterceptor()
            interceptor.level=HttpLoggingInterceptor.Level.BODY
            val okHttpClient:OkHttpClient=OkHttpClient.Builder().addInterceptor(interceptor)
                .addInterceptor(networkConnectionInterceptor).
                build()
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
                .create(RetriveApi::class.java)
        }
    }

}

