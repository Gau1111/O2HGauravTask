package info.softweb.gauravo2hpractical.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_user_result")
data class LocalUserResult(
    val cell: String?=null,
    val email: String?=null,
    val gender: String?=null,
    val phone: String?=null,
    val thumbnail: String?=null,
    @PrimaryKey(autoGenerate = true) var localid: Int? = null
)
