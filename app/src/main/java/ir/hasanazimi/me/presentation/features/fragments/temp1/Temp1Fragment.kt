package ir.hasanazimi.me.presentation.features.fragments.temp1

import androidx.lifecycle.lifecycleScope
import ir.hasanazimi.me.common.base.BaseFragment
import ir.hasanazimi.me.common.extensions.showToast
import ir.hasanazimi.me.common.views.CircleProgress
import ir.hasanazimi.me.databinding.FragmentTemp1Binding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Temp1Fragment : BaseFragment<FragmentTemp1Binding>(FragmentTemp1Binding::inflate) {

    override fun listeners() {
        super.listeners()


        binding.progress.setProgress(64,true)
        binding.progress.setOnProgressChangedListener(object : CircleProgress.OnProgressChangedListener{
            override fun onProgressChanged(progress: Int) {
                showToast(requireContext(),progress.toString())
            }
        })



        binding.loadingBtn.clickListener{
            lifecycleScope.launch {
                binding.loadingBtn.showLoading(true)
                delay(3000)
                binding.loadingBtn.showLoading(false)
            }
        }



    }
}