package com.papanews;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.text.InputType;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {
    RelativeLayout cs;
    TextInputEditText textInputEditTextFullName, textInputEditTextUsername, TextInputEditTextPassowrd, TextInputEditTextEmail;
    TextInputEditText textInputNumber, textInputLocation, textInputProffesion, textOtp;

    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;
    private Context context = null;
    boolean defaultValue = false;
    Button select, upload_php;
    ImageView phpImage;
    Bitmap bitmap;
    int checkusername;

    private static final int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_REQUEST = 1;
    private Uri filepath;

    String fullName = "", userName = "", password = "", email = "", location = "", number = "", profession = "", otp;


    String URL = "http://papanews.in/PapaNews/insertImage.php";

    final int CODE_GALLERY_REQUEST = 999;
    RadioButton male, female, other;
    String usergender = "male", dateofbirth = "";

//    datepicker

    DatePickerDialog picker;
    EditText eText;
    Button btnGet;
    TextView tvw,dab;

    TextInputLayout patext;
    String pushusername;
    String pushemail, pushImage;
    TextView gendertext;

    int nextint = 0;
    int backflag = 0;

    TextInputLayout usernamevisi, mobilenumbervisi, textinputOTP;
    RadioGroup genderselectvisi;
    Spinner locationvisi, proffesionvisi;

    private LocationManager locationManager;
    private int REQUEST_LOCATION = 1;

    TextView tepro;

    Context mContext;
    DatePicker datePicker;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernamevisi = findViewById(R.id.textInputLayoutUsername);
        mobilenumbervisi = findViewById(R.id.textinputnumber);
        proffesionvisi = findViewById(R.id.textinputProfession);
        locationvisi = (Spinner) findViewById(R.id.textinputLocation);
        genderselectvisi = findViewById(R.id.genderselect);
        gendertext = findViewById(R.id.gendertext);
        tepro = findViewById(R.id.professionText);
        dab = findViewById(R.id.dab);

//        otp
        textinputOTP = findViewById(R.id.textinputOtp);
        textOtp = findViewById(R.id.getotp);

        final ConnectionDectector cd = new ConnectionDectector(this);
        if (!(cd.isConnected())) {
//            Toast.makeText(SignUp.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }





        phpImage = findViewById(R.id.profileImagephp);
        select = findViewById(R.id.choose);
        upload_php = findViewById(R.id.upload);
        cs = findViewById(R.id.signmain);
        patext = findViewById(R.id.textInputLayoutPassword);

        getTheUserPermission();


        SharedPreferences sharedPreferences;
        sharedPreferences = this.getSharedPreferences("autoLogin", Context.MODE_PRIVATE);

        if (global.logincheck == 2) {
            try {
                final String fb_full = sharedPreferences.getString("facebook_fullname", "");
                final String fb_user = sharedPreferences.getString("facebook_username", "");
                final String fb_email = sharedPreferences.getString("facebook_email", "");
                final String fb_image = sharedPreferences.getString("facebook_image", "");
                pushemail = fb_email;
                pushusername = fb_full;
                pushImage = fb_image;
            } catch (Exception e) {
                Log.e("ThrowException ", String.valueOf(e));
            }

        }


        if (global.logincheck == 1) {
            try {
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(SignUp.this);
                String personName = acct.getDisplayName();
                String personEmail = acct.getEmail();
                String imagegoogle = String.valueOf(acct.getPhotoUrl());
                pushemail = personEmail;
                pushusername = personName;
                pushImage = imagegoogle;
            } catch (Exception e) {
                Log.e("ThrowException ", String.valueOf(e));
            }

        }

        datePicker = (findViewById(R.id.date_picker));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -12);
        datePicker.setMaxDate(calendar.getTimeInMillis());
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String currentDate = (monthOfYear+1) + "/" + dayOfMonth + "/" + year;
                dateofbirth = currentDate;
            }
        });


        tvw = (TextView) findViewById(R.id.textView1);
        eText = (EditText) findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR) - 12;
                // date picker dialog


                picker = new DatePickerDialog(SignUp.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                dateofbirth = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                            }
                        }, year, month, day);

                picker.getDatePicker().setSpinnersShown(true);
                picker.getDatePicker().setCalendarViewShown(false);
                picker.getDatePicker().setMaxDate((long) (System.currentTimeMillis() - 3.784e+11));
                picker.show();

            }
        });
        btnGet = (Button) findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvw.setText("Selected Date: " + eText.getText());
            }
        });


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        SignUp.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST

                );
            }
        });

        upload_php.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), "Error : " + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        String imageData = imageToString(bitmap);
                        params.put("image", imageData);

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);
                requestQueue.add(stringRequest);
            }
        });


        textInputEditTextFullName = findViewById(R.id.fullname);
        textInputEditTextUsername = findViewById(R.id.username);
        TextInputEditTextPassowrd = findViewById(R.id.password);
        TextInputEditTextEmail = findViewById(R.id.email);
        textInputLocation = findViewById(R.id.locationin);
        textInputNumber = findViewById(R.id.number);
        textInputProffesion = findViewById(R.id.profession);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        other = findViewById(R.id.other);

        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);


        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.nothing);
                finish();
            }
        });

        locationvisi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                location = locationvisi.getSelectedItem().toString();

//                Toast.makeText(getBaseContext(), locationvisi.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        proffesionvisi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                profession = proffesionvisi.getSelectedItem().toString();
//                Toast.makeText(getBaseContext(), proffesionvisi.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


//                    View view = SignUp.this.getCurrentFocus();
//                    if (view != null) {
//                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                    }


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                fullName = String.valueOf(textInputEditTextFullName.getText());
                userName = String.valueOf(textInputEditTextUsername.getText());
                number = String.valueOf(textInputNumber.getText());


                if (nextint == 0) {
                    if (!userName.equals("") && !number.equals("")) {
                        if (number.length() == 10) {
                            checkUsername(userName);
                        } else {
                            Toast.makeText(SignUp.this, "Please Input proper number", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(SignUp.this, "Please Input all fields", Toast.LENGTH_LONG).show();
                    }
                } else if (nextint == 1) {
                    if (!dateofbirth.equals("")) {
                        nextint = 2;
                        genderselectvisi.setVisibility(View.GONE);
                        gendertext.setVisibility(View.GONE);
                        eText.setVisibility(View.GONE);
                        datePicker.setVisibility(View.GONE);
                        dab.setVisibility(View.GONE);
                        locationvisi.setVisibility(View.GONE);
                        tepro.setVisibility(View.VISIBLE);
                        proffesionvisi.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(SignUp.this, "Please Select your birthdate", Toast.LENGTH_LONG).show();
                    }
                } else if (nextint == 2) {
                    fullName = String.valueOf(textInputEditTextFullName.getText());
                    userName = String.valueOf(textInputEditTextUsername.getText());
                    password = String.valueOf(TextInputEditTextPassowrd.getText());
                    email = String.valueOf(TextInputEditTextEmail.getText());

                    number = String.valueOf(textInputNumber.getText());


                    if (!profession.equals("") && !usergender.equals("")) {

                        progressBar.setVisibility(View.VISIBLE);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                StringRequest request = new StringRequest(Request.Method.POST,
                                        global.BASE_URL + global.newSignup, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("File Uploaded Successfully")) {
                                            progressBar.setVisibility(View.GONE);
//                                            Toast.makeText(SignUp.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(SignUp.this, Tutorial.class);
                                            startActivity(i);
//                                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                                        } else {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(SignUp.this, "Username Already in use.please try another", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> map = new HashMap<String, String>();
                                        map.put("fullname", pushusername);
                                        map.put("username", userName);
                                        map.put("email", pushemail);
                                        map.put("password", password);
                                        map.put("location", global.userLoc);
                                        map.put("gender", usergender);
                                        map.put("number", number);
                                        map.put("profession", profession);
                                        map.put("dob", dateofbirth);
                                        map.put("image", pushImage);
                                        return map;
                                    }
                                };
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                queue.add(request);
                            }

                        }, 1000);

                    } else {
                        Toast.makeText(SignUp.this, "Please Input all fields", Toast.LENGTH_LONG).show();
                    }

                }
            }

        });

    }

    private void checkUsername(final String userdatabase) {

        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                StringRequest request = new StringRequest(Request.Method.POST,
                        global.BASE_URL + global.checkUsername, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        if (response.equals("exist")) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "username already exist", Toast.LENGTH_LONG).show();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            proffesionvisi.setVisibility(View.GONE);
                            nextint = 1;
                            usernamevisi.setVisibility(View.GONE);
                            mobilenumbervisi.setVisibility(View.GONE);
                            genderselectvisi.setVisibility(View.VISIBLE);
                            gendertext.setVisibility(View.VISIBLE);
                            eText.setVisibility(View.GONE);
                            datePicker.setVisibility(View.VISIBLE);
                            dab.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("username", userdatabase);
                        return map;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(request);
            }

        }, 1000);

    }


    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.male:
                if (checked)
                    usergender = "Male";
                break;
            case R.id.female:
                if (checked)
                    usergender = "female";
                break;
            case R.id.other:
                if (checked)
                    usergender = "other";
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CODE_GALLERY_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), CODE_GALLERY_REQUEST);
            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data != null) {

            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                phpImage.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        String encodedImages = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImages;
    }


    @Override
    public void onBackPressed() {
        if (nextint == 0) {
            super.onBackPressed();
        } else if (nextint == 1) {
            proffesionvisi.setVisibility(View.GONE);
            nextint = 0;
            usernamevisi.setVisibility(View.VISIBLE);
            mobilenumbervisi.setVisibility(View.VISIBLE);
            genderselectvisi.setVisibility(View.GONE);
            gendertext.setVisibility(View.GONE);
            eText.setVisibility(View.GONE);
            datePicker.setVisibility(View.GONE);
            dab.setVisibility(View.GONE);
        } else if (nextint == 2) {
            nextint = 1;
            genderselectvisi.setVisibility(View.VISIBLE);
            gendertext.setVisibility(View.VISIBLE);
            eText.setVisibility(View.GONE);
            datePicker.setVisibility(View.VISIBLE);
            dab.setVisibility(View.VISIBLE);
            locationvisi.setVisibility(View.GONE);
            proffesionvisi.setVisibility(View.GONE);
        }


    }

    private void getTheUserPermission() {
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationGetter locationGetter = new LocationGetter(SignUp.this, REQUEST_LOCATION, locationManager);


        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            locationGetter.OnGPS();
        } else {

            locationGetter.getLocation();
        }
    }


}