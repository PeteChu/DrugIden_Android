package com.example.drugiden.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.drugiden.R
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * Created by schecterza on 10/27/2017.
 */

class MainFragment : Fragment(), View.OnClickListener {

    lateinit var imageViewLogo: ImageView
    lateinit var editTextSearch: EditText
    lateinit var btnQuickSearch: Button
    lateinit var btnAdvanceSearch: Button

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
        initInstances(rootView)
        return rootView
    }

    private fun initInstances(rootView: View) {

        imageViewLogo = rootView.imageView_app_logo
        editTextSearch = rootView.editText_search
        btnQuickSearch = rootView.btn_quick_search
        btnAdvanceSearch = rootView.btn_advance_search

        btnQuickSearch.setOnClickListener(this)
        btnAdvanceSearch.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {

        when (p0!!) {
            btnQuickSearch -> {
                onClickQuickSearch()
            }
            btnAdvanceSearch -> {
                onCLickAdvanceSearch()
            }
        }
    }

    private fun onClickQuickSearch() {
        fragmentManager.beginTransaction()
                .replace(R.id.mainActivity_content_container, MedSearchListFragment.newInstance())
                .addToBackStack(null)
                .commit()
    }

    private fun onCLickAdvanceSearch() {

    }

    companion object {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}