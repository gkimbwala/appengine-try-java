package myapp;

import org.junit.Ignore;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;


/**
 * This test calls the real server! Maybe with Retrofit.
 */
public class IntegrationTest {
    @Test @Ignore
    public void name() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
//                 .baseUrl("https://allowance-182916.appspot.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        AllowanceService allowanceService = retrofit.create(AllowanceService.class);

        Call<AllowanceRate> call = allowanceService.getAllowanceRate("gloria");

        Response<AllowanceRate> response = call.execute();

        AllowanceRate allowanceRate = response.body();

        System.out.println(allowanceRate.name + " gets " + allowanceRate.allowance + " for doing " + allowanceRate.chores);
    }

    interface AllowanceService {
        @GET("allowance/{name}")
        Call<AllowanceRate> getAllowanceRate(@Path("name") String name);
    }

    static class AllowanceRate {
        String name;
        long allowance;
        List<String> chores;
    }
}