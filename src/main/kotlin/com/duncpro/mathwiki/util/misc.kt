package com.duncpro.mathwiki.util

fun <T> repeating(count: Int, value: T) = sequence {
    for (i in 0 until count) {
        yield(value)
    }
}