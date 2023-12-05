package com.ifeng.iagreement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ifeng.iagreement.databinding.FragmentBottomSheetIagreementBinding

class IAgreementBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        private const val IAGREEMENT_URL = "IAGREEMENT_URL"
        private const val IAGREEMENT_TITLE = "IAGREEMENT_TITLE"

        fun newInstance(url: String, title: String): IAgreementBottomSheetFragment {
            val args = Bundle()
            args.putString(IAGREEMENT_URL, url)
            args.putString(IAGREEMENT_TITLE, title)
            val fragment = IAgreementBottomSheetFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val iAgreementUrl: String by lazy { requireNotNull(arguments?.getString(IAGREEMENT_URL)) }
    private val iAgreementTitle: String by lazy {
        requireNotNull(
            arguments?.getString(
                IAGREEMENT_TITLE
            )
        )
    }

    private var binding: FragmentBottomSheetIagreementBinding? = null

    override fun getTheme(): Int {
        return R.style.IAgreementBottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetIagreementBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            iagreementClose.setOnClickListener {
                dismiss()
            }

            iagreementTitle.text = iAgreementTitle
            iagreementWebview.loadUrl(iAgreementUrl)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // required on fragment
        binding = null
    }
}