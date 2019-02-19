package com.chooloo.www.callmanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chooloo.www.callmanager.R;
import com.chooloo.www.callmanager.adapter.ContactAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CGroupDetailsFragment extends Fragment {

    private CGroupDetailsViewModel mViewModel;

    private View mRootView;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private ContactAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_cgroup_details, container, false);
        ButterKnife.bind(this, mRootView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mAdapter = new ContactAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();
        if (args == null || !args.containsKey(getString(R.string.arg_list_id))) {
            throw new IllegalArgumentException("Must have a list id argument passed to the fragment");
        }

        long listId = args.getLong(getString(R.string.arg_list_id));
        mViewModel = ViewModelProviders.of(this).get(CGroupDetailsViewModel.class);
        mViewModel.setListId(listId);
        mViewModel.getContacts().observe(this, contacts -> {
            mAdapter.setData(contacts);
        });
    }

    @OnClick(R.id.call_cgroup)
    public void callCGroup(View v) {
        //TODO implement
    }
}