package com.example.drugiden.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drugiden.R

/**
 * Created by schecterza on 10/28/2017.
 */

class SearchItemListAdapter : RecyclerView.Adapter<SearchItemListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.custom_item_search, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.onBindData(position)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBindData(position: Int) {

        }
    }

}