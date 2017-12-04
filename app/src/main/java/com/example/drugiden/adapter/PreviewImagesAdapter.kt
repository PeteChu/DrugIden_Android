package com.example.drugiden.adapter

import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.drugiden.dao.ImageListItem
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso

/**
 * Created by petechu on 4/12/2560.
 */

class PreviewImagesAdapter(imagesList: List<ImageListItem>, imagesListSize: Int) : PagerAdapter() {

  var imagesList: List<ImageListItem>
  var imagesListSize: Int = 0

  init {
    this.imagesList = imagesList
    this.imagesListSize = imagesListSize
  }

  override fun instantiateItem(container: ViewGroup, position: Int): Any {

    val frameLayout = FrameLayout(container.context)
    frameLayout.setBackgroundColor(Color.parseColor("#000000"))

    val imageView = PhotoView(frameLayout.context)

    Picasso.with(container.context)
      .load("http://www.phar.ubu.ac.th/drugiden/" + imagesList[position].path)
      .into(imageView)

    frameLayout.addView(imageView)

    container.addView(frameLayout, 0)
    return frameLayout

  }

  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    return container.removeView(`object` as View)
  }

  override fun isViewFromObject(view: View, `object`: Any): Boolean {
    return view == `object`
  }

  override fun getCount(): Int {
    return imagesListSize
  }
}