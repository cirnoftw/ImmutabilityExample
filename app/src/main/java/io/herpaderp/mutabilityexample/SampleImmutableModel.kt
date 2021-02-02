package io.herpaderp.mutabilityexample

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SampleImmutableModel(val name: String = "initial"): Parcelable