package com.ifeng.iagreement

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.ifeng.iagreement.data.IAgreementDto
import com.ifeng.iagreement.databinding.DialogIagreementPopupBinding


class IAgreementPopupDialog : DialogFragment() {

    companion object {
        private const val IAGREEMENT_DTO = "IAGREEMENT_DTO"
//        private const val TEXT_CLICK_SERVICE = "《服务协议》"
//        private const val TEXT_CLICK_PRIVACY = "《隐私条款》"

        fun newInstance(dto: IAgreementDto): IAgreementPopupDialog {
            val args = Bundle()
            args.putParcelable(IAGREEMENT_DTO, dto)
            val fragment = IAgreementPopupDialog().apply {
                arguments = args
                isCancelable = false
            }
            return fragment
        }
    }

    private val iAgreementDto: IAgreementDto by lazy {
        requireNotNull(
            arguments?.getParcelable(
                IAGREEMENT_DTO
            )
        )
    }

    private val content: String
        get() = iAgreementDto.content

    private val agreementText: String
        get() = iAgreementDto.agreementText

    private val privacyText: String
        get() = iAgreementDto.privacyText

    private val agreementUrl: String
        get() = iAgreementDto.agreementUrl

    private val privacyUrl: String
        get() = iAgreementDto.privacyUrl

    private var binding: DialogIagreementPopupBinding? = null

    interface Callback {
        fun onIAgreementAgree()
        fun onIAgreementNot()
    }

    var onIAgreementDialogCallback: Callback? = null

    override fun getTheme(): Int {
        return R.style.IAgreementDialogStyle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogIagreementPopupBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickToLookAgreement()

        binding?.iagreementOk?.setOnClickListener {
            dismiss()
            onIAgreementDialogCallback?.onIAgreementAgree()
        }
        binding?.iagreementNot?.setOnClickListener {
            dismiss()
            onIAgreementDialogCallback?.onIAgreementNot()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // required on fragment
        binding = null
    }


    private fun setClickToLookAgreement() {
        binding?.apply {
            val sb = SpannableStringBuilder(content)
            val serviceIndex = sb.indexOf(agreementText)
            val privacyIndex = sb.indexOf(privacyText)
            iagreementDesc.movementMethod = LinkMovementMethod.getInstance()
            if (serviceIndex > 0) {
                sb.setSpan(
                    ClickDatesSpan(root.context) {
                        IAgreementBottomSheetFragment.newInstance(agreementUrl, agreementText)
                            .show(childFragmentManager, "IAgreementBottomSheetFragment")
                    },
                    serviceIndex,
                    serviceIndex + agreementText.length,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
            }

            if (privacyIndex > 0) {
                sb.setSpan(
                    ClickDatesSpan(root.context) {
                        IAgreementBottomSheetFragment.newInstance(privacyUrl, privacyText)
                            .show(childFragmentManager, "IAgreementBottomSheetFragment")
                    },
                    privacyIndex,
                    privacyIndex + privacyText.length,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
            }
            iagreementDesc.text = sb
        }
    }

    // ClickableSpan
    class ClickDatesSpan(
        val context: Context,
        val clickCallback: () -> Unit
    ) : ClickableSpan() {

        companion object {
            private const val DELAY = 1000L
        }

        private var prevTime = 0L

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = ContextCompat.getColor(context, R.color.iagreement_blue)
            ds.isFakeBoldText = true
            ds.isUnderlineText = true
        }

        override fun onClick(widget: View) {
            // The point of this is to prevent someone from spam clicking/touching
            val time = System.currentTimeMillis()
            if (time >= prevTime + DELAY) {
                prevTime = time
                // Remove the background color of the font after clicking
                (widget as? TextView)?.highlightColor = Color.TRANSPARENT
                clickCallback()
            }
        }
    }

}