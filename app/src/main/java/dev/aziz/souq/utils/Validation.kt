package dev.aziz.souq.utils

import android.util.Patterns
import dev.aziz.souq.R

fun validateEmail(email: String): Int? {
    return when {
        email.isBlank() -> R.string.email_is_required
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> R.string.invalid_email
        else -> null
    }
}
fun validatePassword(password:  String ) : Int?{
    return when {
        password.isBlank() -> R.string.password_is_required
        password.length < 6 -> R.string.password_must_be_at_least
        else -> null
    }
}
