package com.example.drugiden.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drugiden.R
import com.example.drugiden.adapter.SearchItemListAdapter
import kotlinx.android.synthetic.main.fragment_med_search_list.view.*

/**
 * Created by schecterza on 10/27/2017.
 */

class MedSearchListFragment : Fragment() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    lateinit var mRecyclerDivider: DividerItemDecoration


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_med_search_list, container, false)
        initInstances(rootView)
        return rootView
    }

    private fun initInstances(rootView: View) {

        mRecyclerView = rootView.recyclerView_med_search_list
        mRecyclerView.setHasFixedSize(true)
        mAdapter = SearchItemListAdapter()
        mLayoutManager = LinearLayoutManager(context)
        mRecyclerDivider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(mRecyclerDivider)

    }

    companion object {
        fun newInstance(): MedSearchListFragment {
            val fragment = MedSearchListFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}