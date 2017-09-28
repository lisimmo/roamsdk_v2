package ng.shoppi.roamsdk;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import ng.shoppi.roamsdk.adapter.CameraItemsRecyclerAdapter;
import ng.shoppi.roamsdk.model.Button;
import ng.shoppi.roamsdk.model.Checkbox;
import ng.shoppi.roamsdk.model.Form;
import ng.shoppi.roamsdk.model.Edit;
import ng.shoppi.roamsdk.model.Field;
import ng.shoppi.roamsdk.model.FieldFormat;
import ng.shoppi.roamsdk.model.FieldType;
import ng.shoppi.roamsdk.model.MCamera;
import ng.shoppi.roamsdk.model.Page;
import ng.shoppi.roamsdk.model.Radio;
import ng.shoppi.roamsdk.model.RadioOption;
import ng.shoppi.roamsdk.model.Spinner;
import ng.shoppi.roamsdk.model.Switch;

public class RoamFormActivity extends AppCompatActivity {

    private Form form;
    private LinearLayout linearLayoutPage;
    private static Context context;

    private ArrayList<Page> pages;
    private ArrayList<SinglePageFragment> pageFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roam_form);
        context = this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            form = (Form)extras.getSerializable("FORM");
        }

        if (form==null){
            form = new Form();
        }

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        linearLayoutPage = (LinearLayout) findViewById(R.id.linearLayoutPage);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(form.getTitle());

            //getSupportActionBar().setSubtitle("Hi bro");

            /*getSupportActionBar().setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });*/
        } catch (Exception e) {
            e.printStackTrace();
        }




        pages = form.getPages();
        DecodePageList(pages);

        CreateTabs();
    }

    private void DecodePageList(ArrayList<Page> pages) {
        Gson gson = new Gson();

        for (Page page : pages) {
            String title = page.getPageTitle();
            //Log.e("Roam ", "Field: " + title);

            ArrayList<Field> fieldList = new ArrayList<>();
            fieldList = page.getPageLayout();
            Log.e("Roam ", "FieldList Initial: " + fieldList);

            int l = fieldList.size();
            for (int i = 0; i < l; i++) {
                Field field = fieldList.get(i);

                if (field.getFieldType().equalsIgnoreCase(FieldType.EDIT.toString())) {
                    Edit e = gson.fromJson(gson.toJson(field), Edit.class);
                    fieldList.set(i, e);
                } else if (field.getFieldType().equalsIgnoreCase(FieldType.RADIO.toString())) {
                    Radio r = gson.fromJson(gson.toJson(field), Radio.class);
                    fieldList.set(i, r);
                } else if (field.getFieldType().equalsIgnoreCase(FieldType.CHECKBOX.toString())) {
                    Checkbox c = gson.fromJson(gson.toJson(field), Checkbox.class);
                    fieldList.set(i, c);
                } else if (field.getFieldType().equalsIgnoreCase(FieldType.SPINNER.toString())) {
                    Log.e("Roam ", "Field: " + field);
                    Spinner s = gson.fromJson(gson.toJson(field), Spinner.class);
                    fieldList.set(i, s);
                } else if (field.getFieldType().equalsIgnoreCase(FieldType.BUTTON.toString())) {
                    Button b = gson.fromJson(gson.toJson(field), Button.class);
                    fieldList.set(i, b);
                } else if (field.getFieldType().equalsIgnoreCase(FieldType.SWITCH.toString())) {
                    Switch s = gson.fromJson(gson.toJson(field), Switch.class);
                    fieldList.set(i, s);
                } else if (field.getFieldType().equalsIgnoreCase(FieldType.CAMERA.toString())) {
                    MCamera m = gson.fromJson(gson.toJson(field), MCamera.class);
                    fieldList.set(i, m);
                }
            }
            Log.e("Roam ", "FieldList Final: " + fieldList);
        }
    }

    private void CreateTabs() {
        linearLayoutPage.removeAllViewsInLayout();
        View v = View.inflate(context, R.layout.mtablayout, null);

        int l = pages.size();
        if (pages == null || l == 0) {
            return;
        }

        Log.e("MainActivity", "Got here 1");
        for (int i = 0; i < l; i++) {
            SinglePageFragment s = SinglePageFragment.newInstance(pages.get(i));
            /*Bundle args = new Bundle();
            args.putString("TITLE", pageList.get(i).getPageTitle());
            args.putParcelable("PAGE", pageList.get(i));
            s.setArguments(args);*/

            pageFragmentList.add(s);

            //pageFragmentTitleList.add(pageList.get(i).getPageTitle());
        }

        ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        //viewPager.setOffscreenPageLimit(6);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
        if (l == 1) {
            tabLayout.setVisibility(View.GONE);
        } else {
            tabLayout.setVisibility(View.VISIBLE);
        }

        if (l > 2) {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }

        linearLayoutPage.addView(v);
    }


    public static class SinglePageFragment extends Fragment {

        private Page page;
        private LinearLayout linearLayoutForm;
        float density;

        public SinglePageFragment() {
        }

        public static SinglePageFragment newInstance(Page page) {
            SinglePageFragment fragment = new SinglePageFragment();
            Bundle args = new Bundle();
            args.putSerializable("PAGE", page);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_single_page, container, false);

            Bundle extras = getArguments();
            if (extras != null) {
                //page = extras.getParcelable("PAGE");
                page = (Page)extras.getSerializable("PAGE");
            }

            DisplayMetrics metrics = getResources().getDisplayMetrics();
            density = (int) metrics.density;

            linearLayoutForm = (LinearLayout) rootView.findViewById(R.id.linearLayoutForm);
            CreateForms();


            return rootView;
        }

        private void CreateForms() {
            linearLayoutForm.removeAllViewsInLayout();

            for (Field f : page.getPageLayout()) {
                if (f.getFieldType().equalsIgnoreCase(FieldType.EDIT.toString())) {
                    AddEdit((Edit) f);
                } else if (f.getFieldType().equalsIgnoreCase(FieldType.RADIO.toString())) {
                    AddRadio((Radio) f);
                } else if (f.getFieldType().equalsIgnoreCase(FieldType.CHECKBOX.toString())) {
                    AddCheckBox((Checkbox) f);
                } else if (f.getFieldType().equalsIgnoreCase(FieldType.SPINNER.toString())) {
                    AddSpinner((Spinner) f);
                } else if (f.getFieldType().equalsIgnoreCase(FieldType.BUTTON.toString())) {
                    AddButton((Button) f);
                } else if (f.getFieldType().equalsIgnoreCase(FieldType.SWITCH.toString())) {
                    AddSwitch((Switch) f);
                } else if (f.getFieldType().equalsIgnoreCase(FieldType.CAMERA.toString())) {
                    AddCamera((MCamera) f);
                }

            }
        }

        //Adding Views

        private void AddEdit(final Edit edit) {
            View v = View.inflate(context, R.layout.edit, null);
            TextView textView = (TextView) v.findViewById(R.id.textViewLabel);
            textView.setText(edit.getFieldName());

            //LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            EditText editText = (EditText) v.findViewById(R.id.editText);
            //editText.setPadding(5,10,5,10);
            editText.setHint(edit.getFieldHint());
            //editText.setLayoutParams(params1);

            //setting the fieldformat
            if (edit.getFieldFormat().equalsIgnoreCase(FieldFormat.PHONE.toString())) {
                editText.setInputType(InputType.TYPE_CLASS_PHONE);
                int maxLengthofEditText = 11;
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLengthofEditText)});
            } else if (edit.getFieldFormat().equalsIgnoreCase(FieldFormat.EMAIL.toString())) {
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            } else if (edit.getFieldFormat().equalsIgnoreCase(FieldFormat.NUMBER.toString())) {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            } else if (edit.getFieldFormat().equalsIgnoreCase(FieldFormat.NAME.toString())) {
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            } else if (edit.getFieldFormat().equalsIgnoreCase(FieldFormat.TEXT.toString())) {
                editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            }

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    //Log.e("AndyForms", "The text: " + charSequence);
                    edit.setFieldValue(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            float m1 = getResources().getDimension(R.dimen.field_left_margin);
            float m2 = getResources().getDimension(R.dimen.field_top_margin);
            float m3 = getResources().getDimension(R.dimen.field_right_margin);
            float m4 = getResources().getDimension(R.dimen.field_bottom_margin);
            //params.setMargins(0,(int)(m*density), (int)(m2*density), 0);
            params.setMargins((int) (m1 * density), (int) (m2 * density), (int) (m3 * density), (int) (m4 * density));

            linearLayoutForm.addView(v, params);
        }

        private void AddRadio(final Radio radio) {

            View v = View.inflate(context, R.layout.radio, null);
            TextView textView = (TextView) v.findViewById(R.id.textViewLabel);
            textView.setText(radio.getFieldName());

            RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radioGroup);
            for (RadioOption o : radio.getOptions()
                    ) {
                //View voptions =
                RadioButton radioButton = (RadioButton) View.inflate(context, R.layout.radiooption, null);
                radioButton.setText(o.getValue());
                radioButton.setPadding(5, 10, 5, 10);
                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40);
                params.setMargins(0, (int) (8 * density), 0, 0);
                //radioButton.setLayoutParams(params);
                radioGroup.addView(radioButton, params);
            }

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    int radioButtonID = radioGroup.getCheckedRadioButtonId();
                    View radioButton = radioGroup.findViewById(radioButtonID);
                    int idx = radioGroup.indexOfChild(radioButton);

                    //I disagree with the below Feb 18
                    //We use i - 1 because i starts from 1 but our outions are indexed from 0;
                    //radio.DoCheck(idx - 1, true);
                    radio.DoCheck(idx, true);

                    int l = radioGroup.getChildCount();
                    for (int j = 0; j < l; j++) {
                        RadioButton r = (RadioButton) radioGroup.getChildAt(j);
                        if (idx == j) {
                            r.setBackgroundResource(R.drawable.oval_radio_options_pressed);
                        } else {
                            r.setBackgroundResource(R.drawable.oval_radio_options_unpressed);
                        }
                    }

                /*// TODO: 10/23/2016 Remove
                String t = gson.toJson(PAGE.getPageLayout());
                //Log.e("AndyForms","Radio: "+t);
                Log.e("AndyForms", "Fields: " + t);*/

                }
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            float m1 = getResources().getDimension(R.dimen.field_left_margin);
            float m2 = getResources().getDimension(R.dimen.field_top_margin);
            float m3 = getResources().getDimension(R.dimen.field_right_margin);
            float m4 = getResources().getDimension(R.dimen.field_bottom_margin);
            //params.setMargins(0,(int)(m*density), (int)(m2*density), 0);
            params.setMargins((int) (m1 * density), (int) (m2 * density), (int) (m3 * density), (int) (m4 * density));
            linearLayoutForm.addView(v, params);
            //linearLayoutForm.addView(radioGroup);
        }

        private void AddCheckBox(final Checkbox checkbox) {
            View v = View.inflate(context, R.layout.checkbox, null);
            TextView textView = (TextView) v.findViewById(R.id.textViewLabel);
            textView.setText(checkbox.getFieldName());

            LinearLayout linearLayoutCheckboxes = (LinearLayout) v.findViewById(R.id.linearLayoutCheckboxes);

            int i = 0;
            for (RadioOption o : checkbox.getOptions()
                    ) {
                CheckBox checkBoxView = (CheckBox) View.inflate(context, R.layout.checkoption, null);
                checkBoxView.setText(o.getValue());
                checkBoxView.setTag(i);
                checkBoxView.setPadding(5, 10, 5, 10);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                float m = getResources().getDimension(R.dimen.radio_padding_left);
                params.setMargins((int) (m * density), (int) (8 * density), 0, 0);
                //radioButton.setLayoutParams(params);

                checkBoxView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        checkbox.DoCheck((int) compoundButton.getTag(), b);

                        if (b) {
                            compoundButton.setBackgroundResource(R.drawable.oval_radio_options_pressed);
                        } else {
                            compoundButton.setBackgroundResource(R.drawable.oval_radio_options_unpressed);
                        }
                    }
                });
                linearLayoutCheckboxes.addView(checkBoxView, params);

                i++;
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            float m1 = getResources().getDimension(R.dimen.field_left_margin);
            float m2 = getResources().getDimension(R.dimen.field_top_margin);
            float m3 = getResources().getDimension(R.dimen.field_right_margin);
            float m4 = getResources().getDimension(R.dimen.field_bottom_margin);
            params.setMargins((int) (m1 * density), (int) (m2 * density), (int) (m3 * density), (int) (m4 * density));

            linearLayoutForm.addView(v, params);

        }

        private void AddSpinner(final Spinner spinner) {
            View v = View.inflate(context, R.layout.spinner, null);
            TextView textView = (TextView) v.findViewById(R.id.textViewLabel);
            textView.setText(spinner.getFieldName());
            int l = spinner.getOptions().size();
            String[] options = new String[l];
            for (int i = 0; i < l; i++) {
                options[i] = spinner.getOptions().get(i).getValue();
            }

            ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, options);
            android.widget.Spinner spinnerView = (android.widget.Spinner) v.findViewById(R.id.spinnerView);
            spinnerView.setAdapter(adapter);

            spinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinner.setFieldValue(spinner.getOptions().get(i).getValue());

                /*// TODO: 10/23/2016 Remove
                String t = gson.toJson(PAGE.getPageLayout());
                //Log.e("AndyForms","Radio: "+t);
                Log.e("AndyForms", "Fields: " + t);*/
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            float m1 = getResources().getDimension(R.dimen.field_left_margin);
            float m2 = getResources().getDimension(R.dimen.field_top_margin);
            float m3 = getResources().getDimension(R.dimen.field_right_margin);
            float m4 = getResources().getDimension(R.dimen.field_bottom_margin);
            params.setMargins((int) (m1 * density), (int) (m2 * density), (int) (m3 * density), (int) (m4 * density));

            linearLayoutForm.addView(v, params);
        }

        private void AddButton(final Button button) {
            android.widget.Button buttonView = (android.widget.Button) View.inflate(context, R.layout.button, null);
            buttonView.setText(button.getFieldName());

            buttonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //mListener.CreateSubmissionOutput();
                }
            });

            int buttonWidth = 200;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (buttonWidth * density), ViewGroup.LayoutParams.WRAP_CONTENT);
            float m1 = getResources().getDimension(R.dimen.field_left_margin);
            float m2 = getResources().getDimension(R.dimen.field_top_margin);
            float m3 = getResources().getDimension(R.dimen.field_right_margin);
            float m4 = getResources().getDimension(R.dimen.field_bottom_margin);
            params.setMargins((int) (m1 * density), (int) (m2 * density), (int) (m3 * density), (int) (m4 * density));
            params.gravity = Gravity.CENTER_HORIZONTAL;

            linearLayoutForm.addView(buttonView, params);

        }

        private void AddSwitch(final Switch mSwitch) {
            View v = View.inflate(context, R.layout.switch_layout, null);

            TextView textView = (TextView) v.findViewById(R.id.switchLabel);
            textView.setText(mSwitch.getFieldName());

            SwitchCompat switchView = (SwitchCompat) v.findViewById(R.id.switchValue);
            switchView.setChecked(Boolean.valueOf(mSwitch.getFieldValue()));

            switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mSwitch.setFieldValue(String.valueOf(b));
                }
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
            float m1 = getResources().getDimension(R.dimen.field_left_margin);
            float m2 = getResources().getDimension(R.dimen.field_top_margin);
            float m3 = getResources().getDimension(R.dimen.field_right_margin);
            float m4 = getResources().getDimension(R.dimen.field_bottom_margin);
            //params.setMargins(0,(int)(m*density), (int)(m2*density), 0);
            params.setMargins((int) (m1 * density), (int) (m2 * density), (int) (m3 * density), (int) (m4 * density));

            linearLayoutForm.addView(v, params);

        }

        private void AddCamera(final MCamera mCamera) {
            View v = View.inflate(context, R.layout.camera_layout, null);

            TextView textView = (TextView) v.findViewById(R.id.cameraLabel);
            textView.setText(mCamera.getFieldName());

            ImageView imageViewCameraButton = (ImageView) v.findViewById(R.id.imageViewCameraButton);


            RecyclerView recyclerViewPictures = (RecyclerView) v.findViewById(R.id.recyclerViewPictures);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            final RecyclerView.Adapter adapter = new CameraItemsRecyclerAdapter(context, mCamera);

            recyclerViewPictures.setHasFixedSize(false);
            recyclerViewPictures.setLayoutManager(layoutManager);
            recyclerViewPictures.setAdapter(adapter);

            imageViewCameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //currentAdapter = adapter;
                    //TakePicture(mCamera);
                }
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            float m1 = getResources().getDimension(R.dimen.field_left_margin);
            float m2 = getResources().getDimension(R.dimen.field_top_margin);
            float m3 = getResources().getDimension(R.dimen.field_right_margin);
            float m4 = getResources().getDimension(R.dimen.field_bottom_margin);
            //params.setMargins(0,(int)(m*density), (int)(m2*density), 0);
            params.setMargins((int) (m1 * density), (int) (m2 * density), (int) (m3 * density), (int) (m4 * density));

            linearLayoutForm.addView(v, params);

        }
    }

    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public Fragment getItem(int position) {

            //return SinglePageFragment.newInstance(1);
            return pageFragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pages.get(position).getPageTitle();
        }
    }
}
