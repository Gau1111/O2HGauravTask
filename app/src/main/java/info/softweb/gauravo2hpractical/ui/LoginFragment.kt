package info.softweb.gauravo2hpractical.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.SignInButton
import info.softweb.gauravo2hpractical.O2HApp
import info.softweb.gauravo2hpractical.R
import info.softweb.gauravo2hpractical.databinding.FragmentLoginBinding
import info.softweb.gauravo2hpractical.listener.GoogleSignInInterface
import info.softweb.gauravo2hpractical.utils.TAG


class LoginFragment : Fragment(), View.OnClickListener, GoogleSignInInterface {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var binding:FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val account = GoogleSignIn.getLastSignedInAccount(this.requireContext())
        account?.let {
            updateUI(account)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        (activity as MainActivity).passVal(this)
        return binding.root
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if(account!=null) {
           // this.requireContext().toast(""+account.displayName)
            val action=LoginFragmentDirections.actionLoginFragmentToRetriveDataFragment()
            action.setGoogleAccount(account)
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        mGoogleSignInClient=(this.requireActivity().application as O2HApp).getGoogleClient()
        binding.signInButton.setSize(SignInButton.SIZE_STANDARD)
        binding.signInButton.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v)
        {
            binding.signInButton ->{

                (activity as MainActivity).makeGoogleLogin(mGoogleSignInClient.signInIntent)
            }
            else -> {

            }
        }
    }

    override fun passVal(acct: GoogleSignInAccount?) {
        //this.requireContext().toast(""+acct.displayName)
        if(acct!=null) {
            //this.requireContext().toast(""+account.displayName)
            val navHostFragment: Fragment? =
                getActivity()?.getSupportFragmentManager()?.findFragmentById(info.softweb.gauravo2hpractical.R.id.nav_host_fragment)
            val fragment=navHostFragment?.childFragmentManager!!.fragments[0]
            Log.d(TAG, "passVal: "+fragment)

            val action=LoginFragmentDirections.actionLoginFragmentToRetriveDataFragment()
            action.setGoogleAccount(acct)
            findNavController().navigate(action)
        }
    }
}