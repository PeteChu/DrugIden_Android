package com.example.drugiden.adapter

import android.graphics.Color
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.drugiden.R
import com.example.drugiden.dao.DrugImageItem
import com.example.drugiden.fragment.PreviewImageFragment
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * Created by petechu on 4/12/2560.
 */

class PreviewImagesAdapter(imagesList: List<DrugImageItem>,
                           imagesListSize: Int,
                           fragmentManager: FragmentManager,
                           zoomAble: Boolean) : PagerAdapter() {

    var imagesList: List<DrugImageItem>
    var imagesListSize: Int = 0
    var zoomAble: Boolean = false
    var fragmentManager: FragmentManager? = null

    init {
        this.imagesList = imagesList
        this.imagesListSize = imagesListSize
        this.zoomAble = zoomAble
        this.fragmentManager = fragmentManager
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val frameLayout = FrameLayout(container.context)
        frameLayout.setBackgroundColor(Color.parseColor("#000000"))


        var imageView = ImageView(frameLayout.context)

        if (zoomAble) {
            imageView = PhotoView(frameLayout.context)
        }

        imageView.setOnClickListener({
            fragmentManager!!.beginTransaction()
                    .replace(R.id.mainActivity_content_container, PreviewImageFragment.newInstance(imagesList[position].path!!))
                    .addToBackStack(null)
                    .commit()
        })

        Picasso.with(container.context)
                .load("http://www.phar.ubu.ac.th/drugiden/" + imagesList[position].path)
                .error(R.drawable.ic_placeholder)
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