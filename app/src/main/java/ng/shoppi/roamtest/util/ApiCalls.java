package ng.shoppi.roamtest.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by User on 3/20/2017.
 */
public interface ApiCalls {
    //Auth
    //***********************************************************************************************
    //Me

    @GET("user/me")
    Call<JsonObject> getMe();

}
