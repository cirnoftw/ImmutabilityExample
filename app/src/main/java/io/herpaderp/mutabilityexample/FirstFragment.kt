package io.herpaderp.mutabilityexample

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {
    private var index = 0
    private var task: Runnable? = null

    private lateinit var mutableModel: SampleMutableModel
    private lateinit var immutableModel: SampleImmutableModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mutableModel = SampleMutableModel()
        immutableModel = SampleImmutableModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_first, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.nextButton).setOnClickListener {
            requireFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(
                    R.id.container,
                    SecondFragment.newInstance(mutableModel, immutableModel),
                    null
                )
                .commit()
        }
        task = Runnable {
            mutate()
            reload()
            task?.let { Handler().postDelayed(it, 1000L) }
        }
        task?.run()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        task = null
    }

    private fun mutate() {
        mutableModel.apply { name = index.toString() }
        immutableModel = immutableModel.copy(name = index.toString())
        index++
    }

    fun reload() {
        view?.run {
            findViewById<TextView>(R.id.firstVariableView).text =
                getString(R.string.mutable_label, mutableModel.name)
            findViewById<TextView>(R.id.secondVariableView).text =
                getString(R.string.immutable_label, immutableModel.name)
        }
    }
}