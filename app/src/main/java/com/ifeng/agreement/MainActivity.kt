package com.ifeng.agreement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ifeng.iagreement.IAgreementManager
import com.ifeng.iagreement.data.IAgreementDto

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        IAgreementManager.showIAgreementPopupDialog(
            supportFragmentManager,
            agreementDto = IAgreementDto(
                content = getString(com.ifeng.iagreement.R.string.iagreement_desc),
                privacyText = "《隐私条款》",
                agreementText = "《服务协议》",
                privacyUrl = "http://www.3xiaozhi.com/bprivacy",
                agreementUrl = "http://www.3xiaozhi.com/agreement.html"
            ),
            onIAgreementCallback = object : IAgreementManager.Callback {
                override fun onIAgreementAgree() {
                    Toast.makeText(this@MainActivity, "onIAgreementAgree", Toast.LENGTH_LONG).show()
                }

                override fun onIAgreementQuit() {
                    Toast.makeText(this@MainActivity, "onIAgreementQuit", Toast.LENGTH_LONG).show()
                    finish()
                }
            })
    }
}