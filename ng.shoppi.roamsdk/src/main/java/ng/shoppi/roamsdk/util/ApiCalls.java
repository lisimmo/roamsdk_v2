package ng.shoppi.roamsdk.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import ng.shoppi.roamsdk.model.Client;
import ng.shoppi.roamsdk.model.Form;
import ng.shoppi.roamsdk.model.Job;
import ng.shoppi.roamsdk.model.Page;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by User on 3/20/2017.
 * This class handles my API calls
 */
public interface ApiCalls {

    //Auth
    @GET("me")
    Call<JsonElement> validateToken(@Header("Authorization") String authorization);

    //Auth
    @POST("auth/login")
    Call<JsonElement> login(@Query("email") String email, @Query("password") String password, @Query("role") String role);

    @POST("auth/login")
    Call<JsonElement> loginWithBody(@Body RequestBody body);

    @POST("auth/register")
    Call<JsonElement> register(@Body RequestBody body);

    @Multipart
    @POST("files/upload")
    Call<ResponseBody> fileUpload(@Part("description") RequestBody description,
                                  @Part MultipartBody.Part file);


    //--------------------------------------------------------------------------------------------
    @GET("roam_test_client.txt")
    Call<ArrayList<Client>> getClients();

    @GET("roam_test_forms.txt")
    Call<ArrayList<Form>> getForms();


    @GET("roam_test_jobs.txt")
    Call<ArrayList<Job>> getJobs();


    @GET("salesniper.txt")
    Call<JsonArray> getPages();









    //-------------------------------------------------------------------------------------------
    @POST("/freelancer")
    Call<ArrayList<Client>> registerAsFreelancer(@Body RequestBody body);





    /**
    * @inheritDoc
    * Get the user's Clients.
    * */
    @GET("roam_test_client.txt")
    Call<ArrayList<Client>> getClients(@Path("user_id") String user_id);


    /**
    * @inheritDoc
    * Get the user's Forms. All of it from all his clients
    * */
    @GET("roam_test_forms.txt")
    Call<ArrayList<Form>> getForms(@Path("user_id") String user_id);

    /**
    * @inheritDoc
    * Get the user's Forms of a Specific Client by the client ID
    * */
    @GET("roam_test_forms.txt")
    Call<ArrayList<Form>> getForms(@Path("user_id") String user_id, @Path("client_id") String client_id);


}
