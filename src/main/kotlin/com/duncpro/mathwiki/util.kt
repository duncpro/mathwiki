package com.duncpro.mathwiki

import kotlin.math.max
import kotlin.math.min

fun clamp(value: Double, lb: Double, ub: Double) = min(max(value, lb), ub)