package com.tttrfge.deliveryapp.sesion3.fragments.profilepac

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.tttrfge.deliveryapp.R

class SendApackageEmpty : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.send_apackage_empty, container, false)
    }

    @SuppressLint
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val BF1 = view?.findViewById<Button>(R.id.wallet)
        val controller = findNavController()
        BF1?.setOnClickListener { controller.navigate(R.id.onboarding4) }
    }

}