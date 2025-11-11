package com.github.itechbear.clion.cpplint.nova.util

/**
 * Utilities for detecting and handling Cygwin environment.
 *
 * TODO: Update for CLion Nova 2025.2 toolchain APIs
 * Current implementation uses fallback detection.
 */
object CygwinUtil {

    /**
     * Detects if the current environment is Cygwin.
     *
     * TODO: Implement proper detection using CLion Nova toolchain API:
     * - Research com.jetbrains.cidr.cpp.toolchains.* package for Nova
     * - Look for Cygwin toolchain detection
     * - May need to use different API than CPPToolchains.getInstance()
     */
    fun isCygwinEnvironment(): Boolean {
        // Stub implementation - assumes not Cygwin for now
        // Will be updated with proper Nova API
        return false

        // OLD 2019.3 implementation (for reference):
        // val toolchains = CPPToolchains.getInstance().getToolchains()
        // return toolchains.any { it is Cygwin }
    }

    /**
     * Converts Windows path to Cygwin path.
     *
     * Example: C:\foo\bar → /cygdrive/c/foo/bar
     */
    fun toCygwinPath(windowsPath: String): String {
        if (!isCygwinEnvironment()) {
            return windowsPath
        }

        // Convert C:\path\to\file to /cygdrive/c/path/to/file
        val drive = windowsPath.substringBefore(':')
        val path = windowsPath.substringAfter(':', "").replace('\\', '/')
        return "/cygdrive/${drive.lowercase()}$path"

        // TODO: Verify if Nova has built-in path conversion utility
    }

    /**
     * Gets the path to bash executable.
     *
     * TODO: Update to use Nova toolchain API to get bash path
     */
    fun getBashPath(): String {
        // Default bash path for Unix-like systems
        return "bash"

        // For Cygwin, might need something like:
        // return "/bin/bash" or "C:\cygwin64\bin\bash.exe"
        // depending on toolchain configuration
    }
}
