package com.github.jaskelai.object_tracking.presentation.utils.ext

inline val String.onlyDigits: String get() = replace("[^\\d]".toRegex(), "")
