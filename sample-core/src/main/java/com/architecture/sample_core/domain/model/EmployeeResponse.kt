package com.architecture.sample_core.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class EmployeeResponse(
    val status:String,
    var data:List<EmployeeData>,
    val message:String
):Parcelable
