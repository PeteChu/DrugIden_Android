package com.example.drugiden.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.drugiden.R
import com.example.drugiden.adapter.PreviewImagesAdapter
import com.example.drugiden.dao.DrugImageList
import com.example.drugiden.dao.DrugSearchItem
import com.example.drugiden.manager.HttpManager
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.fragment_med_detail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by schecterza on 10/27/2017.
 */
class MedDetailFragment : Fragment() {

  lateinit var mDrugSearchDetail: DrugSearchItem
  lateinit var mToolbar: Toolbar

  lateinit var mViewPager: ViewPager
  lateinit var mPreviewImagesAdapter: PreviewImagesAdapter
  lateinit var mViewPagerIndicator: CirclePageIndicator
  lateinit var mImagesResponse: DrugImageList

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.fragment_med_detail, container, false)
    initInstances(rootView)
    return rootView
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    mDrugSearchDetail = arguments!!.getSerializable("drugSearchDetail") as DrugSearchItem
    super.onCreate(savedInstanceState)
  }


  fun initInstances(rootView: View) {

    loadImage()

    mToolbar = rootView.toolbar_med_detail
    mToolbar.title = mDrugSearchDetail.tradenamename
    (activity as AppCompatActivity).setSupportActionBar(mToolbar)

    mViewPager = rootView.detail_viewPager_preview_image
    mViewPagerIndicator = rootView.detail_page_indicator

    rootView.textView_med_tradeName_eng.text = mDrugSearchDetail.tradenamename
    rootView.textView_med_regNumber.text = mDrugSearchDetail.regno
    rootView.textView_med_manufacturer.text = mDrugSearchDetail.manufacturername
    rootView.textView_med_licenseName.text = mDrugSearchDetail.licenseename
    rootView.textView_med_distributor.text = mDrugSearchDetail.distributorname
    rootView.textView_med_size.text = mDrugSearchDetail.dsizename
    rootView.textView_med_wide.text = mDrugSearchDetail.dwide.toString() + "cm"
    rootView.textView_med_long.text = mDrugSearchDetail.dlong.toString() + "cm"
    rootView.textView_med_type.text = mDrugSearchDetail.dtypename
    rootView.textView_med_shape.text = mDrugSearchDetail.dshapename
    rootView.textView_med_group.text = mDrugSearchDetail.dgroupname
    rootView.textView_med_rType.text = mDrugSearchDetail.drtypename
    rootView.textView_med_status.text = mDrugSearchDetail.dstatusname


    if (mDrugSearchDetail.tradenamename.isNullOrBlank()) {
      rootView.textView_med_tradeName_eng.text = "-"
    }
    if (mDrugSearchDetail.regno.isNullOrBlank()) {
      rootView.textView_med_regNumber.text = "-"
    }
    if (mDrugSearchDetail.manufacturername.isNullOrBlank()) {
      rootView.textView_med_manufacturer.text = "-"
    }
    if (mDrugSearchDetail.licenseename.isNullOrBlank()) {
      rootView.textView_med_licenseName.text = "-"
    }
    if (mDrugSearchDetail.distributorname.isNullOrBlank()) {
      rootView.textView_med_distributor.text = "-"
    }
    if (mDrugSearchDetail.dsizename.isNullOrBlank()) {
      rootView.textView_med_size.text = "-"
    }
    if (mDrugSearchDetail.dwide!!.isNaN()) {
      rootView.textView_med_wide.text = "-"
    }
    if (mDrugSearchDetail.dlong!!.isNaN()) {
      rootView.textView_med_long.text = "-"
    }
    if (mDrugSearchDetail.dtypename.isNullOrBlank()) {
      rootView.textView_med_type.text = "-"
    }
    if (mDrugSearchDetail.dshapename.isNullOrBlank()) {
      rootView.textView_med_shape.text = "-"
    }
    if (mDrugSearchDetail.dgroupname.isNullOrBlank()) {
      rootView.textView_med_group.text = "-"
    }
    if (mDrugSearchDetail.drtypename.isNullOrBlank()) {
      rootView.textView_med_rType.text = "-"
    }
    if (mDrugSearchDetail.dstatusname.isNullOrBlank()) {
      rootView.textView_med_status.text = "-"
    }

  }

  fun loadImage() {
    var call = HttpManager.getInstance().getService().getDimages(mDrugSearchDetail.id.toString(), "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjExMTExIiwidXVpZCI6ImVlMmZjMjc5LWE0YTctNGYxNy04NTVjLTllMDY2ZTRkZjdmMSIsImlhdCI6MTUxMjQwMjE4NSwiZXhwIjoxNTEzMDA2OTg1fQ.ccCrMMELmVkaLRbDa7Avno3ektW_cKeL9aAcijKoEWIyooDw9f6ZN05--j-KXhuiscAcGrekuOc_VA05lmyY1Q")
    call.enqueue(object : Callback<DrugImageList> {
      override fun onResponse(call: Call<DrugImageList>?, response: Response<DrugImageList>) {

        mImagesResponse = response.body()!!

        mPreviewImagesAdapter = PreviewImagesAdapter(mImagesResponse.imageList!!, mImagesResponse.imageList!!.size, fragmentManager!!, false)
        mViewPager.adapter = mPreviewImagesAdapter

        mViewPagerIndicator.setViewPager(mViewPager)

      }

      override fun onFailure(call: Call<DrugImageList>?, t: Throwable?) {
        Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()
      }
    })
  }

  companion object {
    fun newInstance(drugSearchDetail: DrugSearchItem): MedDetailFragment {
      val fragment = MedDetailFragment()
      var args = Bundle()
      args.putSerializable("drugSearchDetail", drugSearchDetail)
      fragment.arguments = args
      return fragment
    }
  }
}