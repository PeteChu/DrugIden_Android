package com.example.drugiden.adapter

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drugiden.R
import com.example.drugiden.dao.DrugSearchList
import com.example.drugiden.dao.DrugSearchItem
import com.example.drugiden.fragment.MedDetailFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_item_search.view.*

/**
 * Created by schecterza on 10/28/2017.
 */

class SearchItemListAdapter(listDrugResponse: DrugSearchList, fragmentManager: FragmentManager) : RecyclerView.Adapter<SearchItemListAdapter.ViewHolder>() {

    var mListMedResponse = listDrugResponse.results
    var mFragmentManager = fragmentManager

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.custom_item_search, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mListMedResponse!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.onBindData(mListMedResponse, position, mFragmentManager)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBindData(listData: List<DrugSearchItem>?, position: Int, fragmentManager: FragmentManager) {
            var data = listData!![position]

            if (!data.dimgpath.isNullOrEmpty()) {
                Picasso.with(itemView.context)
                        .load("http://www.phar.ubu.ac.th/drugiden/" + data.dimgpath)
                        .error(R.drawable.ic_placeholder)
                        .into(itemView.imageView_medicine_photo)
            }

            itemView.textView_med_name.text = data.tradenamename
            itemView.textView_med_regNumber.text = data.regno
            itemView.textView_med_manufacturer.text = data.manufacturername
            itemView.textView_med_license.text = data.licenseename
            itemView.textView_med_distributor.text = data.distributorname

            itemView.setOnClickListener { onClick(itemView.context, data, fragmentManager) }
        }

        fun onClick(c: Context, data: DrugSearchItem, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                    .replace(R.id.mainActivity_content_container, MedDetailFragment.newInstance(data))
                    .addToBackStack(null)
                    .commit()
        }

    }

}