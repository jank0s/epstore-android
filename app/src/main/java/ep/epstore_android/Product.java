package ep.epstore_android;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by jan on 27/12/2016.
 */

public class Product implements Serializable {
    public int id;
    public String name;
    public double price;
    public int rating;
    public String uri;

    @Override
    public String toString() {
        /*return String.format(Locale.ENGLISH,
                "%s: %s(%.2f EUR)",
                name, description, price);*/
        return "";
    }
}


