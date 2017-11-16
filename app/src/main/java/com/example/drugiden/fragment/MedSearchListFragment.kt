package com.example.drugiden.fragment

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
import android.widget.Toast
import com.example.drugiden.R
import com.example.drugiden.adapter.SearchItemListAdapter
import com.example.drugiden.dao.QuickSearchMedResponse
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

    lateinit var listMedResponse: QuickSearchMedResponse

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_med_search_list, container, false)
        initInstances(rootView)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        var args = arguments
        listMedResponse = args!!.getSerializable("listMedResponse") as QuickSearchMedResponse

        super.onCreate(savedInstanceState)
    }

    private fun initInstances(rootView: View) {

        mToolbar = rootView.toolbar_med_searchList
        var actionbar = (activity as AppCompatActivity)
        actionbar.setSupportActionBar(mToolbar)
        actionbar.title = "ผลการค้นหา"

        mRecyclerView = rootView.recyclerView_med_search_list
        mRecyclerView.setHasFixedSize(true)
        mAdapter = SearchItemListAdapter(listMedResponse, fragmentManager!!)
        mLayoutManager = LinearLayoutManager(context)
        mRecyclerDivider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(mRecyclerDivider)

        Toast.makeText(context, listMedResponse.result!!.size.toString(), Toast.LENGTH_SHORT).show()
    }


    companion object {
        fun newInstance(listMedResponse: QuickSearchMedResponse): MedSearchListFragment {
            val fragment = MedSearchListFragment()
            var args = Bundle()
            args.putSerializable("listMedResponse", listMedResponse)
            fragment.arguments = args
            return fragment
        }
    }
}