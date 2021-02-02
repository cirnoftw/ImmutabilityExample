package io.herpaderp.mutabilityexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {
    private lateinit var mutableModel: SampleMutableModel
    private lateinit var immutableModel: SampleImmutableModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().run {
            mutableModel = getParcelable(KEY_MUTABLE)!!
            immutableModel = getParcelable(KEY_IMMUTABLE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_second, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reload()
        view.findViewById<Button>(R.id.reloadButton).setOnClickListener { reload() }
    }

    private fun reload() {
        requireView().run {
            findViewById<TextView>(R.id.firstVariableView).text =
                getString(R.string.mutable_label, mutableModel.name)
            findViewById<TextView>(R.id.secondVariableView).text =
                getString(R.string.immutable_label, immutableModel.name)
        }
    }

    companion object {
        const val KEY_MUTABLE = "mutable"
        const val KEY_IMMUTABLE = "immutable"

        fun newInstance(mutable: SampleMutableModel, immutable: SampleImmutableModel) =
            SecondFragment().apply {
                arguments = bundleOf(KEY_MUTABLE to mutable, KEY_IMMUTABLE to immutable)
            }
    }
}