package com.architecture.sample_core.model
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity
data class EmployeeData(
    @PrimaryKey(autoGenerate = false)
    val id:Int?,
    val employee_name: String?,
    val employee_salary: String?,
    val employee_age: Int?,
    val profile_image: String?
):Parcelable
