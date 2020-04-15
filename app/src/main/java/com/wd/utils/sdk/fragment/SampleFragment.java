package com.wd.utils.sdk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wd.utils.sdk.R;
import com.wd.utils.sdk.basecontrols.BaseFragment;

import sdk.utils.wd.network.NetworkListener;
import sdk.utils.wd.network.NetworkUtility;

public class SampleFragment extends BaseFragment implements NetworkListener {

    View view;
    Button statusButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sample, container, false);
        //registering a callback to be notified in this fragment whenever there is a change in network status
        NetworkUtility.getInstance().registerCallback(this);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        statusButton = (Button) view.findViewById(R.id.statusButton);
        if (NetworkUtility.getInstance().isNetworkAvailable(getContext())) {
            statusButton.setText("Online");
        } else {
            statusButton.setText("Offline");
        }
        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusButton != null) {
                    Toast.makeText(getActivity(), statusButton.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onInternetReceive() {
        if (statusButton != null) {
            statusButton.setText("Online");
        }
    }

    @Override
    public void onInternetGone() {
        if (statusButton != null) {
            statusButton.setText("Offline");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //unregister a callback to when this fragment destroys to prevent memory overloading
        NetworkUtility.getInstance().unregisterCallback(this);
    }
}
