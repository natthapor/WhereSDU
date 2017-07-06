package sdu.phewma.nattha.wheresdu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sdu.phewma.nattha.wheresdu.R;

/**
 * Created by Nattha on 7/6/2017.
 */

public class RegisterFragment extends Fragment{

    public static RegisterFragment registerInstance() {
        RegisterFragment registerFragment = new RegisterFragment();
        Bundle bundle = new Bundle();
        registerFragment.setArguments(bundle);
        return registerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.register_fragment_layout, container, false);

        return view;
    }
}   //Main Class
