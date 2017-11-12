package com.example.drugiden.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drugiden.R
import com.example.drugiden.dao.QuickSearchMedResponse
import com.example.drugiden.dao.ResultItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_item_search.view.*

/**
 * Created by schecterza on 10/28/2017.
 */

class SearchItemListAdapter(listMedResponse: QuickSearchMedResponse) : RecyclerView.Adapter<SearchItemListAdapter.ViewHolder>() {

    var mListMedResponse = listMedResponse.result

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.custom_item_search, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mListMedResponse!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.onBindData(mListMedResponse, position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBindData( listData: List<ResultItem>?,position: Int) {
            var data = listData!![position]

            if (!data.imgPath.isNullOrEmpty()){
                Picasso.with(itemView.context)
                        .load("http://www.phar.ubu.ac.th/drugiden/"+data.imgPath)
                        .into(itemView.imageView_medicine_photo)
            }

            itemView.textView_med_name.text = data.tradeName
            itemView.textView_med_regNumber.text = data.regNumber
        }

    }

}