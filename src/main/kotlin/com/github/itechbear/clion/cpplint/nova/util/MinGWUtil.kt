package com.github.itechbear.clion.cpplint.nova.util

import com.intellij.openapi.util.SystemInfo

/**
 * Utilities for detecting MinGW environment.
 *
 * TODO: Update for CLion Nova 2025.2 toolchain APIs
 * Current implementation uses OS-based fallback detection.
 */
object MinGWUtil {

    /**
     * Detects if the current environment is MinGW.
     *
     * TODO: Implement proper detection using CLion Nova toolchain API:
     * - Research com.jetbrains.cidr.cpp.toolchains.* package for Nova
     * - Look for MinGW toolchain detection
     * - May need to use different API than CPPToolchains.getInstance()
     */
    fun isMinGWEnvironment(): Boolean {
        // Stub implementation - use Windows as proxy for MinGW possibility
        // This is a simplification; proper implementation should check active toolchain
        return SystemInfo.isWindows

        // OLD 2019.3 implementation (for reference):
        // val toolchains = CPPToolchains.getInstance().getToolchains()
        // return toolchains.any { it is MinGW }
    }
}
