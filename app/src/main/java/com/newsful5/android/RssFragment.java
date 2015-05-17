package com.newsful5.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.etsy.android.grid.StaggeredGridView;


import java.util.List;

public class RssFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ProgressBar progressBar;
    private StaggeredGridView gridview;
    private View view;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();

        position=bundle.getInt("position");
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_layout, container, false);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            gridview = (StaggeredGridView) view.findViewById(R.id.gridview);
            gridview.setVisibility(View.GONE);
            gridview.setOnItemClickListener(this);
            startService();
        } else {

            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
        return view;
    }

    private void startService() {
        Intent intent = new Intent(getActivity(), RssService.class);
        Bundle bundle=new Bundle();
        bundle.putInt("position",position);
        intent.putExtra("position",bundle);
        intent.putExtra(RssService.RECEIVER, resultReceiver);
        getActivity().startService(intent);
    }

    /**
     * Once the {@link RssService} finishes its task, the result is sent to this ResultReceiver.
     */
    private final ResultReceiver resultReceiver = new ResultReceiver(new Handler()) {
        @SuppressWarnings("unchecked")
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            List<RssItem> items = (List<RssItem>) resultData.getSerializable(RssService.ITEMS);
            if (items != null) {
                RssAdapter adapter = new RssAdapter(getActivity(), items);
                gridview.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "Please select some sites from Settings or Check your Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
            progressBar.setVisibility(View.GONE);
            gridview.setVisibility(View.VISIBLE);
        };
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RssAdapter adapter = (RssAdapter) parent.getAdapter();
        RssItem item = (RssItem) adapter.getItem(position);
        Uri uri = Uri.parse(item.getLink());
        Intent intent=new Intent(getActivity(),WebViewClass.class);
        intent.putExtra("url",uri.toString());
        startActivity(intent);

    }
}