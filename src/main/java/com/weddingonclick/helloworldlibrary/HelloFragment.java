package com.weddingonclick.helloworldlibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.weddingonclick.helloworldlibrary.HelloFragment.OnArgJsonClickListener} interface
 * to handle interaction events.
 * Use the {@link HelloFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelloFragment extends Fragment {

    private static final String ARG_JSON = "json";

    private String argJson;
    private OnArgJsonClickListener argJsonClickListener;


    public HelloFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param argJson Parameter 1.
     * @return A new instance of fragment HelloFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HelloFragment newInstance(String argJson) {
        HelloFragment fragment = new HelloFragment();
        Bundle args = new Bundle();
        args.putString(ARG_JSON, argJson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            argJson = getArguments().getString(ARG_JSON);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hello, container, false);
        TextView argJsonTextView = (TextView) rootView.findViewById(R.id.json_text_view);
        argJsonTextView.setText(argJson);

        argJsonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject responseJson = new JSONObject();
                try {
                    responseJson.put("first", "hello");
                    responseJson.put("last", "activity");
                    argJsonClickListener.OnClick(v, responseJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnArgJsonClickListener) {
            argJsonClickListener = (OnArgJsonClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnArgJsonClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        argJsonClickListener = null;
    }

    public interface OnArgJsonClickListener {
        void OnClick(View view, JSONObject responseJson);
    }
}
