package io.herpaderp.mutabilityexample

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SampleMutableModel(var name: String = "initial"): Parcelable