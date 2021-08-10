package info.softweb.gauravo2hpractical.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import info.softweb.gauravo2hpractical.models.LocalUserResult
import info.softweb.gauravo2hpractical.models.UserResult

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllData(quotes : List<LocalUserResult>?)

    @Query("SELECT * FROM local_user_result")
    fun getData() : LiveData<List<LocalUserResult>>

}