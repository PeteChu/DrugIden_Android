package com.example.drugiden.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.afollestad.materialdialogs.GravityEnum
import com.afollestad.materialdialogs.MaterialDialog
import com.example.drugiden.R
import com.example.drugiden.manager.HttpManager
import com.rengwuxian.materialedittext.MaterialEditText
import kotlinx.android.synthetic.main.fragment_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.animation.Animation
import android.view.animation.Transformation
import com.example.drugiden.dao.MedSearchResult


/**
 * Created by schecterza on 10/27/2017.
 */

class MainFragment : Fragment(), View.OnClickListener {

  lateinit var mToolbar: Toolbar
  lateinit var imageViewLogo: ImageView
  lateinit var editTextSearch: MaterialEditText
  lateinit var editTextAdvOptionColor: MaterialEditText
  lateinit var btnSearch: Button
  lateinit var switchAdvSearch: Switch
  lateinit var optionsAdvSearch: LinearLayout

  lateinit var listMedResponse: MedSearchResult

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
    initInstances(rootView)
    return rootView
  }

  private fun initInstances(rootView: View) {

    mToolbar = rootView.toolbar_med_main
    imageViewLogo = rootView.imageView_app_logo
    editTextSearch = rootView.editText_search
    editTextAdvOptionColor = rootView.editText_advOption_color
    btnSearch = rootView.btn_quick_search
    switchAdvSearch = rootView.switch_advanceSearch
    optionsAdvSearch = rootView.view_advanceSearch

    var actionbar = (activity as AppCompatActivity)
    actionbar.setSupportActionBar(mToolbar)

    if (!switchAdvSearch.isChecked) {
      collapse(optionsAdvSearch)
    }


    btnSearch.setOnClickListener(this)
    switchAdvSearch.setOnClickListener(this)

    editTextAdvOptionColor.setOnClickListener(this)


  }

  override fun onClick(p0: View?) {

    when (p0!!) {
      btnSearch -> {
        if (!switchAdvSearch.isChecked) {
          onClickQuickSearch()
        } else {
          onClickAdvanceSearch()
        }
      }
      switchAdvSearch -> {
        onCheckSwitchAdvSearch()
      }
      editTextAdvOptionColor -> {
        onClickEditTextAdvOptionColor()
      }
    }
  }

  private fun onClickQuickSearch() {

    if (!isEditTextEmpty(editTextSearch)) {
      var call = HttpManager.getInstance().getService().quickSearch(editTextSearch.text.toString())
      call.enqueue(object : Callback<MedSearchResult> {

        override fun onResponse(call: Call<MedSearchResult>?, response: Response<MedSearchResult>?) {
          if (response!!.isSuccessful) {

            listMedResponse = response.body()!!

            if (listMedResponse.results!!.isNotEmpty()) {
              fragmentManager!!.beginTransaction()
                .replace(R.id.mainActivity_content_container, MedSearchListFragment.newInstance(listMedResponse))
                .addToBackStack(null)
                .commit()
            } else {
              MaterialDialog.Builder(context!!)
                .title("ผลการค้นหา")
                .content("-ไม่พบข้อมูลตามที่ร้องขอ-")
                .contentGravity(GravityEnum.CENTER)
                .positiveText("ตกลง")
                .show()
            }

          }
        }

        override fun onFailure(call: Call<MedSearchResult>?, t: Throwable?) {
          MaterialDialog.Builder(context!!)
            .title("ผลการค้นหา")
            .content("-กรุณาลองอีกครั้ง-")
            .contentGravity(GravityEnum.CENTER)
            .positiveText("ตกลง")
            .show()
        }
      })
    } else {
      editTextSearch.error = "Error"
    }

  }

  private fun onClickAdvanceSearch() {

  }

  private fun onCheckSwitchAdvSearch() {
    if (switchAdvSearch.isChecked) {
      expand(optionsAdvSearch)
    } else {
      collapse(optionsAdvSearch)
    }
  }

  private fun onClickEditTextAdvOptionColor() {
    MaterialDialog.Builder(context!!)
      .title("ลักษณะสี")
      .items(R.array.Color)
      .itemsCallback({ dialog, itemView, position, text ->
        editTextAdvOptionColor.setText(text)
      })
      .show()
  }

  fun expand(v: View) {
    v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    val targetHeight = v.measuredHeight

    // Older versions of android (pre API 21) cancel animations for views with a height of 0.
    v.layoutParams.height = 1
    v.visibility = View.VISIBLE
    val a = object : Animation() {
      override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        v.layoutParams.height = if (interpolatedTime == 1f)
          LinearLayout.LayoutParams.WRAP_CONTENT
        else
          (targetHeight * interpolatedTime).toInt()
        v.requestLayout()
      }

      override fun willChangeBounds(): Boolean {
        return true
      }
    }

    // 1dp/ms
    a.duration = (targetHeight / v.context.resources.displayMetrics.density).toInt().toLong()
    v.startAnimation(a)
  }

  fun collapse(v: View) {
    val initialHeight = v.measuredHeight

    val a = object : Animation() {
      override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        if (interpolatedTime == 1f) {
          v.visibility = View.GONE
        } else {
          v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
          v.requestLayout()
        }
      }

      override fun willChangeBounds(): Boolean {
        return true
      }
    }

    // 1dp/ms
    a.duration = (initialHeight / v.context.resources.displayMetrics.density).toInt().toLong()
    v.startAnimation(a)
  }


  private fun isEditTextEmpty(editText: EditText): Boolean {
    var input = editText.text.toString().trim()
    return input.isEmpty()
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