package info.softweb.gauravo2hpractical.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import info.softweb.gauravo2hpractical.models.LocalUserResult
import info.softweb.gauravo2hpractical.models.UserResult
import info.softweb.gauravo2hpractical.network.RetriveApi
import info.softweb.gauravo2hpractical.network.SafeApiRequest
import info.softweb.gauravo2hpractical.room.AppDatabase
import info.softweb.gauravo2hpractical.room.DataDao
import info.softweb.gauravo2hpractical.util.Coroutines


class RetriveRepository(
    private var api: RetriveApi,
    private var context:Context
) : SafeApiRequest() {

    var db: DataDao = AppDatabase(context).getDataDao()

     fun getData(): LiveData<List<LocalUserResult>> {
        return db.getData()
     }

     suspend fun fetchServerData(results : Int)=
         apiRequest {
                   api.getUsers(results) }

     fun saveQuotes(data: List<LocalUserResult>?) {
        Coroutines.io {
            db.saveAllData(data)
        }
    }

}