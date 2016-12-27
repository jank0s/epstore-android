package ep.epstore_android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

/**
 * Created by jan on 27/12/2016.
 */

public class ProductAdapter extends ArrayAdapter<Product>{
    public ProductAdapter(Context context, List<Product> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        final Product product = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.productlist_element, parent, false);
        }

        final TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        final TextView tvRating = (TextView) convertView.findViewById(R.id.tv_rating);
        final TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price);

        tvName.setText(product.name);
        tvRating.setText("Ocena: "+ product.rating);
        tvPrice.setText(String.format(Locale.ENGLISH, "%.2f €", product.price));

        return convertView;
    }

}
