package com.ifeng.iagreement

import androidx.fragment.app.FragmentManager
import com.ifeng.iagreement.data.IAgreementDto

object IAgreementManager {

    interface Callback {
        fun onIAgreementAgree()
        fun onIAgreementQuit()
    }

    fun showIAgreementPopupDialog(fragmentManager: FragmentManager, dto: IAgreementDto, onIAgreementCallback: Callback? = null){
        IAgreementPopupDialog.newInstance(dto).apply {
           onIAgreementDialogCallback = object : IAgreementPopupDialog.Callback{
               override fun onIAgreementAgree() {
                   onIAgreementCallback?.onIAgreementAgree()
               }

               override fun onIAgreementNot() {
                   showIAgreementPopupAgainDialog(fragmentManager, onIAgreementCallback)
               }
           }
        }.show(fragmentManager, "IAgreementPopupDialog")
    }

    fun showIAgreementPopupAgainDialog(fragmentManager: FragmentManager, onIAgreementCallback: Callback? = null){
        IAgreementPopupAgainDialog.newInstance().apply {
            onIAgreementAgainDialogCallback = object : IAgreementPopupAgainDialog.Callback{
                override fun onIAgreementAgree() {
                    onIAgreementCallback?.onIAgreementAgree()
                }

                override fun onIAgreementQuit() {
                    onIAgreementCallback?.onIAgreementQuit()
                }
            }
        }.show(fragmentManager, "IAgreementPopupAgainDialog")
    }

}