package com.example.drugiden.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.afollestad.materialdialogs.GravityEnum
import com.afollestad.materialdialogs.MaterialDialog
import com.example.drugiden.R
import com.example.drugiden.dao.QuickSearchMedResponse
import com.example.drugiden.manager.HttpManager
import kotlinx.android.synthetic.main.fragment_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by schecterza on 10/27/2017.
 */

class MainFragment : Fragment(), View.OnClickListener {

    lateinit var imageViewLogo: ImageView
    lateinit var editTextSearch: EditText
    lateinit var btnQuickSearch: Button
    lateinit var btnAdvanceSearch: Button

    lateinit var listMedResponse: QuickSearchMedResponse

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
        initInstances(rootView)
        return rootView
    }

    private fun initInstances(rootView: View) {

        imageViewLogo = rootView.imageView_app_logo
        editTextSearch = rootView.editText_search
        btnQuickSearch = rootView.btn_quick_search
        btnAdvanceSearch = rootView.btn_advance_search

        btnQuickSearch.setOnClickListener(this)
        btnAdvanceSearch.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {

        when (p0!!) {
            btnQuickSearch -> {
                onClickQuickSearch()
            }
            btnAdvanceSearch -> {
                onCLickAdvanceSearch()
            }
        }
    }

    private fun onClickQuickSearch() {

        if (!isEditTextEmpty(editTextSearch)) {
            var call = HttpManager.getInstance().getService().quickSearch(editTextSearch.text.toString())
            call.enqueue(object : Callback<QuickSearchMedResponse> {

                override fun onResponse(call: Call<QuickSearchMedResponse>?, response: Response<QuickSearchMedResponse>?) {
                    if (response!!.isSuccessful) {

                        listMedResponse = response.body()!!

                        if (listMedResponse.result!!.isNotEmpty()) {
                            fragmentManager!!.beginTransaction()
                                    .replace(R.id.mainActivity_content_container, MedSearchListFragment.newInstance(listMedResponse))
                                    .addToBackStack(null)
                                    .commit()
                        } else {
                            MaterialDialog.Builder(context!!)
                                    .content("-ไม่พบข้อมูลตามที่ร้องขอ-")
                                    .contentGravity(GravityEnum.CENTER)
                                    .positiveText("ตกลง")
                                    .show()
                        }

                    }
                }

                override fun onFailure(call: Call<QuickSearchMedResponse>?, t: Throwable?) {
                    Toast.makeText(context, t!!.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            editTextSearch.error = "Error"
        }

    }

    private fun onCLickAdvanceSearch() {

    }

    private fun isEditTextEmpty(editText: EditText) : Boolean {
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