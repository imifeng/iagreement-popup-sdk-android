package com.ifeng.iagreement

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.ifeng.iagreement.databinding.DialogIagreementPopupAgainBinding


class IAgreementPopupAgainDialog : DialogFragment() {

    private var binding: DialogIagreementPopupAgainBinding? = null

    interface Callback {
        fun onIAgreementAgree()
        fun onIAgreementQuit()
    }

    var onIAgreementAgainDialogCallback: Callback? = null

    override fun getTheme(): Int {
        return R.style.IAgreementDialogStyle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogIagreementPopupAgainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.iagreementOk?.setOnClickListener {
            dismiss()
            onIAgreementAgainDialogCallback?.onIAgreementAgree()
        }
        binding?.iagreementNot?.setOnClickListener {
            dismiss()
            onIAgreementAgainDialogCallback?.onIAgreementQuit()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // required on fragment
        binding = null
    }

    companion object {

        fun newInstance(): IAgreementPopupAgainDialog {
            return IAgreementPopupAgainDialog()
        }
    }
}