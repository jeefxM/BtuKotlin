import com.google.gson.annotations.SerializedName

data class Base<T>(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val total: Int,
    val data: T?
)