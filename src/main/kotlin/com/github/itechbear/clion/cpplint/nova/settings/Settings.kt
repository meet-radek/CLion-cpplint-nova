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

    fun setBoolean(key: String, value: Boolean) {
        // Store as string because setValue(key, false) UNSETS the key instead of setting it to false!
        instance.setValue(key, value.toString())
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        val stringValue = instance.getValue(key)
        return stringValue?.toBoolean() ?: defaultValue
    }
}
