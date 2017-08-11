package sdu.phewma.nattha.wheresdu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import sdu.phewma.nattha.wheresdu.GetAllUser;
import sdu.phewma.nattha.wheresdu.MyAlert;
import sdu.phewma.nattha.wheresdu.R;
import sdu.phewma.nattha.wheresdu.Service;

/**
 * Created by Nattha on 7/6/2017.
 */

public class MainFragment extends Fragment{

    private EditText userEditText, passwordEditText;
    public static MainFragment mainInstance() {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.main_fragment_layout, container, false);


        return view;
    } // onCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Register Controller
        registerController();


        //Login Controller

        loginController();

    }  // onActivityCreated

    private void loginController() {
        //Initial View
         userEditText = (EditText) getView().findViewById(R.id.edtUser);
         passwordEditText = (EditText) getView().findViewById(R.id.edtPassword);
         Button button = (Button) getView().findViewById(R.id.btnLogin);

        // Check Space
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String strUser = userEditText.getText().toString().trim();
               String strPassword = passwordEditText.getText().toString().trim();

               if (strUser.equals("") || strPassword.equals("")){
                   MyAlert myAlert = new MyAlert(getActivity());
                   myAlert.myDialog(getResources().getString(R.string.title_have),
                           getResources().getString(R.string.message));
               }else{

                   checkUserAndPass(strUser, strPassword);

               }
           }
       });


    } //loginController

    private void checkUserAndPass(String strUser, String strPassword) {

        String tag = "7JultV1";

        try {

            GetAllUser getAllUser = new GetAllUser(getActivity());
            getAllUser.execute();

            String strJSON = getAllUser.get();
            Log.d(tag, "JSON ==>" + strJSON);
            boolean status = true; // true==>User False
            String strId = null;
            String strName = null;
            String strPasswordTrue = null;



            JSONArray jsonArray = new JSONArray(strJSON);
            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (strUser.equals(jsonObject.getString("User"))) {
                    status = false; // User ==> True
                    strId = jsonObject.getString("id");
                    strName = jsonObject.getString("Name");
                    strPasswordTrue = jsonObject.getString("Password");
                } // if

            }  //for
            if (status) {
                // False
                MyAlert myAlert = new MyAlert(getActivity());
                myAlert.myDialog("User False", "No this User in my Database");
            } else if (strPassword.equals(strPasswordTrue)) {
                //Password True
                Toast.makeText(getActivity(), "Welcome" + strName, Toast.LENGTH_SHORT).show();

                //Intent To Service
                Intent intent = new Intent(getActivity(), Service.class);
                intent.putExtra("ID", strId);
                getActivity().startActivity(intent);


            }else {
                //Password False
                MyAlert myAlert = new MyAlert(getActivity());
                myAlert.myDialog("Password False", "Please Try again Password False");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // CheckUser

    private void registerController() {
        TextView textView = (TextView) getView().findViewById(R.id.txtNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContent, RegisterFragment.registerInstance()).commit();
            }
        });
    }

}   // Main  Class นี่คือ คลาสหลัก
