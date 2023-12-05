# iagreement-popup-sdk-android
App首次运行收集处理个人信息前需要以醒目方式提示用户阅读隐私政策。


[![](https://jitpack.io/v/imifeng/iagreement-popup-sdk-android.svg)](https://jitpack.io/#imifeng/iagreement-popup-sdk-android)

## 使用
```
// TEST CODE
IAgreementManager.showIAgreementPopupDialog(
    supportFragmentManager,
    dto = IAgreementDto(
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
```
