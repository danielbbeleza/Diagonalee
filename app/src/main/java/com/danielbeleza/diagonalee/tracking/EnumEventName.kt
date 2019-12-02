package com.danielbeleza.common_core

enum class EnumEventName {
    CLICK, ITEM_VIEW;

    override fun toString(): String {
        return this.name.toLowerCase()
    }
}
