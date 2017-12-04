package com.example.drugiden.fragment

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.drugiden.R
import com.example.drugiden.adapter.PreviewImagesAdapter
import com.example.drugiden.dao.ImagesResponse
import com.example.drugiden.manager.HttpManager
import com.viewpagerindicator.CirclePageIndicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.fragment_preview_image.view.*

/**
 * Created by petechu on 4/12/2560.
 */

class PreviewImageFragment: Fragment(){

  lateinit var mViewPager: ViewPager
  lateinit var mPreviewImagesAdapter: PreviewImagesAdapter
  lateinit var mViewPagerIndicator: CirclePageIndicator
  lateinit var medicineId: String
  lateinit var mImagesResponse: ImagesResponse

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.fragment_preview_image, container, false)
    initInstances(rootView)
    return rootView
  }

  override fun onCreate(savedInstanceState: Bundle?) {

    medicineId = arguments!!.getString("medicineId")
    super.onCreate(savedInstanceState)
  }


  fun initInstances(rootView: View) {

    loadImage()

    mViewPager = rootView.viewPager_preview_image
    mViewPagerIndicator = rootView.page_indicator

  }



  fun loadImage() {
    var call = HttpManager.getInstance().getService().getDimages(medicineId)
    call.enqueue(object : Callback<ImagesResponse> {
      override fun onResponse(call: Call<ImagesResponse>?, response: Response<ImagesResponse>) {

        mImagesResponse = response.body()!!

        mPreviewImagesAdapter = PreviewImagesAdapter(mImagesResponse.imageList!!, mImagesResponse.imageList!!.size)
        mViewPager.adapter = mPreviewImagesAdapter

        mViewPagerIndicator.setViewPager(mViewPager)


//        Toast.makeText(context, response.body()!!.imageList!!.size.toString(), Toast.LENGTH_SHORT).show()


      }

      override fun onFailure(call: Call<ImagesResponse>?, t: Throwable?) {
        Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()
      }
    })
  }


  companion object {
    fun newInstance(medicineId: Int): Fragment {
      var fragment = PreviewImageFragment()
      var args = Bundle()
      args.putString("medicineId", medicineId.toString())
      fragment.arguments = args
      return fragment
    }
  }

}