package info.softweb.gauravo2hpractical.listener

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface GoogleSignInInterface {
    fun passVal(acct: GoogleSignInAccount?)
}