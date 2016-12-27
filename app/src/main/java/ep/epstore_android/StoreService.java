package ep.epstore_android;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class StoreService {
    interface RestApi {
        String URL = "http://epstore.tk/api/";

        @GET("products")
        Call<List<Product>> getAllProducts();

        @GET("products/{id}")
        Call<Product> get(@Path("id") int id);
    }

    private static RestApi instance;

    public static synchronized RestApi getInstance() {
        if (instance == null) {
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestApi.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            instance = retrofit.create(RestApi.class);
        }

        return instance;
    }
}
