package ir.ha.meproject.presentation.fragments.features.more

import androidx.navigation.fragment.findNavController
import ir.ha.meproject.common.base.BaseFragment
import ir.ha.meproject.common.extensions.singleClick
import ir.ha.meproject.databinding.FragmentMoreBinding

class MoreFragment : BaseFragment<FragmentMoreBinding>(FragmentMoreBinding::inflate) {


    private lateinit var args : MoreFragmentArgs

    override fun initializing() {
        super.initializing()
        args = MoreFragmentArgs.fromBundle(requireArguments())
    }


    override fun uiConfig() {
        super.uiConfig()

        binding.argumentsTV.text = args.argument
        binding.intentValueTV.text = requireActivity().intent.getStringExtra("key") ?: ""
    }


    override fun listeners() {
        super.listeners()
        binding.goToUsersFragment.singleClick {
            findNavController().navigate(MoreFragmentDirections.actionMoreFragmentToUsersFragment())
        }
    }

}