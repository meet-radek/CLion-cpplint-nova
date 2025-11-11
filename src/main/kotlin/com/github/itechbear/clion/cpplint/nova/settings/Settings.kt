package com.github.itechbear.clion.cpplint.nova.settings

import com.intellij.ide.util.PropertiesComponent

/**
 * Simple wrapper around IntelliJ's PropertiesComponent for persistent settings storage.
 */
object Settings {
    private val instance = PropertiesComponent.getInstance()

    fun set(key: String, value: String) {
        instance.setValue(key, value)
    }

    fun get(key: String): String? {
        return instance.getValue(key)
    }
}
