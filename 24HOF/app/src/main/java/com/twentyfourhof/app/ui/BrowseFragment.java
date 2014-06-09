package com.twentyfourhof.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.inject.Inject;
import com.twentyfourhof.app.R;
import roboguice.fragment.RoboListFragment;

public class BrowseFragment extends RoboListFragment {

    @Inject
    private TextAdapter textAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_browse, container, false);
		setListAdapter(textAdapter);
		return rootView;
	}

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}