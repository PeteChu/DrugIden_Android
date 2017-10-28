package com.example.drugiden.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drugiden.R

/**
 * Created by schecterza on 10/27/2017.
 */

class MedSearchListFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_med_search_list, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

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