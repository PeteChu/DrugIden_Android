package com.example.drugiden.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
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
import com.example.drugiden.dao.*
import com.github.pavlospt.CircleView
import petrov.kristiyan.colorpicker.ColorPicker
import kotlin.collections.ArrayList


/**
 * Created by schecterza on 10/27/2017.
 */

class MainFragment : Fragment(), View.OnClickListener {

    lateinit var mToolbar: Toolbar
    lateinit var imageViewLogo: ImageView
    lateinit var viewOptionColor1: CircleView
    lateinit var viewOptionColor2: CircleView
    lateinit var viewOptionColor3: CircleView
    lateinit var viewOptionColor4: CircleView

    lateinit var editTextSearch: MaterialEditText
    lateinit var editTextAdvOptionDrugGroup: MaterialEditText
    lateinit var editTextAdvOptionDrugSize: MaterialEditText
    lateinit var editTextAdvOptionDrugShape: MaterialEditText
    lateinit var editTextAdvOptionDrugShapeType: MaterialEditText
    lateinit var editTextAdvOptionDrugStatus: MaterialEditText
    lateinit var editTextAdvOptionDrugType: MaterialEditText
    lateinit var editTextAdvOptionDrugRType: MaterialEditText

    lateinit var btnSearch: Button
    lateinit var switchAdvSearch: Switch
    lateinit var optionsAdvSearch: LinearLayout

    lateinit var listDrugResponse: DrugSearchList

    var mMedColor: ArrayList<String> = ArrayList()
    var mDrugGroup: ArrayList<String> = ArrayList()
    var mDrugSize: ArrayList<String> = ArrayList()
    var mDrugShape: ArrayList<String> = ArrayList()
    var mDrugShapeType: ArrayList<String> = ArrayList()
    var mDrugStatus: ArrayList<String> = ArrayList()
    var mDrugRType: ArrayList<String> = ArrayList()
    var mDrugType: ArrayList<String> = ArrayList()

    var mColor1: String = ""
    var mColor2: String = ""
    var mColor3: String = ""
    var mColor4: String = ""
    var mTradename: String = ""
    var mLicense: String = ""
    var mManufacturer: String = ""
    var mDistributor: String = ""
    var mGenericName: String = ""
    var mDWide: String = ""
    var mDLong: String = ""
    var mDGroup: String = "ไม่ระบุ"
    var mDType: String = "ไม่ระบุ"
    var mDRType: String = "ไม่ระบุ"
    var mDShape: String = "ไม่ระบุ"
    var mShapeText: String = ""
    var mShapeText2: String = ""
    var mShapeText3: String = ""
    var mShapeText4: String = ""
    var mShapeText5: String = ""
    var mShapeText6: String = ""
    var mDSize: String = "ไม่ระบุ"
    var mDStatus: String = "ไม่ระบุ"

    var mColorList = arrayListOf(
            "#ffffffff", "#ffea0000", "#fffe700e", "#ffffe400", "#ffefeddb",
            "#ffa8e26a", "#ff4a9fe0", "#ff120992", "#fffdb1ef", "#ffc977db",
            "#ffc2924a", "#ffcccccc", "#ff232222")

    var mColorMap = mapOf(
            "ffffffff" to "ขาว", "ffea0000" to "แดง", "fffe700e" to "ส้ม", "ffffe400" to "เหลือง", "ffefeddb" to "ครีม",
            "ffa8e26a" to "เขียว", "ff4a9fe0" to "ฟ้า", "ff120992" to "น้ำเงิน", "fffdb1ef" to "ชมพู", "ffc977db" to "ม่วง",
            "ffc2924a" to "น้ำตาล", "ffcccccc" to "เทา", "ff232222" to "ดำ")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        initInstances(rootView)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadAdvOptions()
        super.onCreate(savedInstanceState)
    }

    private fun initInstances(rootView: View) {

        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        mToolbar = rootView.toolbar_med_main
        imageViewLogo = rootView.imageView_app_logo
        editTextSearch = rootView.editText_search
        viewOptionColor1 = rootView.advOption_color1
        viewOptionColor2 = rootView.advOption_color2
        viewOptionColor3 = rootView.advOption_color3
        viewOptionColor4 = rootView.advOption_color4
        editTextAdvOptionDrugGroup = rootView.editText_advOption_drugGroup
        editTextAdvOptionDrugSize = rootView.editText_advOption_drugSize
        editTextAdvOptionDrugShape = rootView.editText_advOption_drugShape
        editTextAdvOptionDrugShapeType = rootView.editText_advOption_drugShapeType
        editTextAdvOptionDrugStatus = rootView.editText_advOption_drugStatus
        editTextAdvOptionDrugType = rootView.editText_advOption_drugType
        editTextAdvOptionDrugRType = rootView.editText_advOption_drugRType

        viewOptionColor1.setShowSubtitle(false)
        viewOptionColor2.setShowSubtitle(false)
        viewOptionColor3.setShowSubtitle(false)
        viewOptionColor4.setShowSubtitle(false)

        btnSearch = rootView.btn_quick_search
        switchAdvSearch = rootView.switch_advanceSearch
        optionsAdvSearch = rootView.view_advanceSearch

        switchAdvSearch.isChecked = false


        var actionbar = (activity as AppCompatActivity)
        mToolbar.setTitle(R.string.app_name)
        mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
        actionbar.setSupportActionBar(mToolbar)

        actionbar.supportActionBar!!.hide()

        if (!switchAdvSearch.isChecked) {
            collapse(optionsAdvSearch)
        }


        btnSearch.setOnClickListener(this)
        switchAdvSearch.setOnClickListener(this)
        viewOptionColor1.setOnClickListener(this)
        viewOptionColor2.setOnClickListener(this)
        viewOptionColor3.setOnClickListener(this)
        viewOptionColor4.setOnClickListener(this)
        editTextAdvOptionDrugGroup.setOnClickListener(this)
        editTextAdvOptionDrugSize.setOnClickListener(this)
        editTextAdvOptionDrugShape.setOnClickListener(this)
        editTextAdvOptionDrugShapeType.setOnClickListener(this)
        editTextAdvOptionDrugStatus.setOnClickListener(this)
        editTextAdvOptionDrugType.setOnClickListener(this)
        editTextAdvOptionDrugRType.setOnClickListener(this)


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
            viewOptionColor1 -> {
                onClickEditTextAdvOptionColor(viewOptionColor1, 1)
            }
            viewOptionColor2 -> {
                onClickEditTextAdvOptionColor(viewOptionColor2, 2)
            }
            viewOptionColor3 -> {
                onClickEditTextAdvOptionColor(viewOptionColor3, 3)
            }
            viewOptionColor4 -> {
                onClickEditTextAdvOptionColor(viewOptionColor4, 4)
            }
            editTextAdvOptionDrugGroup -> {
                onClickEditTextAdvOptionDrugGroup()
            }
            editTextAdvOptionDrugSize -> {
                onClickEditTextAdvOptionDrugSize()
            }
            editTextAdvOptionDrugShape -> {
                onClickEditTextAdvOptionDrugShape()
            }
            editTextAdvOptionDrugShapeType -> {
                onClickEditTextAdvOptionDrugShapeType()
            }
            editTextAdvOptionDrugStatus -> {
                onClickEditTextAdvOptionDrugStatus()
            }
            editTextAdvOptionDrugType -> {
                onClickEditTextAdvOptionDrugType()
            }
            editTextAdvOptionDrugRType -> {
                onClickEditTextAdvOptionDrugRType()
            }
        }
    }

    private fun onClickQuickSearch() {

        searchDrug()
    }

    private fun onClickAdvanceSearch() {

        searchDrug()
    }

    /**
     * Search Drug
     */

    private fun searchDrug() {
//        if (!isEditTextEmpty(editTextSearch)) {
//        Toast.makeText(context, editTextSearch.text.toString(), Toast.LENGTH_SHORT).show()

//        MaterialDialog.Builder(context)
//                .progressIndeterminateStyle(false)

        var call: Call<DrugSearchList>

        if (!switchAdvSearch.isChecked) {

            if (!isEditTextEmpty(editTextSearch)) {
                call = HttpManager.getInstance().getService().quickSearch(editTextSearch.text.toString(), resources.getString(R.string.token))
            } else {
                MaterialDialog.Builder(context!!)
                        .title("ผลการค้นหา")
                        .content("-ไม่พบข้อมูลตามที่ร้องขอ-")
                        .contentGravity(GravityEnum.CENTER)
                        .positiveText("ตกลง")
                        .show()
                return
            }

        } else {
            mTradename = editTextSearch.text.toString()
            call = HttpManager.getInstance().getService().advanceSearch(
                    mColor1, mColor2, mColor3, mColor4, mTradename, mManufacturer, mGenericName, mLicense, mDistributor, mDGroup,
                    mDType, mDRType, mDShape, mDWide, mDLong, mShapeText, mShapeText2, mShapeText3, mShapeText4, mShapeText5,
                    mShapeText6, mDSize, mDStatus, resources.getString(R.string.token))
        }

        call.enqueue(object : Callback<DrugSearchList> {

            override fun onResponse(call: Call<DrugSearchList>?, response: Response<DrugSearchList>?) {
                if (response!!.isSuccessful) {

                    listDrugResponse = response.body()!!

                    if (listDrugResponse.results!!.isNotEmpty()) {
                        fragmentManager!!.beginTransaction()
                                .replace(R.id.mainActivity_content_container, MedSearchListFragment.newInstance(listDrugResponse))
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

            override fun onFailure(call: Call<DrugSearchList>?, t: Throwable?) {
                MaterialDialog.Builder(context!!)
                        .title("ผลการค้นหา")
                        .content("-กรุณาลองอีกครั้ง-")
                        .contentGravity(GravityEnum.CENTER)
                        .positiveText("ตกลง")
                        .show()
            }
        })
//        } else {
//            editTextSearch.error = "Error"
//        }
    }

    /**
     * Load advance search options
     */

    private fun loadAdvOptions() {
        var drugColor = HttpManager.getInstance().getService().getMedColor()
        drugColor.enqueue(object : Callback<DrugColorList> {

            override fun onResponse(call: Call<DrugColorList>?, response: Response<DrugColorList>?) {
                var tmpColor = response!!.body()!!.results!!

                mMedColor.clear()
                for (item in tmpColor) {
                    mMedColor.add(item.name!!)
                }
            }

            override fun onFailure(call: Call<DrugColorList>?, t: Throwable?) {
                Toast.makeText(context, "Color" + t.toString(), Toast.LENGTH_SHORT).show()
            }

        })

        var drugGroup = HttpManager.getInstance().getService().getMedGroup()
        drugGroup.enqueue(object : Callback<DrugGroupList> {
            override fun onResponse(call: Call<DrugGroupList>?, response: Response<DrugGroupList>?) {
                var tmpGroup = response!!.body()!!.results!!

                mDrugGroup.clear()
                for (item in tmpGroup) {
                    mDrugGroup.add(item.name!!)
                }
            }

            override fun onFailure(call: Call<DrugGroupList>?, t: Throwable?) {
                Toast.makeText(context, "Group" + t.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        var drugSize = HttpManager.getInstance().getService().getDrugSize()
        drugSize.enqueue(object : Callback<DrugSizeList> {
            override fun onResponse(call: Call<DrugSizeList>?, response: Response<DrugSizeList>?) {
                var tmpSize = response!!.body()!!.results!!

                mDrugSize.clear()
                for (item in tmpSize) {
                    mDrugSize.add(item.name!!)
                }
            }

            override fun onFailure(call: Call<DrugSizeList>?, t: Throwable?) {
                Toast.makeText(context, "Size" + t.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        var drugShape = HttpManager.getInstance().getService().getDrugShape()
        drugShape.enqueue(object : Callback<DrugShapeList> {

            override fun onResponse(call: Call<DrugShapeList>?, response: Response<DrugShapeList>?) {
                var tmpShape = response!!.body()!!.results!!

                mDrugShape.clear()
                for (item in tmpShape) {
                    mDrugShape.add(item.name!!)
                }
            }

            override fun onFailure(call: Call<DrugShapeList>?, t: Throwable?) {
                Toast.makeText(context, "Shape" + t.toString(), Toast.LENGTH_SHORT).show()
            }

        })

        var drugShapeType = HttpManager.getInstance().getService().getDrugShapeType()
        drugShapeType.enqueue(object : Callback<DrugShapTypeList> {
            override fun onResponse(call: Call<DrugShapTypeList>?, response: Response<DrugShapTypeList>?) {
                var tmpShapeType = response!!.body()!!.results!!

                mDrugShapeType.clear()
                for (item in tmpShapeType) {
                    mDrugShapeType.add(item.name!!)
                }
            }

            override fun onFailure(call: Call<DrugShapTypeList>?, t: Throwable?) {
                Toast.makeText(context, "ShapeType" + t.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        var drugStatus = HttpManager.getInstance().getService().getDrugStatus()
        drugStatus.enqueue(object : Callback<DrugStatusList> {

            override fun onResponse(call: Call<DrugStatusList>?, response: Response<DrugStatusList>?) {
                var tmpStatus = response!!.body()!!.results!!

                mDrugStatus.clear()
                for (item in tmpStatus) {
                    mDrugStatus.add(item.name!!)
                }
            }

            override fun onFailure(call: Call<DrugStatusList>?, t: Throwable?) {
                Toast.makeText(context, "Status" + t.toString(), Toast.LENGTH_SHORT).show()
            }

        })

        var drugType = HttpManager.getInstance().getService().getDrugType()
        drugType.enqueue(object : Callback<DrugTypeList> {

            override fun onResponse(call: Call<DrugTypeList>?, response: Response<DrugTypeList>?) {
                var tmpType = response!!.body()!!.results!!

                mDrugType.clear()
                for (item in tmpType) {
                    mDrugType.add(item.name!!)
                }
            }

            override fun onFailure(call: Call<DrugTypeList>?, t: Throwable?) {
                Toast.makeText(context, "Type" + t.toString(), Toast.LENGTH_SHORT).show()
            }

        })

        var drugRType = HttpManager.getInstance().getService().getDrugRType()
        drugRType.enqueue(object : Callback<DrugRTypeList> {

            override fun onResponse(call: Call<DrugRTypeList>?, response: Response<DrugRTypeList>?) {
                var tmpRType = response!!.body()!!.results!!

                mDrugRType.clear()
                for (item in tmpRType) {
                    mDrugRType.add(item.name!!)
                }
            }

            override fun onFailure(call: Call<DrugRTypeList>?, t: Throwable?) {
                Toast.makeText(context, "RType" + t.toString(), Toast.LENGTH_SHORT).show()
            }

        })

    }

    /**
     * Check toggle switch advance search
     */

    private fun onCheckSwitchAdvSearch() {
        if (switchAdvSearch.isChecked) {
            expand(optionsAdvSearch)
        } else {
            collapse(optionsAdvSearch)
        }
    }

    /**
     * Onclick advance search options function
     */

    private fun onClickEditTextAdvOptionColor(circleView: CircleView, numberColor: Int) {

        var colorPicker = ColorPicker(activity)
        colorPicker.setColors(mColorList)
        colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
            override fun onChooseColor(position: Int, color: Int) {

                circleView.titleText = mColorMap.getValue(java.lang.Integer.toHexString(color))
                circleView.fillColor = color
                when (numberColor) {
                    1 -> {
                        mColor1 = mColorMap.getValue(java.lang.Integer.toHexString(color))
                    }
                    2 -> {
                        mColor2 = mColorMap.getValue(java.lang.Integer.toHexString(color))
                    }
                    3 -> {
                        mColor3 = mColorMap.getValue(java.lang.Integer.toHexString(color))
                    }
                    4 -> {
                        mColor4 = mColorMap.getValue(java.lang.Integer.toHexString(color))
                    }
                }

            }

            override fun onCancel() {

            }
        })
        colorPicker.show()
    }

    private fun onClickEditTextAdvOptionDrugGroup() {
        MaterialDialog.Builder(context!!)
                .title("ประเภทของยา")
                .items(mDrugGroup)
                .itemsCallback({ dialog, itemView, position, text ->
                    editTextAdvOptionDrugGroup.setText(text)
                    mDGroup = text.toString()
                })
                .show()
    }

    private fun onClickEditTextAdvOptionDrugSize() {
        MaterialDialog.Builder(context!!)
                .title("ขนาดด้านยาว")
                .items(mDrugSize)
                .itemsCallback({ dialog, itemView, position, text ->
                    editTextAdvOptionDrugSize.setText(text)
                    mDSize = text.toString()
                })
                .show()
    }

    private fun onClickEditTextAdvOptionDrugShape() {
        MaterialDialog.Builder(context!!)
                .title("รูปร่างลักษณะ")
                .items(mDrugShape)
                .itemsCallback({ dialog, itemView, position, text ->
                    editTextAdvOptionDrugShape.setText(text)
                    mDShape = text.toString()
                })
                .show()
    }

    private fun onClickEditTextAdvOptionDrugShapeType() {
        MaterialDialog.Builder(context!!)
                .title("สัญลักษณ์ / ตัวอักษร ที่ปรากฎบนเม็ดยา")
                .items(mDrugShapeType)
                .itemsCallbackMultiChoice(null, object : MaterialDialog.ListCallbackMultiChoice {
                    override fun onSelection(dialog: MaterialDialog?, which: Array<Int>, text: Array<CharSequence>): Boolean {
                        Toast.makeText(context, text.toString(), Toast.LENGTH_SHORT).show()
                        return true
                    }
                })
                .positiveText("OK")
                .show()
    }

    private fun onClickEditTextAdvOptionDrugStatus() {
        MaterialDialog.Builder(context!!)
                .title("สถานะของยา")
                .items(mDrugStatus)
                .itemsCallback({ dialog, itemView, position, text ->
                    editTextAdvOptionDrugStatus.setText(text)
                    mDStatus = text.toString()
                })
                .show()
    }

    private fun onClickEditTextAdvOptionDrugType() {
        MaterialDialog.Builder(context!!)
                .title("รูปแบบผลิตภัณฑ์")
                .items(mDrugType)
                .itemsCallback({ dialog, itemView, position, text ->
                    editTextAdvOptionDrugType.setText(text)
                    mDType = text.toString()
                })
                .show()
    }

    private fun onClickEditTextAdvOptionDrugRType() {
        MaterialDialog.Builder(context!!)
                .title("ประเภททะเบียนยา")
                .items(mDrugRType)
                .itemsCallback({ dialog, itemView, position, text ->
                    editTextAdvOptionDrugRType.setText(text)
                    mDType = text.toString()
                })
                .show()
    }

    /**
     * Expand and collapse view function
     */

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

    /**
     * Check search EditText
     */

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