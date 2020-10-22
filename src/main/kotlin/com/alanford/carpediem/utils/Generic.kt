package com.alanford.carpediem.utils

import java.util.*

/**
 * Class used to genrate a random number
 */
object Generic {
    /**
     * Use this when you want to generate an int in tests
     * @param start the lower boundary
     * @param end thr highest boundary
     */
    @JvmStatic
    fun generateNumberBetween(start: Int, end: Int) = (Math.random() * (end - start + 1)).toInt() + start
}