package com.example.bbsuestc.widget

import android.animation.LayoutTransition
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnFocusChangeListener
import android.view.View.OnKeyListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.Toast
import com.alibaba.fastjson.JSONObject
import com.bumptech.glide.Glide
import com.example.bbsuestc.R
import com.example.bbsuestc.utils.ScreenUtil.dip2px
import com.google.android.material.imageview.ShapeableImageView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RichContentEdittext @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {
    private var viewTagIndex = 1 // 新生的view都会打一个tag，对每个view来说，这个tag是唯一的。
    private val rootLayout // 这个是所有子view的容器，scrollView内部的唯一一个ViewGroup
            : LinearLayout
    private val inflater: LayoutInflater
    private val keyListener // 所有EditText的软键盘监听器
            : OnKeyListener
    private val btnListener // 图片右上角删除按钮监听器
            : OnClickListener
    private val focusListener // 所有EditText的焦点监听listener
            : OnFocusChangeListener
    private var lastFocusEdit // 最近被聚焦的EditText
            : EmojiEditText
    private var mTransitioner: LayoutTransition? = null // 只在图片View添加或remove时，触发transition动画
    private val editNormalPadding = 0 //
    private var disappearingImageIndex = 0
    private val imagePaths : ArrayList<String?> //图片地址集合

    /** 自定义属性  */ //插入的图片高度
    private val imageHeight = 600

    //两张相邻图片间距
    private val imageBottom = 10

    //文字相关属性，初始提示信息，文字大小和颜色
    private val textInitHint = "输入正文"
    private val textHint = ""
    private val textSize = 17
    private val textColor = getContext().getColor(R.color.black)
    private val textLineSpace = 10

    //删除图片的接口
    private var onRtImageDeleteListener: OnRtImageDeleteListener? = null
    private var onRtImageClickListener: OnRtImageClickListener? = null

    init {
        imagePaths = ArrayList()
        inflater = LayoutInflater.from(context)

        // 1. 初始化allLayout
        rootLayout = LinearLayout(context)
        rootLayout.orientation = LinearLayout.VERTICAL
        setupLayoutTransitions() //禁止载入动画
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        rootLayout.setPadding(0, 15, 0, 15) //设置间距，防止生成图片时文字太靠边，不能用margin，否则有黑边
        addView(rootLayout, layoutParams)

        // 2. 初始化键盘退格监听
        // 主要用来处理点击回删按钮时，view的一些列合并操作
        keyListener = OnKeyListener { v: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN
                && event.keyCode == KeyEvent.KEYCODE_DEL
            ) {
                val edit = v as EmojiEditText
                onBackspacePress(edit)
            }
            false
        }

        // 3. 图片删除处理
        btnListener = OnClickListener { v: View ->
            if (v is ShapeableImageView) {
                val imageView = v
                // 开放图片点击接口
                if (onRtImageClickListener != null) {
                    onRtImageClickListener!!.onRtImageClick(imageView, imageView.tag as String)
                }
            } else if (v is ImageView) {
                val parentView = v.getParent() as RelativeLayout
                onImageCloseClick(parentView)
            }
        }
        focusListener = OnFocusChangeListener { v: View, hasFocus: Boolean ->
            if (hasFocus) {
                lastFocusEdit = v as EmojiEditText
            }
        }
        val firstEditParam = LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT
        )
        val firstEdit = createEditText(textInitHint, dip2px(context!!, EDIT_TEXT_PADDING.toFloat()))
        rootLayout.addView(firstEdit, firstEditParam)
        lastFocusEdit = firstEdit
    }

    /**
     * author: sca_tl
     * description: 回退键处理
     */
    private fun onBackspacePress(editTxt: EmojiEditText) {
        try {
            val startSelection = editTxt.selectionStart
            // 只有在光标已经顶到文本输入框的最前方，在判定是否删除之前的图片，或两个View合并
            if (startSelection == 0) {
                val editIndex = rootLayout.indexOfChild(editTxt)
                val preView = rootLayout.getChildAt(editIndex - 1) // 如果editIndex-1<0,则返回的是null
                if (null != preView) {
                    if (preView is RelativeLayout) {
                        // 光标EditText的上一个view对应的是图片
                        onImageCloseClick(preView)
                    } else if (preView is EmojiEditText) {
                        // 光标EditText的上一个view对应的还是文本框EditText
                        val str1 = editTxt.text.toString()
                        val preEdit = preView
                        val str2 = preEdit.text.toString()

                        // 合并文本view时，不需要transition动画
                        rootLayout.layoutTransition = null
                        rootLayout.removeView(editTxt)
                        rootLayout.layoutTransition = mTransitioner // 恢复transition动画

                        // 文本合并
                        preEdit.setText(str2 + str1)
                        preEdit.requestFocus()
                        preEdit.setSelection(str2.length, str2.length)
                        lastFocusEdit = preEdit
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * author: sca_tl
     * description: 处理图片叉掉的点击事件
     * @param view 整个image对应的relativeLayout view
     */
    private fun onImageCloseClick(view: View) {
        try {
            if (!mTransitioner!!.isRunning) {
                disappearingImageIndex = rootLayout.indexOfChild(view)
                //删除编辑器里的图片
                val dataList = buildEditorData()
                val editData = dataList[disappearingImageIndex]
                if (editData.imagePath != null) {
                    if (onRtImageDeleteListener != null) {
                        onRtImageDeleteListener!!.onRtImageDelete(editData.imagePath)
                    }
                    imagePaths.remove(editData.imagePath)
                }
                rootLayout.removeView(view)
                mergeEditText() //合并上下EditText内容
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 清空所有布局
     */
    fun clearAllLayout() {
        rootLayout.removeAllViews()
    }

    val lastIndex: Int
        /**
         * 获取索引位置
         */
        get() = rootLayout.childCount

    /**
     * author: sca_tl
     * description: 生成文本输入框
     */
    fun createEditText(hint: String?, paddingTop: Int): EmojiEditText {
        val editText =
            inflater.inflate(R.layout.widget_content_editor_edittext, null) as EmojiEditText
        editText.setOnKeyListener(keyListener)
        editText.tag = viewTagIndex++
        editText.setPadding(editNormalPadding, paddingTop, editNormalPadding, paddingTop)
        editText.hint = hint
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
        editText.setTextColor(textColor)
        editText.setLineSpacing(textLineSpace.toFloat(), 1.0f)
        editText.onFocusChangeListener = focusListener
        return editText
    }

    /**
     * description: 生成图片View
     */
    private fun createImageLayout(): RelativeLayout {
        val layout =
            inflater.inflate(R.layout.widget_content_editor_imageview, null) as RelativeLayout
        layout.tag = viewTagIndex++
        val closeView : ImageView= layout.findViewById(R.id.content_editor_img_close)
        closeView.tag = layout.tag
        closeView.setOnClickListener(btnListener)
        val imageView : ShapeableImageView= layout.findViewById(R.id.content_editor_imageView)
        imageView.setOnClickListener(btnListener)
        return layout
    }

    /**
     * 根据绝对路径添加view
     */
    fun insertImage(imagePath: String?, width: Int) {
        if (TextUtils.isEmpty(imagePath)) {
            return
        }
        val bmp = getScaledBitmap(imagePath, width)
        insertImage(bmp, imagePath)
    }

    /**
     * author: sca_tl
     * description: 插入图片
     */
    private fun insertImage(bitmap: Bitmap?, imagePath: String?) {
        //bitmap == null时，可能是网络图片，不能做限制
        if (TextUtils.isEmpty(imagePath)) {
            return
        }
        try {
            //lastFocusEdit获取焦点的EditText
            val lastEditStr = lastFocusEdit.text.toString()
            val cursorIndex = lastFocusEdit.selectionStart //获取光标所在位置
            val editStr1 = lastEditStr.substring(0, cursorIndex).trim { it <= ' ' } //获取光标前面的字符串
            val editStr2 = lastEditStr.substring(cursorIndex).trim { it <= ' ' } //获取光标后的字符串
            val lastEditIndex = rootLayout.indexOfChild(lastFocusEdit) //获取焦点的EditText所在位置
            if (lastEditStr.isEmpty()) {
                //如果当前获取焦点的EditText为空，直接在EditText下方插入图片，并且插入空的EditText
                addEditTextAtIndex(lastEditIndex + 1, "")
                addImageViewAtIndex(lastEditIndex + 1, bitmap, imagePath)
            } else if (editStr1.isEmpty()) {
                //如果光标已经顶在了editText的最前面，则直接插入图片，并且EditText下移即可
                addImageViewAtIndex(lastEditIndex, bitmap, imagePath)
                //同时插入一个空的EditText，防止插入多张图片无法写文字
                addEditTextAtIndex(lastEditIndex + 1, "")
            } else if (editStr2.isEmpty()) {
                // 如果光标已经顶在了editText的最末端，则需要添加新的imageView和EditText
                addEditTextAtIndex(lastEditIndex + 1, "")
                addImageViewAtIndex(lastEditIndex + 1, bitmap, imagePath)
            } else {
                //如果光标已经顶在了editText的最中间，则需要分割字符串，分割成两个EditText，并在两个EditText中间插入图片
                //把光标前面的字符串保留，设置给当前获得焦点的EditText（此为分割出来的第一个EditText）
                lastFocusEdit.setText(editStr1)
                //把光标后面的字符串放在新创建的EditText中（此为分割出来的第二个EditText）
                addEditTextAtIndex(lastEditIndex + 1, editStr2)
                //在第二个EditText的位置插入一个空的EditText，以便连续插入多张图片时，有空间写文字，第二个EditText下移
                addEditTextAtIndex(lastEditIndex + 1, "")
                //在空的EditText的位置插入图片布局，空的EditText下移
                addImageViewAtIndex(lastEditIndex + 1, bitmap, imagePath)
            }
            //隐藏软键盘
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(lastFocusEdit.windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * author: sca_tl
     * description: 在特定位置插入EditText
     * @param index 位置
     * @param editStr EditText显示的文字
     */
    fun addEditTextAtIndex(index: Int, editStr: CharSequence) {
        try {
            val editText2 = createEditText(textHint, EDIT_TEXT_PADDING)
            if (!TextUtils.isEmpty(editStr)) { //判断插入的字符串是否为空，如果没有内容则显示hint提示信息
                editText2.setText(editStr)
            }
            editText2.onFocusChangeListener = focusListener

            // 请注意此处，EditText添加、或删除不触动Transition动画
            rootLayout.layoutTransition = null
            rootLayout.addView(editText2, index)
            rootLayout.layoutTransition = mTransitioner // remove之后恢复transition动画
            //插入新的EditText之后，修改lastFocusEdit的指向
            lastFocusEdit = editText2
            lastFocusEdit.requestFocus()
            lastFocusEdit.setSelection(editStr.length, editStr.length)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * author: sca_tl
     * description: 在光标处插入文字
     */
    fun insertText(text: String?) {
        lastFocusEdit.text!!.insert(lastFocusEdit.selectionStart, text)
    }

    /**
     * author: sca_tl
     * description: 在光标处插入表情
     * @param emotion_path 表情路径，表情文件名[s_123]需要改成河畔服务器可识别的[s:123]
     */
    fun insertEmotion(emotion_path: String?) {
        lastFocusEdit.insertEmotion(emotion_path!!, lastFocusEdit.selectionStart)
    }

    /**
     * author: sca_tl
     * description: 在特定位置添加ImageView
     */
    fun addImageViewAtIndex(index: Int, bmp: Bitmap?, imagePath: String?) {
        if (TextUtils.isEmpty(imagePath)) {
            return
        }
        try {
            imagePaths.add(imagePath)
            val imageLayout = createImageLayout()
            val imageView : ShapeableImageView = imageLayout.findViewById(R.id.content_editor_imageView)
            Glide.with(context).load(imagePath).into(imageView)
            imageView.tag = imagePath
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP //裁剪居中
            val lp = RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, imageHeight)
            lp.bottomMargin = imageBottom
            imageView.layoutParams = lp
            Glide.with(context).load(imagePath).into(imageView)
            rootLayout.addView(imageLayout, index)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * author: sca_tl
     * description: 在特定位置添加ImageView
     */
    fun addImageViewAtIndex(index: Int, imagePath: String?) {
        if (TextUtils.isEmpty(imagePath)) {
            return
        }
        try {
            imagePaths.add(imagePath)
            val imageLayout = createImageLayout()
            val imageView =
                imageLayout.findViewById<ShapeableImageView>(R.id.content_editor_imageView)
            imageView.tag = imagePath
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP //裁剪居中
            val lp = RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, imageHeight
            ) //固定图片高度，记得设置裁剪剧中
            lp.bottomMargin = imageBottom
            imageView.layoutParams = lp
            Glide.with(context).load(imagePath).into(imageView)
            rootLayout.addView(imageLayout, index)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * author: sca_tl
     * description: 根据view的宽度，动态缩放bitmap尺寸
     * @param width view的宽度
     */
    private fun getScaledBitmap(filePath: String?, width: Int): Bitmap? {
        if (TextUtils.isEmpty(filePath)) {
            return null
        }
        val options = BitmapFactory.Options()
        try {
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(filePath, options)
            val sampleSize = if (options.outWidth > width) (options.outWidth / width
                    + 1) else 1
            options.inJustDecodeBounds = false
            options.inSampleSize = sampleSize
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return BitmapFactory.decodeFile(filePath, options)
    }

    /**
     * 初始化transition动画
     */
    private fun setupLayoutTransitions() {
        mTransitioner = LayoutTransition()
        rootLayout.layoutTransition = mTransitioner
        mTransitioner!!.addTransitionListener(object : LayoutTransition.TransitionListener {
            override fun startTransition(
                transition: LayoutTransition,
                container: ViewGroup,
                view: View,
                transitionType: Int
            ) {
            }

            override fun endTransition(
                transition: LayoutTransition,
                container: ViewGroup,
                view: View,
                transitionType: Int
            ) {
                if (!transition.isRunning && transitionType == LayoutTransition.CHANGE_DISAPPEARING) {
                    // transition动画结束，合并EditText
                    mergeEditText()
                }
            }
        })
        mTransitioner!!.setDuration(300)
    }

    /**
     * 图片删除的时候，如果上下方都是EditText，则合并处理
     */
    private fun mergeEditText() {
        try {
            val preView = rootLayout.getChildAt(disappearingImageIndex - 1)
            val nextView = rootLayout.getChildAt(disappearingImageIndex)
            if (preView is EmojiEditText && nextView is EmojiEditText) {
                val preEdit = preView
                val nextEdit = nextView
                val str1 = preEdit.text.toString()
                val str2 = nextEdit.text.toString()
                var mergeText = ""
                mergeText = if (str2.length > 0) {
                    """
     $str1
     $str2
     """.trimIndent()
                } else {
                    str1
                }
                rootLayout.layoutTransition = null
                rootLayout.removeView(nextEdit)
                preEdit.setText(mergeText)
                preEdit.requestFocus()
                preEdit.setSelection(str1.length, str1.length)
                rootLayout.layoutTransition = mTransitioner
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * author: sca_tl
     * description: 生成编辑数据
     */
    fun buildEditorData(): List<EditData> {
        val dataList: MutableList<EditData> = ArrayList()
        try {
            for (index in 0 until rootLayout.childCount) {
                val itemView = rootLayout.getChildAt(index)
                val itemData = EditData()
                if (itemView is EmojiEditText) {  //是文本
                    itemData.inputStr = itemView.text.toString()
                    itemData.content_type = CONTENT_TYPE_TEXT
                } else if (itemView is RelativeLayout) {  //是图片
                    val item =
                        itemView.findViewById<ShapeableImageView>(R.id.content_editor_imageView)
                    itemData.imagePath = item.tag as String
                    itemData.content_type = CONTENT_TYPE_IMAGE
                }
                dataList.add(itemData)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataList
    }

    val isEditorEmpty: Boolean
        /**
         * author: sca_tl
         * description: 编辑器是否为空
         */
        get() = TextUtils.isEmpty(lastFocusEdit.text) && imagePaths.size == 0
    val imgPathList: List<String?>
        /**
         * author: sca_tl
         * description: 获取图片集合
         */
        get() {
            val path_list: MutableList<String?> = ArrayList()
            val editList = buildEditorData()
            for (itemData in editList) {
                if (itemData.imagePath != null) {
                    path_list.add(itemData.imagePath)
                }
            }
            return path_list
        }

    /**
     * author: sca_tl
     * description: 异步方式显示数据
     */
    fun setEditorData(content: String?) {
        rootLayout.removeAllViews()
        Observable.create { emitter ->
            try {
                val jsonArray = JSONObject.parseArray(content)
                for (i in jsonArray.indices) {
                    emitter.onNext(jsonArray.getJSONObject(i))
                }
                emitter.onComplete()
            } catch (e: Exception) {
                e.printStackTrace()
                emitter.onError(e)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<JSONObject> {
                override fun onComplete() {
                    if (rootLayout.getChildAt(0) is EmojiEditText) {
                        val editText = rootLayout.getChildAt(0) as EmojiEditText
                        if (TextUtils.isEmpty(editText.text.toString())) {
                            rootLayout.removeView(editText)
                        }
                    }

                    //在全部插入完毕后，再插入一个EditText，防止最后一张图片后无法插入文字
                    if (rootLayout.getChildAt(lastIndex) is RelativeLayout) {
                        addEditTextAtIndex(lastIndex, "")
                    }
                }

                override fun onError(e: Throwable) {}
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(content_json: JSONObject) {
                    try {
                        val type = content_json.getIntValue("content_type")
                        val content = content_json.getString("content")
                        if (type == CONTENT_TYPE_TEXT) {
                            addEditTextAtIndex(lastIndex, content)
                        }
                        if (type == CONTENT_TYPE_IMAGE) {
                            //addEditTextAtIndex(getLastIndex(), "");
                            addImageViewAtIndex(lastIndex, content)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    inner class EditData {
        var inputStr: String? = null //文本内容
        var imagePath: String? = null //图片路径
        var content_type = 0 //0为文本，1为图片
        var bitmap: Bitmap? = null
    }

    interface OnRtImageDeleteListener {
        fun onRtImageDelete(imagePath: String?)
    }

    fun setOnRtImageDeleteListener(onRtImageDeleteListener: OnRtImageDeleteListener?) {
        this.onRtImageDeleteListener = onRtImageDeleteListener
    }

    interface OnRtImageClickListener {
        fun onRtImageClick(view: View?, imagePath: String?)
    }

    fun setOnRtImageClickListener(onRtImageClickListener: OnRtImageClickListener?) {
        this.onRtImageClickListener = onRtImageClickListener
    }

    companion object {
        private const val EDIT_TEXT_PADDING = 10 // edittext常规padding是10dp
        const val CONTENT_TYPE_TEXT = 0 //文本
        const val CONTENT_TYPE_IMAGE = 1 //图片
    }
}
