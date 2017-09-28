package ng.shoppi.roamsdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import ng.shoppi.roamsdk.model.Button;
import ng.shoppi.roamsdk.model.Checkbox;
import ng.shoppi.roamsdk.model.Client;
import ng.shoppi.roamsdk.model.Edit;
import ng.shoppi.roamsdk.model.Field;
import ng.shoppi.roamsdk.model.FieldType;
import ng.shoppi.roamsdk.model.Form;
import ng.shoppi.roamsdk.model.MCamera;
import ng.shoppi.roamsdk.model.Page;
import ng.shoppi.roamsdk.model.Radio;
import ng.shoppi.roamsdk.model.Spinner;
import ng.shoppi.roamsdk.model.Switch;
import ng.shoppi.roamsdk.model.User;
import ng.shoppi.roamsdk.util.ApiCalls;
import ng.shoppi.roamsdk.util.GPSTracker;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 4/10/2017.
 */
public class Roam {
    private int id;
    private static Context context;
    private static String access_token;
    private static ApiCalls apiCalls;
    private static GPSTracker gpsTracker;

    private static final String BASE_URL = "http://e-testpedia.com/projects/andyforms/";

    private static Roam roam;
    private Client client;

    public static Roam initialize(Context context, String access_token) {
        roam = new Roam();
        Roam.context = context;
        Roam.access_token = access_token;

        AddHeadersToRetrofit();
        gpsTracker = new GPSTracker(context);
        gpsTracker.getLocation();

        return roam;
    }

    private static void AddHeadersToRetrofit() {
        // Define the interceptor, add authentication headers
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("Authorization", "Bearer " + access_token).build();
                return chain.proceed(newRequest);
            }
        };

        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS);

        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        // Set the custom client when building adapter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiCalls = retrofit.create(ApiCalls.class);
    }


    /**
     * @param user_id : The user's id
     * @inheritDoc Gets all the Clients the user works for
     */
    public void getClients(String user_id, final OnClientResponseListener listener) {
        //Call<ArrayList<Client>> callGetClients = apiCalls.getClients(user_id);
        Call<ArrayList<Client>> callGetClients = apiCalls.getClients();
        callGetClients.enqueue(new Callback<ArrayList<Client>>() {
            @Override
            public void onResponse(Call<ArrayList<Client>> call, Response<ArrayList<Client>> response) {
                int code = response.code();
                if (code == 200) {
                    ArrayList<Client> body = response.body();
                    listener.onSuccess(code, body);
                } else {
                    listener.onError(code);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Client>> call, Throwable t) {
                listener.onTimeOut();
            }
        });
    }

   /* public void getForm(final OnFormResponseListener listener)  {
        Call<ArrayList<Page>> callGetForm = apiCalls.getForm();
        callGetForm.enqueue(new Callback<ArrayList<Page>>() {
            @Override
            public void onResponse(Call<ArrayList<Page>> call, Response<ArrayList<Page>> response) {
                int code = response.code();
                if (code==200){
                    ArrayList<Page> body = response.body();

                    Form f = new Form();
                    f.setTitle("Jobberman");
                    f.setDescription("A form by Jobberman");
                    f.setPages(body);

                    showForm(f);

                    listener.onSuccess(code, body);
                }else{
                    listener.onError(code);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Page>> call, Throwable t) {
                t.printStackTrace();
                listener.onTimeOut();
            }
        });
    }*/


    /**
     * @param user_id : The user's id
     * @inheritDoc Gets all the Forms of a user
     */
    public void getForms(String user_id, final OnFormResponseListener listener) {
        //Call<ArrayList<Form>> callGetForm = apiCalls.getForms(user_id);
        Call<ArrayList<Form>> callGetForm = apiCalls.getForms();
        callGetForm.enqueue(new Callback<ArrayList<Form>>() {
            @Override
            public void onResponse(Call<ArrayList<Form>> call, Response<ArrayList<Form>> response) {
                int code = response.code();
                if (code == 200) {
                    ArrayList<Form> body = response.body();

                    listener.onSuccess(code, body);
                } else {
                    listener.onError(code);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Form>> call, Throwable t) {
                t.printStackTrace();
                listener.onTimeOut();
            }
        });
    }


    public void getPages(final Form form, final OnPagesResponseListener listener){
        Call<JsonArray> callGetPages = apiCalls.getPages();
        callGetPages.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                int code = response.code();
                if (code == 200) {
                    JsonArray formContent = response.body();
                    ArrayList<Page> pages = new ArrayList<>();
                    Gson gson = new Gson();

                    for (JsonElement pageJsonElement: formContent) {
                        JsonObject pageJsonObject = pageJsonElement.getAsJsonObject();

                        Page page = new Page();
                        ArrayList<Field> fieldList = new ArrayList<>();

                        if (pageJsonObject.has("pageTitle")){
                            page.setPageTitle(pageJsonObject.get("pageTitle").getAsString());
                        }

                        if (pageJsonObject.has("page_title")){
                            page.setPageTitle(pageJsonObject.get("page_title").getAsString());
                        }


                        JsonArray jsonFields = null;
                        if (pageJsonObject.has("pageLayout")){
                            jsonFields = pageJsonObject.get("pageLayout").getAsJsonArray();
                        }else if (pageJsonObject.has("page_layout")){
                            jsonFields = pageJsonObject.get("page_layout").getAsJsonArray();
                        }

                        if (jsonFields!=null) {
                            for (JsonElement field : jsonFields) {
                                JsonObject j2 = field.getAsJsonObject();
                                String type = j2.get("fieldType").getAsString();

                                if (type.equalsIgnoreCase(FieldType.EDIT.toString())) {
                                    Edit ee = gson.fromJson(j2, Edit.class);
                                    fieldList.add(ee);
                                } else if (type.equalsIgnoreCase(FieldType.RADIO.toString())) {
                                    Radio rr = gson.fromJson(j2, Radio.class);
                                    fieldList.add(rr);
                                } else if (type.equalsIgnoreCase(FieldType.CHECKBOX.toString())) {
                                    Checkbox cc = gson.fromJson(j2, Checkbox.class);
                                    fieldList.add(cc);
                                } else if (type.equalsIgnoreCase(FieldType.SPINNER.toString())) {
                                    Spinner ss = gson.fromJson(j2, Spinner.class);
                                    fieldList.add(ss);
                                } else if (type.equalsIgnoreCase(FieldType.BUTTON.toString())) {
                                    Button bb = gson.fromJson(j2, Button.class);
                                    fieldList.add(bb);
                                } else if (type.equalsIgnoreCase(FieldType.SWITCH.toString())) {
                                    Switch f = gson.fromJson(j2, Switch.class);
                                    fieldList.add(f);
                                } else if (type.equalsIgnoreCase(FieldType.CAMERA.toString())) {
                                    MCamera f = gson.fromJson(j2, MCamera.class);
                                    fieldList.add(f);
                                }
                            }
                        }
                        //JsonElement j = gson.to
                        page.setPageLayout(fieldList);
                        pages.add(page);
                    }
                    form.setPages(pages);
                    listener.onSuccess(code, pages);
                } else {
                    listener.onError(code);
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                t.printStackTrace();
                listener.onTimeOut();
            }
        });
    }


    /**
     * @param client_id : id of the client
     * @param user_id   : The user's id
     * @inheritDoc Gets the forms of a User by a Specific client.
     */
    public void getForms(String user_id, String client_id, final OnFormResponseListener listener) {
        Call<ArrayList<Form>> callGetForm = apiCalls.getForms(user_id, client_id);
        callGetForm.enqueue(new Callback<ArrayList<Form>>() {
            @Override
            public void onResponse(Call<ArrayList<Form>> call, Response<ArrayList<Form>> response) {
                int code = response.code();
                if (code == 200) {
                    ArrayList<Form> body = response.body();

                    listener.onSuccess(code, body);
                } else {
                    listener.onError(code);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Form>> call, Throwable t) {
                t.printStackTrace();
                listener.onTimeOut();
            }
        });
    }


    private void Decode(ArrayList<Page> body) {
        //JsonArray

    }

    public void showForm(Form f) {
        Intent i = new Intent(context, RoamFormActivity.class);
        i.putExtra("FORM",f);
        context.startActivity(i);
    }

    public double getUserLatitude() {
        return User.latitude;
    }


    public double getUserLongitude() {
        return User.longitude;
    }

    public void CheckIn(ng.shoppi.roamsdk.model.Location location, OnCheckInResponseListener listener) {
        if (gpsTracker==null){
            gpsTracker = new GPSTracker(context);
        }
        gpsTracker.getLocation();

        if (getUserLatitude() == 0 || getUserLongitude() == 0) {
            listener.onGPSError();
            return;
        }

        double distance = GPSTracker.distance(getUserLatitude(), getUserLongitude(), location.getLatitude(), location.getLongitude(), 'K');

        if (distance > location.getCheckableDistance()) {
            listener.onDistanceError(distance, location.getCheckableDistance(), "Kilometers");
            return;
        }

        if (distance <= location.getCheckableDistance()) {
            listener.onSuccess();
        }
    }


    public interface OnResponseListener {
        void onSuccess(int code, String body);

        void onError(int code);

        void onTimeOut();
    }

    public interface OnClientResponseListener {
        void onSuccess(int code, ArrayList<Client> body);

        void onError(int code);

        void onTimeOut();
    }


    public interface OnFormResponseListener {
        void onSuccess(int code, ArrayList<Form> forms);

        void onError(int code);

        void onTimeOut();
    }


    public interface OnPagesResponseListener {
        void onSuccess(int code, ArrayList<Page> pages);

        void onError(int code);

        void onTimeOut();
    }

    public interface OnCheckInResponseListener {
        void onSuccess();

        void onGPSError();

        void onDistanceError(double distanceFromLocation, double checkableDistance, String units);
    }
}
