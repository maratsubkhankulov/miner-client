package io.github.maratsubkhankulov.miner;

import android.telephony.SubscriptionInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.math.BigInteger;

import io.github.maratsubkhankulov.miner.entites.LoginRequest;
import io.github.maratsubkhankulov.miner.entities.Job;
import io.github.maratsubkhankulov.miner.entities.SolutionRequest;
import io.github.maratsubkhankulov.miner.entities.SubmitResponse;
import io.github.maratsubkhankulov.miner.entities.TokenResponse;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by msubkhankulov on 5/6/18.
 *
 * Communicates with miner service.
 */

public class ApiClientService {
    ApiClient apiClient;
    OkHttpClient httpClient;
    Gson gson;

    public ApiClientService() {
        String baseUrl = "https://frozen-refuge-70388.herokuapp.com";

        gson = new GsonBuilder().create();
        httpClient = new OkHttpClient.Builder().build();
        apiClient = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiClient.class);
    }

    public Single<Job> getWorkRx() {
        return Single.fromObservable(apiClient
                .getWorkRx())
                .subscribeOn(Schedulers.io());
    }

    public Single<SubmitResponse> postSubmitRx(SolutionRequest solutionRequest) {
        return Single.fromObservable(apiClient
                .postSubmitRx(solutionRequest))
                .subscribeOn(Schedulers.io());
    }

    public Single<TokenResponse> postTokensRx(LoginRequest loginRequest) {
        return Single.fromObservable(apiClient
                .postTokensRx(loginRequest))
                .subscribeOn(Schedulers.io());
    }

    public Job getWork() throws IOException {
        Call<Job> call = apiClient.getWork();
        Response<Job> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        return null;
    }

    public SubmitResponse postSubmit(Integer nonce) throws IOException {
        Call<SubmitResponse> call = apiClient.postSubmit(new SolutionRequest(nonce));
        Response<SubmitResponse> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        return null;
    }

    private interface ApiClient {

        @GET("/work")
        Observable<Job> getWorkRx();

        @POST("/submit")
        Observable<SubmitResponse> postSubmitRx(@Body SolutionRequest solutionRequest);

        @POST("/tokens")
        Observable<TokenResponse> postTokensRx(@Body LoginRequest loginRequest);

        @GET("/work")
        Call<Job> getWork();

        @POST("/submit")
        Call<SubmitResponse> postSubmit(@Body SolutionRequest solutionRequest);

        @POST("/tokens")
        Call<TokenResponse> postTokens(@Body LoginRequest loginRequest);
    }
}
