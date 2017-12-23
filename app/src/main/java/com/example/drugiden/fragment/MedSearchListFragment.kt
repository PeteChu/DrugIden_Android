package com.example.drugiden.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.example.drugiden.R
import com.example.drugiden.adapter.SearchItemListAdapter
import com.example.drugiden.dao.DrugSearchList
import kotlinx.android.synthetic.main.fragment_med_search_list.view.*

/**
 * Created by schecterza on 10/27/2017.
 */

class MedSearchListFragment : Fragment() {

    lateinit var mToolbar: Toolbar
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    lateinit var mRecyclerDivider: DividerItemDecoration

    lateinit var listDrugResponse: DrugSearchList

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_med_search_list, container, false)
        initInstances(rootView)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        var args = arguments
        listDrugResponse = args!!.getSerializable("listDrugResponse") as DrugSearchList

        super.onCreate(savedInstanceState)
    }


    private fun initInstances(rootView: View) {

        mToolbar = rootView.toolbar_med_searchList
        var actionbar = (activity as AppCompatActivity)
        mToolbar.setTitle("ผลการค้นหา")
        mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
        actionbar.setSupportActionBar(mToolbar)

        mRecyclerView = rootView.recyclerView_med_search_list
        mRecyclerView.setHasFixedSize(true)
        mAdapter = SearchItemListAdapter(listDrugResponse, fragmentManager!!)
        mLayoutManager = LinearLayoutManager(context)
        mRecyclerDivider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(mRecyclerDivider)
    }


    companion object {
        fun newInstance(listDrugResponse: DrugSearchList): MedSearchListFragment {
            val fragment = MedSearchListFragment()
            var args = Bundle()
            args.putSerializable("listDrugResponse", listDrugResponse)
            fragment.arguments = args
            return fragment
        }
    }
}