package com.ifeng.iagreement.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//  <string name="web_privacy">隐私政策</string>
//    <string name="web_agreement">用户协议</string>
@Parcelize
data class IAgreementDto(
    val content: String,
    val privacyText: String,
    val agreementText: String,
    val privacyUrl: String,
    val agreementUrl: String,
): Parcelable