package com.duncpro.mathwiki.util

import kotlin.math.max
import kotlin.math.min

fun clamp(value: Double, lb: Double, ub: Double) = min(max(value, lb), ub)