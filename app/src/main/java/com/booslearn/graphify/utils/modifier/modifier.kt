package com.example.cronos.utils.modifier

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.click(
    onClick: () -> Unit
): Modifier {
    return composed {
        this.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            onClick()
        }
    }
}

fun Modifier.clickRipple(
    onClick: () -> Unit
): Modifier {
    return composed {
        this.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple()
        ) {
            onClick()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.combinedClick(
    onClick: () -> Unit
): Modifier {
    return composed {
        this.combinedClickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            onClick()
        }
    }
}

fun <E> List<E>.safeDrop(i: Int): List<E> {
    if (this.size > i) return this.drop(i)
    return emptyList()
}

fun <E> MutableList<E>.safeRemoveAt(index: Int) {
    if (index >= 0 && index < this.size) {
        this.removeAt(index)
    }
}

