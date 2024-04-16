package com.example.bbsuestc.newPostActivity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.am.widget.smoothinputlayout.SmoothInputLayout
import com.example.bbsuestc.R
import com.example.bbsuestc.homeActivity.plates.PlatesViewModel
import com.example.bbsuestc.utils.GlideEngine
import com.example.bbsuestc.utils.Statics
import com.example.bbsuestc.widget.RichContentEdittext
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener

class NewPostActivity : AppCompatActivity() {

    private val viewModel : NewPostViewModel by lazy { ViewModelProvider(this)[NewPostViewModel::class.java] }
    private lateinit var smoothLayout : SmoothInputLayout
    private lateinit var backIv : ImageView
    private lateinit var postTv : TextView
    private lateinit var plateTv : TextView
    private lateinit var themeTv : TextView
    private lateinit var titleEt : EditText
    private lateinit var contentRichEt : RichContentEdittext
    private lateinit var emojiIv : ImageView
    private lateinit var photoIv : ImageView
    private lateinit var atIv : ImageView
    private lateinit var attachmentIv : ImageView
    private lateinit var voteIV : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        init()
    }

    private fun init(){
        findView()
        setClickListeners()
    }


    private fun doChooseTheme() {
        //选择主题
    }

    private fun doChoosePlate() {
        //选择板块
    }

    private fun addVote() {
        //添加投票
    }

    private fun startContentProvider4Attachment() {
        //添加附件，用系统自带content provider
    }

    private fun doAt() {
        //at用户
    }


    private fun startContentProvider4Photo() {
        //添加照片
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED && Build.VERSION.SDK_INT <= 32) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), Statics.PERMISSIONS_READ_EXTERNAL_STORAGE)
        }else if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
        != PackageManager.PERMISSION_GRANTED && Build.VERSION.SDK_INT >= 33){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES), Statics.PERMISSIONS_READ_MEDIA_IMAGES)
        }
        PictureSelector.create(this)
            .openGallery(SelectMimeType.ofImage())
            .setImageEngine(GlideEngine.createGlideEngine())
            .forResult(object : OnResultCallbackListener<LocalMedia>{
                override fun onResult(result: ArrayList<LocalMedia>?) {
                    if (result != null) {
                        for (localFiles : LocalMedia in result){
                            contentRichEt.insertImage(localFiles.realPath, 1000)
                        }
                    }
                }

                override fun onCancel() {
                }

            })
    }

    private fun doPost() {
    }

    private fun findView() {
        backIv = findViewById(R.id.new_post_toolbar_back_iv)
        postTv = findViewById(R.id.new_post_toolbar_post_tv)
        smoothLayout = findViewById(R.id.new_post_whole_smooth_layout)
        plateTv = findViewById(R.id.new_post_plate_tv)
        themeTv = findViewById(R.id.new_post_theme_tv)
        titleEt = findViewById(R.id.new_post_title_et)
        contentRichEt = findViewById(R.id.new_post_content_rich_et)
        emojiIv = findViewById(R.id.new_post_add_emotion_iv)
        photoIv = findViewById(R.id.new_post_add_photos_iv)
        atIv = findViewById(R.id.new_post_at_iv)
        attachmentIv = findViewById(R.id.new_post_add_attachment_iv)
        voteIV = findViewById(R.id.new_post_add_vote_iv)
    }
    private fun setClickListeners() {
        backIv.setOnClickListener { finish() }
        postTv.setOnClickListener { doPost() }
        plateTv.setOnClickListener { doChoosePlate() }
        plateTv.setOnClickListener { doChooseTheme() }
        emojiIv.setOnClickListener { smoothLayout.showInputPane(true) }
        photoIv.setOnClickListener { startContentProvider4Photo() }
        atIv.setOnClickListener { doAt() }
        attachmentIv.setOnClickListener { startContentProvider4Attachment() }
        voteIV.setOnClickListener { addVote() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        this.currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            return
        }
    }
    override fun onPause() {
        super.onPause()
        // TODO: 做文件本地存储草稿以及定时存储草稿，防止闪退，杀后台等情况
    }
}