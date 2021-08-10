package info.softweb.gauravo2hpractical

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class O2HApp : Application() {
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    override fun onCreate() {
        super.onCreate()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    fun getGoogleClient() : GoogleSignInClient
    {
        return mGoogleSignInClient
    }
}