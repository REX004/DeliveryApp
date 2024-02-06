package com.tttrfge.deliveryapp.sesion2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.tttrfge.deliveryapp.R

class OTPVerification : AppCompatActivity() {

    companion object {
        private const val TEST_VERIFY_CODE = ""
    }

    private val constraintLayout: EditText by lazy {
        findViewById(R.id.otpCode)
    }
    private val editTextList: List<EditText> by lazy {
        listOf(
            findViewById(R.id.edit_1),
            findViewById(R.id.edit_2),
            findViewById(R.id.edit_3),
            findViewById(R.id.edit_4),
            findViewById(R.id.edit_5),
            findViewById(R.id.edit_6)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.otp_verification)

        val normalVerificationBorderColor = ContextCompat.getColor(this, R.color.normalVerificationBorderColor)
        val activeVerificationBorderColor = ContextCompat.getColor(this, R.color.activeVerificationBorderColor)

        setListener()

        initFocus()
        for (editText in editTextList) {
            addBorderChangeListener(editText, normalVerificationBorderColor, activeVerificationBorderColor)
        }

    }

    private fun addBorderChangeListener(editText: EditText, normalColor: Int, activeColor: Int) {
        editText.addTextChangedListener {
            val editTextStroke = editText.background as GradientDrawable
            if (it.toString().isNotEmpty()) {
                editTextStroke.setStroke(2, activeColor)
            } else {
                editTextStroke.setStroke(2, normalColor)
            }
        }
    }
    private fun setListener() {
        constraintLayout.setOnClickListener {
            val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(constraintLayout.windowToken, 0)
        }

        for (i in 0 until editTextList.size - 1) {
            setEditTextListeners(editTextList[i], editTextList[i + 1])
        }

        setLastEditTextListener(editTextList.last())
    }

    private fun initFocus() {
        editTextList[0].requestFocus()
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(editTextList[0], InputMethodManager.SHOW_FORCED)
    }

    private fun setEditTextListeners(fromEditText: EditText, targetEditText: EditText) {
        fromEditText.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                targetEditText.requestFocus()
            }
        }

        fromEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                targetEditText.requestFocus()
                true
            } else {
                false
            }
        }
    }

    private fun setLastEditTextListener(lastEditText: EditText) {
        lastEditText.addTextChangedListener {
            if (it.toString().isNotEmpty()) {

                val inputManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(lastEditText.windowToken, 0)

                verifyOTPCode()
            }
        }

        lastEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                val inputManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(lastEditText.windowToken, 0)

                verifyOTPCode()
                true
            } else {
                false
            }
        }
    }

    private fun verifyOTPCode() {
        val otpCode = buildString {
            for (editText in editTextList) {
                append(editText.text.toString().trim())
            }
        }

        if (otpCode.length != 6) {
            return
        }
        if (otpCode == TEST_VERIFY_CODE) {
            Toast.makeText(this, "success, should do next", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "error, input again", Toast.LENGTH_SHORT).show()
            reset()
        }
    }

    private fun reset() {
        for (editText in editTextList) {
            editText.setText("")
        }
        initFocus()
    }
}
