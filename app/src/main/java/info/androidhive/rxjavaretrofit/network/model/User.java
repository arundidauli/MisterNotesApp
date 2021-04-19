package info.androidhive.rxjavaretrofit.network.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ravi on 20/02/18.
 */
@IgnoreExtraProperties
public class User extends BaseResponse {

    @SerializedName("api_key")
    String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
