package com.example.drugiden.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.drugiden.R
import com.example.drugiden.dao.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_med_detail.view.*

/**
 * Created by schecterza on 10/27/2017.
 */
class MedDetailFragment : Fragment() {

  lateinit var medsImage: ImageView
  lateinit var mMedDetail: ResultsItem
  lateinit var mToolbar: Toolbar

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val rootView = inflater!!.inflate(R.layout.fragment_med_detail, container, false)
    initInstances(rootView)
    return rootView
  }

  override fun onCreate(savedInstanceState: Bundle?) {

    mMedDetail = arguments!!.getSerializable("medDetail") as ResultsItem

    super.onCreate(savedInstanceState)
  }

  fun initInstances(rootView: View) {

    mToolbar = rootView.toolbar_med_detail
    (activity as AppCompatActivity).setSupportActionBar(mToolbar)

    Picasso.with(context).load("http://www.phar.ubu.ac.th/drugiden/" + mMedDetail.dimgpath)
      .into(rootView.imageView_medicine_image_detail)

    rootView.textView_med_tradeName_th.text = mMedDetail.tradenamename
    rootView.textView_med_tradeName_eng.text = mMedDetail.tradenamename
    rootView.textView_med_regNumber.text = mMedDetail.regno
    rootView.textView_med_licenseName.text = mMedDetail.licenseename
    rootView.textView_med_distributor.text = mMedDetail.distributorname
    rootView.textView_med_manufacturer.text = mMedDetail.manufacturername

    medsImage = rootView.imageView_medicine_image_detail
    medsImage.setOnClickListener({ view ->
      when (view) {
        view -> {
          fragmentManager!!.beginTransaction()
            .replace(R.id.mainActivity_content_container, PreviewImageFragment.newInstance(mMedDetail.id!!))
            .addToBackStack(null)
            .commit()
        }
      }
    })


  }

  companion object {
    fun newInstance(medDetail: ResultsItem): MedDetailFragment {
      val fragment = MedDetailFragment()
      var args = Bundle()
      args.putSerializable("medDetail", medDetail)
      fragment.arguments = args
      return fragment
    }
  }
}