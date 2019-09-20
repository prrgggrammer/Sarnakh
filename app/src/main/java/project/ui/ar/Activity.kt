package project.ui.ar

import android.os.Bundle
import android.widget.Toast
import cn.easyar.Engine
import project.ui.base.BaseActivity
import javax.inject.Inject

import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.R
import ir.sinapp.sarnakh.databinding.ActivityArBinding
import project.utils.AppLogger
import project.utils.ar.GLView
import android.view.ViewGroup
import com.jakewharton.rxbinding3.view.clicks


class ARActivity : BaseActivity<ActivityArBinding, ARViewModel>(ActivityArBinding::class.java),
    ARNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel

    @Inject
    override lateinit var viewModel: ARViewModel

    lateinit var glView: GLView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this

        glView = GLView(this)

        if (!Engine.initialize(this, getString(R.string.ar_api_key))) {
            AppLogger.e("HelloAR", "Initialization Failed.");
            Toast.makeText(this, Engine.errorMessage(), Toast.LENGTH_LONG).show();
            return;
        }

        binding.preview.addView(
            glView, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        viewModel += binding.btnAnswer.clicks().subscribe {
            openAnswerActivity()
        }

    }

    override fun openAnswerActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}




