package ru.ltcnt.basher.utils

import android.annotation.SuppressLint
import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.ltcnt.basher.R


fun <T> Observable<T>.androidAsync(): Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}

@SuppressLint("NewApi")
fun Context.copyToClipboard(text: String): Boolean = try {
    val sdk = android.os.Build.VERSION.SDK_INT
    if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
        val clipboard = this.getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager
        clipboard.text = text
    } else {
        val clipboard = this.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = android.content.ClipData
                .newPlainText(this.resources.getString(R.string.message), text)
        clipboard.primaryClip = clip
    }
    true
} catch (e: Exception) {
    false
}