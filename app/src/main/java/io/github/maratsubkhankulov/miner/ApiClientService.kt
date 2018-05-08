package io.github.maratsubkhankulov.miner

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.io.IOException

import io.github.maratsubkhankulov.miner.entities.LoginRequest
import io.github.maratsubkhankulov.miner.entities.Job
import io.github.maratsubkhankulov.miner.entities.SolutionRequest
import io.github.maratsubkhankulov.miner.entities.SubmitResponse
import io.github.maratsubkhankulov.miner.entities.TokenResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by msubkhankulov on 5/6/18.
 *
 * Communicates with miner service.
 */

class ApiClientService {
    private var apiClient: ApiClient
    private var httpClient: OkHttpClient
    private var gson: Gson

    val workRx: Single<Job>
        get() = Single.fromObservable(apiClient
                .workRx)
                .subscribeOn(Schedulers.io())

    val work: Job?
        @Throws(IOException::class)
        get() {
            val call = apiClient.work
            val response = call.execute()
            return if (response.isSuccessful) {
                response.body()
            } else null
        }

    init {
        val baseUrl = "https://frozen-refuge-70388.herokuapp.com"

        gson = GsonBuilder().create()
        httpClient = OkHttpClient.Builder().build()
        apiClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiClient::class.java)
    }

    fun postSubmitRx(solutionRequest: SolutionRequest): Single<SubmitResponse> {
        return Single.fromObservable(apiClient
                .postSubmitRx(solutionRequest))
                .subscribeOn(Schedulers.io())
    }

    fun postTokensRx(loginRequest: LoginRequest): Single<TokenResponse> {
        return Single.fromObservable(apiClient
                .postTokensRx(loginRequest))
                .subscribeOn(Schedulers.io())
    }

    @Throws(IOException::class)
    fun postSubmit(nonce: Int?): SubmitResponse? {
        val call = apiClient.postSubmit(SolutionRequest(nonce))
        val response = call.execute()
        return if (response.isSuccessful) {
            response.body()
        } else null
    }

    private interface ApiClient {

        @get:GET("/work")
        val workRx: Observable<Job>

        @get:GET("/work")
        val work: Call<Job>

        @POST("/submit")
        fun postSubmitRx(@Body solutionRequest: SolutionRequest): Observable<SubmitResponse>

        @POST("/tokens")
        fun postTokensRx(@Body loginRequest: LoginRequest): Observable<TokenResponse>

        @POST("/submit")
        fun postSubmit(@Body solutionRequest: SolutionRequest): Call<SubmitResponse>

        @POST("/tokens")
        fun postTokens(@Body loginRequest: LoginRequest): Call<TokenResponse>
    }
}
