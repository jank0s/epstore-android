package ep.epstore_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity implements Callback<Product> {
    private static final String TAG = ProductDetailActivity.class.getCanonicalName();

    private Product product;
    private TextView tvProductDetail;
    private TextView tvProductRating;
    private TextView tvProductPrice;
    private CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        tvProductDetail = (TextView) findViewById(R.id.tv_product_detail);
        tvProductRating = (TextView) findViewById(R.id.tv_product_rating);
        tvProductPrice = (TextView) findViewById(R.id.tv_product_price);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int id = getIntent().getIntExtra("ep.epstore.id", 0);
        if (id > 0) {
            StoreService.getInstance().get(id).enqueue(this);
        }
    }


    @Override
    public void onResponse(Call<Product> call, Response<Product> response) {
        product = response.body();
        Log.i(TAG, "Got result: " + product);

        if (response.isSuccessful()) {
            tvProductDetail.setText(product.description);
            toolbarLayout.setTitle(product.name);
            tvProductRating.setText("Ocena: "+product.rating);
            tvProductPrice.setText(String.format(Locale.ENGLISH, "%.2f €", product.price));
        } else {
            String errorMessage;
            try {
                errorMessage = "An error occurred: " + response.errorBody().string();
            } catch (IOException e) {
                errorMessage = "An error occurred: error while decoding the error message.";
            }
            Log.e(TAG, errorMessage);
            tvProductDetail.setText(errorMessage);
        }
    }

    @Override
    public void onFailure(Call<Product> call, Throwable t) {
        Log.w(TAG, "Error: " + t.getMessage(), t);
    }
}
