package com.example.login.ui.fragments

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.login.R
import com.example.login.chantierApi.ApiConfig
import com.example.login.chantierApi.AppConfig
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

//
class NewChantierFragment : Fragment() {
    var btnuploadMultiple: Button? = null
    var btnPickImage: Button? = null
    var mediaPath: String? = null
    var mediaPath1: String? = null
    var mediaPath2: String? = null
    var mediaPath3: String? = null
    var mediaPath4: String? = null
    var imgView: ImageView? = null
    var imgView1: ImageView? = null
    var imgView2: ImageView? = null
    var imgView3: ImageView? = null
    var imgView4: ImageView? = null
    var mediaColumns = arrayOf(MediaStore.Video.Media._ID)
    var progressDialog: ProgressDialog? = null
    var str1: TextView? = null
    var str2: TextView? = null
    var str3: TextView? = null
    var str4: TextView? = null
    var str5: TextView? = null
    var ed1: EditText? = null
    var ed2: EditText? = null
    var ed3: EditText? = null
    var ed4: EditText? = null
    var ed5: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=  inflater.inflate(R.layout.fragment_new_chantier, container, false)
            progressDialog = ProgressDialog(activity?.baseContext)
            progressDialog!!.setMessage("Uploading...")
            btnuploadMultiple = view.findViewById<View>(R.id.uploadMultiple) as Button
            imgView = view.findViewById<View>(R.id.preview) as ImageView
            imgView1 = view.findViewById<View>(R.id.preview1) as ImageView
            imgView2 = view.findViewById<View>(R.id.preview2) as ImageView
            imgView3 = view.findViewById<View>(R.id.preview3) as ImageView
            imgView4 = view.findViewById<View>(R.id.preview4) as ImageView
            ed1 = view.findViewById<View>(R.id.pname) as EditText
            ed2 = view.findViewById<View>(R.id.client) as EditText
            ed3 = view.findViewById<View>(R.id.region) as EditText
            ed4 = view.findViewById<View>(R.id.type_projet) as EditText
            ed5 = view.findViewById<View>(R.id.type_travx) as EditText
            str1 = view.findViewById<View>(R.id.filename1) as TextView
            str2 = view.findViewById<View>(R.id.filename2) as TextView
            str3 = view.findViewById<View>(R.id.filename3) as TextView
            str4 = view.findViewById<View>(R.id.filename4) as TextView
            str5 = view.findViewById<View>(R.id.filename5) as TextView
            btnuploadMultiple!!.setOnClickListener { uploadMultipleFiles() }
            imgView!!.setOnClickListener {
                val galleryIntent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, 0)
            }
            imgView1!!.setOnClickListener {
                val galleryIntent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(galleryIntent, 1)
            }
            imgView2!!.setOnClickListener {
                val galleryIntent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                startActivityForResult(galleryIntent, 2)
            }
            imgView3!!.setOnClickListener {
                val galleryIntent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                startActivityForResult(galleryIntent, 3)
            }
            imgView4!!.setOnClickListener {
                val galleryIntent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                startActivityForResult(galleryIntent, 4)
            }

        return view
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == AppCompatActivity.RESULT_OK && null != data) {

                // Get the Image from data
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor =
                    context?.contentResolver?.query(selectedImage!!, filePathColumn, null, null, null)!!
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                mediaPath = cursor.getString(columnIndex)
                str1!!.text = mediaPath
                // Set the Image in ImageView for Previewing the Media
                imgView!!.setImageBitmap(BitmapFactory.decodeFile(mediaPath))
                cursor.close()
            } else if (requestCode == 1 && resultCode == AppCompatActivity.RESULT_OK && null != data) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor =
                    context?.contentResolver?.query(selectedImage!!, filePathColumn, null, null, null)!!
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                mediaPath1 = cursor.getString(columnIndex)
                str2!!.text = mediaPath1
                // Set the Image in ImageView for Previewing the Media
                imgView1!!.setImageBitmap(BitmapFactory.decodeFile(mediaPath1))
                cursor.close()
            } else if (requestCode == 2 && resultCode == AppCompatActivity.RESULT_OK && null != data) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor =
                    context?.contentResolver?.query(selectedImage!!, filePathColumn, null, null, null)!!
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                mediaPath2 = cursor.getString(columnIndex)
                str3!!.text = mediaPath2
                // Set the Image in ImageView for Previewing the Media
                imgView2!!.setImageBitmap(BitmapFactory.decodeFile(mediaPath2))
                cursor.close()
            } else if (requestCode == 3 && resultCode == AppCompatActivity.RESULT_OK && null != data) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor =
                    context?.contentResolver?.query(selectedImage!!, filePathColumn, null, null, null)!!
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                mediaPath3 = cursor.getString(columnIndex)
                str4!!.text = mediaPath3
                // Set the Image in ImageView for Previewing the Media
                imgView3!!.setImageBitmap(BitmapFactory.decodeFile(mediaPath3))
                cursor.close()
            } else if (requestCode == 4 && resultCode == AppCompatActivity.RESULT_OK && null != data) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor =
                    context?.contentResolver?.query(selectedImage!!, filePathColumn, null, null, null)!!
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                mediaPath4 = cursor.getString(columnIndex)
                str5!!.text=mediaPath4// Set the Image in ImageView for Previewing the Media
                imgView4!!.setImageBitmap(BitmapFactory.decodeFile(mediaPath4))
                cursor.close()
            }
        } catch (e: Exception) {
            Toast.makeText(activity?.baseContext, "Something went wrong", Toast.LENGTH_LONG).show()
        }
    }

    // Providing Thumbnail For Selected Image
    fun getThumbnailPathForLocalFile(context: Activity, fileUri: Uri?): Bitmap {
        val fileId = getFileId(context, fileUri)
        return MediaStore.Video.Thumbnails.getThumbnail(
            context.contentResolver,
            fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null
        )
    }

    // Getting Selected File ID
    fun getFileId(context: Activity, fileUri: Uri?): Long {
        val cursor = context.managedQuery(fileUri, mediaColumns, null, null, null)
        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            return cursor.getInt(columnIndex).toLong()
        }
        return 0
    }

    fun uploadMultipleFiles() {
        progressDialog!!.show()
        val file = File(mediaPath)
        val file2 = File(mediaPath1)
        val file3 = File(mediaPath2)
        val file4 = File(mediaPath3)
        val file5 = File(mediaPath4)
        val pname = ed1!!.text.toString()
        val client = ed2!!.text.toString()
        val region = ed3!!.text.toString()
        val type_travaux = ed4!!.text.toString()
        val type_projet = ed5!!.text.toString()

        // Parsing any Media type file
        val requestBody1 = file.asRequestBody("*/*".toMediaTypeOrNull())
        val requestBody2 = file2.asRequestBody("*/*".toMediaTypeOrNull())
        val requestBody3 = file3.asRequestBody("*/*".toMediaTypeOrNull())
        val requestBody4 = file4.asRequestBody("*/*".toMediaTypeOrNull())
        val requestBody5 = file5.asRequestBody("*/*".toMediaTypeOrNull())
        val fileToUpload1 = MultipartBody.Part.createFormData("file", file.name, requestBody1)
        val fileToUpload2 = MultipartBody.Part.createFormData("file2", file2.name, requestBody2)
        val fileToUpload3 = MultipartBody.Part.createFormData("file3", file3.name, requestBody3)
        val fileToUpload4 = MultipartBody.Part.createFormData("file4", file4.name, requestBody4)
        val fileToUpload5 = MultipartBody.Part.createFormData("file5", file5.name, requestBody5)
        val name = RequestBody.create(MultipartBody.FORM, pname)
        val cclient = RequestBody.create(MultipartBody.FORM, client)
        val cegion = RequestBody.create(MultipartBody.FORM, region)
        val ctypeproj = RequestBody.create(MultipartBody.FORM, type_projet)
        val ctypetrav = RequestBody.create(MultipartBody.FORM, type_travaux)
        val getResponse: ApiConfig = AppConfig.retrofit.create(ApiConfig::class.java)
        val call: Call<ResponseBody?>? = getResponse.uploadMulFile(
            fileToUpload1, fileToUpload2, fileToUpload3, fileToUpload4, fileToUpload5,
            name, cclient, cegion, ctypeproj, ctypetrav
        )
        call!!.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                if (response.isSuccessful) {
                    Toast.makeText(activity?.baseContext, "Upload success", Toast.LENGTH_LONG).show()
                    progressDialog!!.dismiss()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(activity?.baseContext, "Upload Error", Toast.LENGTH_LONG).show()
                progressDialog!!.dismiss()
            }
        })
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            NewChantierFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}