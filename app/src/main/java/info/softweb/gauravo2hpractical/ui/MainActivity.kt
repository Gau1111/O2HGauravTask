package info.softweb.gauravo2hpractical.ui

import android.R.attr
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.View
import androidx.navigation.findNavController
import info.softweb.gauravo2hpractical.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import info.softweb.gauravo2hpractical.listener.GoogleSignInInterface
import info.softweb.gauravo2hpractical.utils.RC_SIGN_IN
import info.softweb.gauravo2hpractical.utils.TAG


class MainActivity : AppCompatActivity() {
    private var googleSignInInterface : GoogleSignInInterface?=null
    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun makeGoogleLogin(signInIntent: Intent)
    {
        val googleSignInIntent =signInIntent
        startActivityForResult(googleSignInIntent, RC_SIGN_IN)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask!!.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode())
            updateUI(null)
        }
    }

    private fun updateUI(acct: GoogleSignInAccount?) {
     //   val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
          googleSignInInterface?.passVal(acct)
           /* val bundle=Bundle()
            bundle.putParcelable(GOOGLECLIENT,acct)
            navController.navigate(R.id.retriveDataFragment,bundle)*/
        }
    }

    fun passVal(googleSignInInterface: GoogleSignInInterface)
    {
        this.googleSignInInterface=googleSignInInterface
    }
}