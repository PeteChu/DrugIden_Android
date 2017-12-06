package com.example.drugiden.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.drugiden.R
import com.example.drugiden.adapter.PreviewImagesAdapter
import com.example.drugiden.dao.DrugImageList
import com.squareup.picasso.Picasso
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.fragment_preview_image.view.*

/**
 * Created by petechu on 4/12/2560.
 */

class PreviewImageFragment : Fragment() {

  lateinit var mImageView: ImageView
  lateinit var url: String

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.fragment_preview_image, container, false)
    initInstances(rootView)
    return rootView
  }

  override fun onCreate(savedInstanceState: Bundle?) {

    url = arguments!!.getString("url")
    super.onCreate(savedInstanceState)
  }


  fun initInstances(rootView: View) {

    mImageView = rootView.preview_image

    Picasso.with(context)
      .load("http://www.phar.ubu.ac.th/drugiden/" + url)
      .into(mImageView)

  }


  companion object {
    fun newInstance(url: String): Fragment {
      var fragment = PreviewImageFragment()
      var args = Bundle()
      args.putString("url", url)
      fragment.arguments = args
      return fragment
    }
  }

}