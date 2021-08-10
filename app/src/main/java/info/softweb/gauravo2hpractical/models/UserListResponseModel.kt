package info.softweb.gauravo2hpractical.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserListResponseModel(
    @SerializedName("info")
    @Expose
    val info: Info?= Info(),
    @SerializedName("results")
    @Expose
    val results: List<UserResult> ?= listOf()
) : Parcelable

@Parcelize
data class Dob(
    @SerializedName("age")
    @Expose
    val age: Int?=null,
    @SerializedName("date")
    @Expose
    val date: String?=null
): Parcelable


@Parcelize
data class Id(
    @SerializedName("name")
    @Expose
    val name: String?=null,
  /*  @SerializedName("value")
    val value: String?=null*/
): Parcelable

@Parcelize
data class Info(
    @SerializedName("page")
    @Expose
    val page: Int?=null,
    @SerializedName("results")
    @Expose
    val results: Int?=null,
    @SerializedName("seed")
    @Expose
    val seed: String?=null,
    @SerializedName("version")
    @Expose
    val version: String?=null
): Parcelable

@Parcelize
data class Location(
    @SerializedName("city")
    @Expose
    val city: String?=null,
    @SerializedName("coordinates")
    @Expose
    val coordinates: Coordinates?=Coordinates(),
    @SerializedName("country")
    @Expose
    val country: String?=null,
 /*   @SerializedName("postcode")
    val postcode: Any,*/
    @SerializedName("state")
    @Expose
    val state: String?=null,
    @SerializedName("street")
    @Expose
    val street: Street?= Street(),
    @SerializedName("timezone")
    @Expose
    val timezone: Timezone?= Timezone()
): Parcelable

@Parcelize
data class Coordinates(
    @SerializedName("latitude")
    @Expose
    val latitude: String?=null,
    @SerializedName("longitude")
    @Expose
    val longitude: String?=null
): Parcelable

@Parcelize
data class Login(
    @SerializedName("md5")
    @Expose
    val md5: String?=null,
    @SerializedName("password")
    @Expose
    val password: String?=null,
    @SerializedName("salt")
    @Expose
    val salt: String?=null,
    @SerializedName("sha1")
    @Expose
    val sha1: String?=null,
    @SerializedName("sha256")
    @Expose
    val sha256: String?=null,
    @SerializedName("username")
    @Expose
    val username: String?=null,
    @SerializedName("uuid")
    @Expose
    val uuid: String?=null
): Parcelable

@Parcelize
data class Name(
    @SerializedName("first")
    @Expose
    val first: String?=null,
    @SerializedName("last")
    @Expose
    val last: String?=null,
    @SerializedName("title")
    @Expose
    val title: String?=null
): Parcelable

@Parcelize
data class Picture(
    @SerializedName("large")
    @Expose
    val large: String?=null,
    @SerializedName("medium")
    @Expose
    val medium: String?=null,
    @SerializedName("thumbnail")
    @Expose
    val thumbnail: String?=null
): Parcelable

@Parcelize
data class Registered(
    @SerializedName("age")
    @Expose
    val age: Int?=null,
    @SerializedName("date")
    @Expose
    val date: String?=null
): Parcelable


@Parcelize
data class UserResult(
    @SerializedName("cell")
    @Expose
    val cell: String?=null,
    @SerializedName("dob")
    @Expose
    val dob: Dob?= Dob(),
    @SerializedName("email")
    @Expose
    val email: String?=null,
    @SerializedName("gender")
    @Expose
    val gender: String?=null,
    @SerializedName("id")
    @Expose
    val id: Id?=Id(),
    @SerializedName("location")
    @Expose
    val location: Location?= Location(),
    @SerializedName("login")
    @Expose
    val login: Login?= Login(),
    @SerializedName("name")
    @Expose
    val name: Name?=Name(),
    @SerializedName("nat")
    @Expose
    val nat: String?=null,
    @SerializedName("phone")
    @Expose
    val phone: String?=null,
    @SerializedName("picture")
    @Expose
    val picture: Picture?= Picture(),
    @SerializedName("registered")
    @Expose
    val registered: Registered?= Registered()
): Parcelable

@Parcelize
data class Street(
    @SerializedName("name")
    @Expose
    val name: String?=null,
    @SerializedName("number")
    @Expose
    val number: Int?=null
): Parcelable

@Parcelize
data class Timezone(
    @SerializedName("description")
    @Expose
    val description: String?=null,
    @SerializedName("offset")
    @Expose
    val offset: String?=null
): Parcelable
