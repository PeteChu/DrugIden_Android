package com.example.drugiden.fragment

import android.app.ProgressDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
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
import dmax.dialog.SpotsDialog
import petrov.kristiyan.colorpicker.ColorPicker
import kotlin.collections.ArrayList


/**
 * Created by schecterza on 10/27/2017.
 */

class MainFragment : Fragment(), View.OnClickListener {

    lateinit var mToolbar: Toolbar
    lateinit var imageViewLogo: ImageView
    lateinit var waitDialog: SpotsDialog
    lateinit var viewOptionColor1: CircleView
    lateinit var viewOptionColor2: CircleView
    lateinit var viewOptionColor3: CircleView
    lateinit var viewOptionColor4: CircleView

    lateinit var editTextSearch: MaterialEditText
    lateinit var editTextLicense: MaterialEditText
    lateinit var editTextManufactor: MaterialEditText
    lateinit var editTextDistributor: MaterialEditText
    lateinit var editTextGenericName: MaterialEditText
    lateinit var editTextDrugWide: MaterialEditText
    lateinit var editTextDrugLong: MaterialEditText
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

    var mColor1: String? = null
    var mColor2: String? = null
    var mColor3: String? = null
    var mColor4: String? = null
    var mTradename: String? = null
    var mLicense: String? = null
    var mManufacturer: String? = null
    var mDistributor: String? = null
    var mGenericName: String? = null
    var mDWide: String? = null
    var mDLong: String? = null
    var mDGroup: String? = null
    var mDType: String? = null
    var mDRType: String? = null
    var mDShape: String? = null
    var mShapeText: String? = null
    var mShapeText2: String? = null
    var mShapeText3: String? = null
    var mShapeText4: String? = null
    var mShapeText5: String? = null
    var mShapeText6: String? = null
    var mDSize: String? = null
    var mDStatus: String? = null

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
        editTextLicense = rootView.advOption_licensee
        editTextManufactor = rootView.advOption_manufacturer
        editTextDistributor = rootView.advOption_distributor
        editTextGenericName = rootView.advOption_genericName
        editTextDrugWide = rootView.advOption_drugWide
        editTextDrugLong = rootView.advOption_drugLong
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


        var actionbar = (activity as AppCompatActivity)
        mToolbar.setTitle(R.string.app_name)
        mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
        actionbar.setSupportActionBar(mToolbar)

        actionbar.supportActionBar!!.hide()

        switchAdvSearch.isChecked = false
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

        waitDialog = SpotsDialog(context!!)

        onEditTextChange()
    }


    override fun onResume() {
        if (switchAdvSearch.isChecked) {
            switchAdvSearch.isChecked = false
        }

        super.onResume()
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

    private fun onEditTextChange() {
        editTextLicense.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mLicense = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        editTextManufactor.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mManufacturer = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        editTextDistributor.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mDistributor = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        editTextGenericName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mGenericName = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        editTextDrugWide.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mDWide = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        editTextDrugLong.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mDLong = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
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
        waitDialog.show()

        var call: Call<DrugSearchList>?

        if (!switchAdvSearch.isChecked) {

            if (!isEditTextEmpty(editTextSearch)) {
                call = HttpManager.getInstance().getService().quickSearch(editTextSearch.text.toString(), resources.getString(R.string.token))
            } else {
                waitDialog.dismiss()
                MaterialDialog.Builder(context!!)
                        .title("ผลการค้นหา")
                        .content("-ไม่พบข้อมูลตามที่ร้องขอ-")
                        .contentGravity(GravityEnum.CENTER)
                        .positiveText("ตกลง")
                        .show()
                return
            }

        } else {

            if (!mColor1.isNullOrBlank()) {
                mColor1 = (mColor1!!.toInt() + 1).toString()
            }

            if (!mColor2.isNullOrBlank()) {
                mColor2 = (mColor2!!.toInt() + 1).toString()
            }

            if (!mColor3.isNullOrBlank()) {
                mColor3 = (mColor3!!.toInt() + 1).toString()
            }
            if (!mColor4.isNullOrBlank()) {
                mColor4 = (mColor4!!.toInt() + 1).toString()
            }

            mTradename = editTextSearch.text.toString()
            call = HttpManager.getInstance().getService().advanceSearch(
                    mColor1, mColor2, mColor3, mColor4, mTradename, mManufacturer, mGenericName, mLicense, mDistributor, mDGroup,
                    mDType, mDRType, mDShape, mDWide, mDLong, mShapeText, mShapeText2, mShapeText3, mShapeText4, mShapeText5,
                    mShapeText6, mDSize, mDStatus)
        }

        call.enqueue(object : Callback<DrugSearchList> {

            override fun onResponse(call: Call<DrugSearchList>?, response: Response<DrugSearchList>?) {

                waitDialog.dismiss()

                if (response!!.isSuccessful) {

                    listDrugResponse = response.body()!!

//                    Toast.makeText(context, listDrugResponse.results!!.size.toString(), Toast.LENGTH_SHORT).show()

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
//                        mColor1 = mColorMap.getValue(java.lang.Integer.toHexString(color))
                        mColor1 = position.toString()
                    }
                    2 -> {
//                        mColor2 = mColorMap.getValue(java.lang.Integer.toHexString(color))
                        mColor2 = position.toString()
                    }
                    3 -> {
//                        mColor3 = mColorMap.getValue(java.lang.Integer.toHexString(color))
                        mColor3 = position.toString()
                    }
                    4 -> {
//                        mColor4 = mColorMap.getValue(java.lang.Integer.toHexString(color))
                        mColor4 = position.toString()
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
                    mDGroup = position.toString()
//                Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
                })
                .show()
    }

    private fun onClickEditTextAdvOptionDrugSize() {
        MaterialDialog.Builder(context!!)
                .title("ขนาดด้านยาว")
                .items(mDrugSize)
                .itemsCallback({ dialog, itemView, position, text ->
                    editTextAdvOptionDrugSize.setText(text)
                    mDSize = position.toString()
//                Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
                })
                .show()
    }

    private fun onClickEditTextAdvOptionDrugShape() {
        MaterialDialog.Builder(context!!)
                .title("รูปร่างลักษณะ")
                .items(mDrugShape)
                .itemsCallback({ dialog, itemView, position, text ->
                    editTextAdvOptionDrugShape.setText(text)
                    mDShape = position.toString()
//                Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
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
                    mDStatus = position.toString()
//                Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
                })
                .show()
    }

    private fun onClickEditTextAdvOptionDrugType() {
        MaterialDialog.Builder(context!!)
                .title("รูปแบบผลิตภัณฑ์")
                .items(mDrugType)
                .itemsCallback({ dialog, itemView, position, text ->
                    editTextAdvOptionDrugType.setText(text)
                    mDType = position.toString()
//                Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
                })
                .show()
    }

    private fun onClickEditTextAdvOptionDrugRType() {
        MaterialDialog.Builder(context!!)
                .title("ประเภททะเบียนยา")
                .items(mDrugRType)
                .itemsCallback({ dialog, itemView, position, text ->
                    editTextAdvOptionDrugRType.setText(text)
                    mDType = position.toString()
//                Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
                })
                .show()
    }

    /**
     * Reset AdvOptions
     */

    private fun resetAdvOptions() {
        editTextSearch.setText("")
        viewOptionColor1.titleText = "ไม่ระบุ"
        viewOptionColor2.titleText = "ไม่ระบุ"
        viewOptionColor3.titleText = "ไม่ระบุ"
        viewOptionColor4.titleText = "ไม่ระบุ"
        viewOptionColor1.fillColor = Color.parseColor("#FFFFFF")
        viewOptionColor2.fillColor = Color.parseColor("#FFFFFF")
        viewOptionColor3.fillColor = Color.parseColor("#FFFFFF")
        viewOptionColor4.fillColor = Color.parseColor("#FFFFFF")
        editTextLicense.setText("")
        editTextManufactor.setText("")
        editTextDistributor.setText("")
        editTextGenericName.setText("")
        editTextDrugWide.setText("")
        editTextDrugLong.setText("")
        editTextAdvOptionDrugGroup.setText("ไม่ระบุ")
        editTextAdvOptionDrugSize.setText("ไม่ระบุ")
        editTextAdvOptionDrugShape.setText("ไม่ระบุ")
        editTextAdvOptionDrugShapeType.setText("ไม่ระบุ")
        editTextAdvOptionDrugStatus.setText("ไม่ระบุ")
        editTextAdvOptionDrugType.setText("ไม่ระบุ")
        editTextAdvOptionDrugRType.setText("ไม่ระบุ")

        mColor1 = null
        mColor2 = null
        mColor3 = null
        mColor4 = null
        mTradename = null
        mLicense = null
        mManufacturer = null
        mDistributor = null
        mGenericName = null
        mDWide = null
        mDLong = null
        mDGroup = null
        mDType = null
        mDRType = null
        mDShape = null
        mShapeText = null
        mShapeText2 = null
        mShapeText3 = null
        mShapeText4 = null
        mShapeText5 = null
        mShapeText6 = null
        mDSize = null
        mDStatus = null

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

        resetAdvOptions()
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