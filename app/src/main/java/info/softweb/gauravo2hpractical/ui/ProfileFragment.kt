package info.softweb.gauravo2hpractical.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import info.softweb.gauravo2hpractical.O2HApp
import info.softweb.gauravo2hpractical.R
import info.softweb.gauravo2hpractical.databinding.FragmentProfileBinding
import info.softweb.gauravo2hpractical.utils.TAG
import kotlinx.android.synthetic.main.row_user.*


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val args: ProfileFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val data=args.googleAccount
        data?.let {
            it.email?.let { email ->
                binding.txtEmail.text=email
            }
            it.displayName?.let { displayName ->
                binding.txtFullName.text=displayName
            }

            Log.d(TAG, "initViews: "+it.photoUrl)
            it.photoUrl?.let { photoUrl ->
                Glide.with(this).load(photoUrl.toString()).placeholder(R.drawable.ic_profile_image).error(R.drawable.ic_profile_image).into(binding.imgProfile)
            }
        }
        binding.txtLogout.setOnClickListener {
            ((requireActivity().application) as O2HApp).getGoogleClient().signOut().addOnCompleteListener(this.requireActivity(), object : OnCompleteListener<Void?> {
                    override fun onComplete(task: Task<Void?>) {
                        Navigation.findNavController(binding.txtLogout).navigate(R.id.loginFragment)
                    }
                })

        }

    }
}