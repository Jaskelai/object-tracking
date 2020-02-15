package com.github.jaskelai.object_tracking.presentation.ui.binding_adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

@BindingAdapter("setPhoneMask")
fun setPhoneMask(textView: TextView, value: Boolean?) {
    if (value == true) {
        val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        val watcher = MaskFormatWatcher(mask)
        watcher.installOn(textView)
    }
}