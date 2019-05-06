package ex.devs.exbooks.Screens.BookAddingScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ex.devs.exbooks.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AutoCompleteFragment extends Fragment {


    public AutoCompleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auto_complete, container, false);
    }


}
