package ng.shoppi.roamtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import ng.shoppi.roamsdk.Roam;
import ng.shoppi.roamsdk.model.Client;
import ng.shoppi.roamsdk.model.Form;
import ng.shoppi.roamsdk.model.Job;
import ng.shoppi.roamsdk.model.Location;
import ng.shoppi.roamsdk.model.Page;
import ng.shoppi.roamsdk.util.RoamSort;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Client> clients;
    private Roam roam;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpbnRlcm5hbCI6IkNkSU5xcXhCNlNC" +
                "YzMwL0dqRzg1VWZWVlJjeTc5ZzVydkhhc0N2cHJ6VVVkVklwYnkzOFc0SEEwaWI3aUxoYyttTzZZYVNnWD" +
                "UvV1RySldaRTNnT1dzSVVYUVlUeXNtSHFMY0gvWnk0bGkrYTB4VVJzeGNzWGV1NnM0aDYwcVRtblkySnlI" +
                "WUkrQWlHc3h4MTZNNzVvemxaWUF1SUs0UGcrUENVUElJK2EzY0xCQmtIMnJET2V5WXJpQXVxdyt6K2htOU" +
                "Z4U3p3MWdjPSIsInVzZXJfbmFtZSI6IjVBUUp4ODM3cGF4MUY1ekM5YmtKdEUiLCJzY29wZSI6WyJvcGVu" +
                "aWQiXSwiZXhwIjoxNDk4NTU2MTU2LCJhdXRob3JpdGllcyI6WyJ1c2VyIl0sImp0aSI6IjI2MTkwZTQ2LT" +
                "VjMGYtNDZmMS1hNGUwLTZiM2VjYWQ0NDI2YSIsImNsaWVudF9pZCI6IjRBSk0zS0JUVTVESklVN1k1OEk3" +
                "OUw1Q08ifQ.Tv-eMcg_JJiGPTf0_m6GIzewLB5P-X5pLaix6UArmlI";

        String user_id = "Yinka";
        String client_id = "Yinka";

        roam = Roam.initialize(MainActivity.this, access_token);

        roam.getClients(user_id, new Roam.OnClientResponseListener() {

            @Override
            public void onSuccess(int code, ArrayList<Client> body) {
                Log.e("Roam", "Client Response code: " + code);
                Log.e("Roam", "Client Response code: " + body.toString());

                clients = body;
            }

            @Override
            public void onError(int code) {
                Log.e("Roam", "Client Response code: " + code);
            }

            @Override
            public void onTimeOut() {
                Log.e("Roam", "Client Timeout");
            }
        });


        roam.getForms(user_id, new Roam.OnFormResponseListener() {

            @Override
            public void onSuccess(int code, final ArrayList<Form> forms) {
                Log.e("Roam", "Forms Response code: " + code);
                Log.e("Roam", "Forms Response code: " + forms.toString());

                if (forms.size() > 0) {

                    roam.getPages(forms.get(0), new Roam.OnPagesResponseListener() {
                        @Override
                        public void onSuccess(int code, ArrayList<Page> pages) {
                            Log.e("Roam", "Pages Response code: " + code);
                            Log.e("Roam", "Pages Response code: " + pages.toString());

                            roam.showForm(forms.get(0));
                        }

                        @Override
                        public void onError(int code) {

                        }

                        @Override
                        public void onTimeOut() {

                        }
                    });

                    CheckIntoFormLocations(forms);
                }

                /*for (Form form: forms) {
                    roam.getPages(form, new Roam.OnPagesResponseListener() {
                        @Override
                        public void onSuccess(int code, ArrayList<Page> pages) {
                            Log.e("Roam","Pages Response code: "+code);
                            Log.e("Roam","Pages Response code: "+pages.toString());
                        }

                        @Override
                        public void onError(int code) {

                        }

                        @Override
                        public void onTimeOut() {

                        }
                    });
                }*/
            }

            @Override
            public void onError(int code) {
                Log.e("Roam", "Forms Response code: " + code);
            }

            @Override
            public void onTimeOut() {
                Log.e("Roam", "Forms Timeout");
            }
        });

        roam.getJobs(user_id, client_id, new Roam.OnJobResponseListener() {
            @Override
            public void onSuccess(int code, ArrayList<Job> jobs) {
                Log.e("Roam", "Jobs Response code: " + code);
                Log.e("Roam", "Jobs Response code: " + jobs.toString());

                RoamSort sort = new RoamSort();

                sort.sortJobsByState(jobs, RoamSort.Sort.ASC);
                System.out.println(jobs);

                sort.sortJobsByWage(jobs, RoamSort.Sort.ASC);
                System.out.println(jobs);

                sort.sortJobsByTown(jobs, RoamSort.Sort.ASC);
                System.out.println(jobs);
            }

            @Override
            public void onError(int code) {
                Log.e("Roam", "Jobs Response code: " + code);
            }

            @Override
            public void onTimeOut() {
                Log.e("Roam", "Jobs Timeout: ");
            }
        });

        //roam.showForm();
    }

    private void CheckIntoFormLocations(ArrayList<Form> forms) {
        for (Form form : forms) {
            for (final Location location : form.getLocations()) {
                roam.CheckIn(location, new Roam.OnCheckInResponseListener() {
                    @Override
                    public void onSuccess() {
                        Log.e(TAG, "Success: " + location.getName());
                    }

                    @Override
                    public void onGPSError() {
                        Log.e(TAG, "GPS ERROR");
                    }

                    @Override
                    public void onDistanceError(double distanceFromLocation, double checkableDistance, String units) {
                        Log.e(TAG, "Distance ERROR: " + distanceFromLocation + units + " Checkable Distance: " + checkableDistance + units);
                    }
                });
            }
        }
    }
}
