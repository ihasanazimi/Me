package ir.ha.meproject.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SampleEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String
)